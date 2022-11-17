package ddd;

public interface SendResultHandler {
	void onSendSuccess(SentMessage sentMessage);
	void onSendFailure();
}
