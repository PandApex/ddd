package ddd;

public class SendNowMessage extends SendableMessage{

	public SendNowMessage(Member sender, MessageContent content, ChatRoom chatRoom) {
		super(sender, content, chatRoom);
	}

	@Override
	public SendResult send(SendService sendService) {
		var sentMessage = sendService.sendNow(this);
		
		var result = new SendResult();
		result.messageSent(sentMessage);
		return result;
	}


}
