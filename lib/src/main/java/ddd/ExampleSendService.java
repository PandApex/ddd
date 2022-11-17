package ddd;

public class ExampleSendService implements SendService {
	
	private Scheduler scheduler;
	
	public ExampleSendService(Scheduler scheduler) {
		this.scheduler=scheduler;
	}

	@Override
	public SentMessage sendNow(SendNowMessage message) {
		ChatRoom room = message.getChatRoom();
		
		// TODO: IO running

		return room.createSentMessage(message, MessageSentTime.now());
	}

	@Override
	public void sendLater(ScheduledMessage message) {
		scheduler.scheduleMessage(message);
	}

}
