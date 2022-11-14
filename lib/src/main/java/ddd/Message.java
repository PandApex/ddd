package ddd;

public abstract class Message {
	protected MessageContent content;
	private Member sender;
	private ChatRoom chatRoom;
	
	public MessageContent getContent() {
		return content;
	}
	
	public Member getSender() {
		return sender;
	}
	
	public ChatRoom getChatRoom() {
		return chatRoom;
	}
	
	public Message(Member sender, MessageContent content, ChatRoom chatRoom) {
		this.sender = sender;
		this.content = content;
		this.chatRoom = chatRoom;
	}
}
