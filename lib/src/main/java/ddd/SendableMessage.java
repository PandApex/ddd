package ddd;

abstract public class SendableMessage extends Message {
	public SendableMessage(Member sender, MessageContent content, ChatRoom chatRoom) {
		super(sender, content, chatRoom);
	}

	abstract public void send(SendService sendService);
}
