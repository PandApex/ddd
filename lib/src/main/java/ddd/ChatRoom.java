package ddd;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
	private final ChatRoomName name;
	private final List<Member> members = new ArrayList<>();
	private final List<SentMessage> messages = new ArrayList<>();
	
	public ChatRoom(ChatRoomName name) {
		this.name = name;
	}

	public ChatRoomName getName() {
		return name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public List<SentMessage> getMessages() {
		return messages;
	}
	
	public void addMember(Member member) {
		this.members.add(member);
	}
	
	public void removeMember(Member member) {
		this.members.remove(member);
	}
	
	public void addSentMessage(SentMessage sentMessage) {
		if (sentMessage.getChatRoom() != this) {
			throw new InvalidValueException("SentMessage.chatRoom must be this room");
		}
		this.messages.add(sentMessage);
	}
}
