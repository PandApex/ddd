package ddd;

public class MessageContent {
	private String text;
	
	public String getText() {
		return text;
	}
	
	public MessageContent(String text) {
		if (text.length() > 200) {
			throw new InvalidValueException("MessageContent.length must be <= 200");
		}
		this.text = text;
	}
}
