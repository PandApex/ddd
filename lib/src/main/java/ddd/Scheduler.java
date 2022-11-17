package ddd;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
	private List<ScheduledMessage> scheduledMessages = new ArrayList<>();

	public List<ScheduledMessage> getScheduledMessages() {
		return scheduledMessages;
	}
	
	public void deleteScheduledMessage(ScheduledMessage message) {
		this.scheduledMessages.remove(message);
	}
	
	public void setScheduleTime(ScheduledMessage message, MessageScheduledTime newTime) {
		if (!hasMessage(message)) {
			throw new InvalidValueException("Scheduler does not have this message");
		}
		message.setScheduledTime(newTime);
	}

	public void setContent(ScheduledMessage message, MessageContent newContent) {
		if (!hasMessage(message)) {
			throw new InvalidValueException("Scheduler does not have this message");
		}
		message.setContent(newContent);
	}
	
	private boolean hasMessage(ScheduledMessage message) {
		for (ScheduledMessage msg : scheduledMessages) {
			if (msg.equals(message)) {
				return true;
			}
		}
		return false;
	}
	
	public SendResult scheduleMessage(ScheduledMessage message) {
		scheduledMessages.add(message);
		
		var result = new SendResult();
		return result;
	}
}
