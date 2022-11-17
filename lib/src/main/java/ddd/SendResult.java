package ddd;

import java.util.ArrayList;
import java.util.List;

public class SendResult {
	private List<SendResultHandler> handlers = new ArrayList<>();
	private SentMessage sentMessage = null;
	private boolean failed = false;
	
	public void addHandler(SendResultHandler handler) {
		handlers.add(handler);
		
		if (sentMessage != null) {
			handler.onSendSuccess(sentMessage);
		}
		if (failed) {
			handler.onSendFailure();
		}
	}
	
	public void removeHandler(SendResultHandler handler) {
		handlers.remove(handler);
	}
	
	void messageSent(SentMessage sentMessage) {
		handlers.forEach(h -> h.onSendSuccess(sentMessage));
		this.sentMessage = sentMessage;
	}
	
	void fail() {
		handlers.forEach(h -> h.onSendFailure());
		failed = true;
	}
}
