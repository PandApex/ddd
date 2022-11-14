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
			throw new RuntimeException("TODO");
		}
		
		this.scheduledTime = scheduledTime;
	}
}
