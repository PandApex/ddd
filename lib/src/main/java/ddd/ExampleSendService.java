package ddd;

public class ExampleSendService implements SendService {
	
	private Scheduler scheduler;
	
	public ExampleSendService(Scheduler scheduler) {
		this.scheduler=scheduler;
	}

	@Override
	public SentMessage sendNow(SendNowMessage message) {
		ChatRoom room = message.getChatRoom();
		
		// TODO: IO が走る
		
		var sentMessage = SentMessage.fromMessage(message, MessageSentTime.now()); 
		room.addSentMessage(sentMessage);
		
		return sentMessage;
	}

	@Override
	public void sendLater(ScheduledMessage message) {
		scheduler.scheduleMessage(message);
	}

}
