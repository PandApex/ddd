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
}
