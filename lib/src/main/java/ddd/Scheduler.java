package ddd;

import java.util.List;

public class Scheduler {
	private List<ScheduledMessage> scheduledMessages;

	public List<ScheduledMessage> getScheduledMessages() {
		return scheduledMessages;
	}
	
	public void deleteScheduledMessage(ScheduledMessage message) {
		this.scheduledMessages.remove(message);
	}
	
	public void setScheduleTime(ScheduledMessage message, MessageScheduledTime newTime) {
		if (!hasMessage(message)) {
			throw new RuntimeException("message is not in queue");
		}
		message.setScheduledTime(newTime);
	}
	
	private boolean hasMessage(ScheduledMessage message) {
		for (ScheduledMessage msg : scheduledMessages) {
			if (msg.equals(message)) {
				return true;
			}
		}
		return false;
	}
	
	public void scheduleMessage(ScheduledMessage message) {
		scheduledMessages.add(message);
	}
}
