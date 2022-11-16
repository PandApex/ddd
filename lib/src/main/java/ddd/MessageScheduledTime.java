package ddd;

import java.time.LocalDateTime;

public class MessageScheduledTime {
	private LocalDateTime scheduledTime;
	
	public LocalDateTime LocalDateTime() {
		return scheduledTime;
	}
	
	public MessageScheduledTime(LocalDateTime scheduledTime) {
		LocalDateTime now = LocalDateTime.now();
		if (scheduledTime.isBefore(now)) {
			throw new InvalidValueException("MessageScheduledTime must be in the future");
		}
		
		this.scheduledTime = scheduledTime;
	}

	@Override
	public int hashCode() {
		return scheduledTime.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MessageScheduledTime)) {
			return false;
		}
		MessageScheduledTime time = (MessageScheduledTime) obj;
		return scheduledTime.equals(time.scheduledTime);
	}
	
	
}
