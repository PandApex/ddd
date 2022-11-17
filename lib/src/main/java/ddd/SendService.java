package ddd;

public interface SendService {
	SentMessage sendNow(SendNowMessage message);
	SendResult sendLater(ScheduledMessage message);
}
