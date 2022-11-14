package ddd;

public class SendNowMessage extends SendableMessage{

	public SendNowMessage(Member sender, MessageContent content, ChatRoom chatRoom) {
		super(sender, content, chatRoom);
	}

	@Override
	public void send(SendService sendService) {
		sendService.sendNow(this);
	}


}
