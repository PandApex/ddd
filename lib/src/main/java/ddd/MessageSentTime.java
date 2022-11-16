package ddd;

import java.time.LocalDateTime;

public class MessageSentTime {
	private LocalDateTime sentTime;
	
	public LocalDateTime LocalDateTime() {
		return sentTime;
	}
	
	public MessageSentTime(LocalDateTime sentTime) {
		LocalDateTime now = LocalDateTime.now();
		if (sentTime.isAfter(now)) {
			throw new InvalidValueException("MessageSentTime must be in the past");
		}
		
		this.sentTime = sentTime;
	}
	
	public static MessageSentTime now() {
		return new MessageSentTime(LocalDateTime.now());
	}

	@Override
	public int hashCode() {
		return sentTime.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MessageSentTime)) {
			return false;
		}
		MessageSentTime time = (MessageSentTime) obj;
		return sentTime.equals(time);
	}
	
	
}
