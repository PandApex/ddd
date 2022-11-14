package ddd;

import java.util.List;

public class ChatRoom {
	private ChatRoomName name;
	private List<Member> members;
	private List<SentMessage> messages;
	
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
		this.messages.add(sentMessage);
	}
}
