package ddd;

public class SentMessage extends Message{
	private MessageSentTime sentTime;

	SentMessage(Member sender, MessageContent content, ChatRoom chatRoom, MessageSentTime sentTime) {
		super(sender, content, chatRoom);
		this.sentTime = sentTime;
	}
	
	public MessageSentTime getSentTime() {
		return sentTime;
	}
	
	static SentMessage fromMessage(Message message, MessageSentTime sentTime) {
		return new SentMessage(message.getSender(), message.getContent(), message.getChatRoom(),sentTime);
	}

}
