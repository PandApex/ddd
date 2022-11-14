package ddd;

public class ChatRoomName {
	private String name;
	
	public String getName() {
		return name;
	}
	
	public ChatRoomName(String name) {
		if (name.length() > 10) {
			throw new InvalidValueException("ChatRoomName.length must be <= 10");
		}
		this.name = name;
	}
}
