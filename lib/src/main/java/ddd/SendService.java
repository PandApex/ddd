package ddd;

public interface SendService {
	SentMessage sendNow(SendNowMessage message);
	void sendLater(ScheduledMessage message);
}
