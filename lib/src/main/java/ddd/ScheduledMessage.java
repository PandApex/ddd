package ddd;

public class ScheduledMessage extends SendableMessage {
	public ScheduledMessage(Member sender, MessageContent content, ChatRoom chatRoom,MessageScheduledTime scheduledTime) {
		super(sender, content, chatRoom);
		this.scheduledTime=scheduledTime;
	}

	private MessageScheduledTime scheduledTime;
	
	public MessageScheduledTime getScheduledTime() {
		return scheduledTime;
	}
	
	public void setContent(MessageContent newContent) {
		this.content=newContent;
	}
	
	public void setScheduledTime(MessageScheduledTime newScheduledTime) {
		this.scheduledTime=newScheduledTime;
	}

	@Override
	public void send(SendService sendService) {
		sendService.sendLater(this);
	}
}
