package ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {

    private static final LocalDateTime mockDateTime = LocalDateTime.of(2022, 1, 1, 1, 0, 0);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void noScheduledMessage() {
        List<ScheduledMessage> scheduledMessages = new Scheduler().getScheduledMessages();
        assertEquals(0, scheduledMessages.size());
    }

    @Test
    void addScheduledMessages() {
        Scheduler scheduler = new Scheduler();
        MessageContent messageContent = new MessageContent("Hello");
        MessageScheduledTime messageScheduledTime = new MessageScheduledTime(LocalDateTime.now().plusHours(3));
        Member member = new Member("John Seed");
        ChatRoom chatRoom = new ChatRoom(new ChatRoomName("DDD"));
        chatRoom.addMember(member);

        ScheduledMessage scheduledMessage = new ScheduledMessage(member, messageContent, chatRoom, messageScheduledTime);
        scheduler.scheduleMessage(scheduledMessage);
        assertEquals(1, scheduler.getScheduledMessages().size());
        assertEquals(scheduledMessage, scheduler.getScheduledMessages().get(0));

        MessageContent messageContent2 = new MessageContent("Hello");
        MessageScheduledTime messageScheduledTime2 = new MessageScheduledTime(LocalDateTime.now().plusHours(1));
        Member member2 = new Member("Apple Jack");
        ChatRoom chatRoom2 = new ChatRoom(new ChatRoomName("DDD"));
        chatRoom2.addMember(member2);

        ScheduledMessage scheduledMessage2 = new ScheduledMessage(member2, messageContent2, chatRoom2, messageScheduledTime2);
        scheduler.scheduleMessage(scheduledMessage2);
        assertEquals(2, scheduler.getScheduledMessages().size());
        assertEquals(scheduledMessage2, scheduler.getScheduledMessages().get(1));
    }

    @Test
    void deleteScheduledMessages() {
        Scheduler scheduler = new Scheduler();
        MessageContent messageContent = new MessageContent("Hello");
        MessageScheduledTime messageScheduledTime = new MessageScheduledTime(LocalDateTime.now().plusHours(3));
        Member member = new Member("John Seed");
        ChatRoom chatRoom = new ChatRoom(new ChatRoomName("DDD"));
        chatRoom.addMember(member);

        ScheduledMessage scheduledMessage = new ScheduledMessage(member, messageContent, chatRoom, messageScheduledTime);
        scheduler.scheduleMessage(scheduledMessage);
        assertEquals(1, scheduler.getScheduledMessages().size());
        assertEquals(scheduledMessage, scheduler.getScheduledMessages().get(0));

        scheduler.scheduleMessage(scheduledMessage);
        assertEquals(2, scheduler.getScheduledMessages().size());
        assertEquals(scheduledMessage, scheduler.getScheduledMessages().get(0));

        // delete first message
        scheduler.deleteScheduledMessage(scheduledMessage);
        assertEquals(1, scheduler.getScheduledMessages().size());

        // delete second message
        scheduler.deleteScheduledMessage(scheduledMessage);
        assertEquals(0, scheduler.getScheduledMessages().size());
    }

    @Test
    void editScheduledSendTime() {
        Scheduler scheduler = new Scheduler();
        MessageContent messageContent = new MessageContent("Hello");
        MessageScheduledTime messageScheduledTime = new MessageScheduledTime(LocalDateTime.now().plusHours(3));
        Member member = new Member("John Seed");
        ChatRoom chatRoom = new ChatRoom(new ChatRoomName("DDD"));
        chatRoom.addMember(member);

        ScheduledMessage scheduledMessage = new ScheduledMessage(member, messageContent, chatRoom, messageScheduledTime);
        scheduler.scheduleMessage(scheduledMessage);
        assertEquals(1, scheduler.getScheduledMessages().size());
        assertEquals(scheduledMessage, scheduler.getScheduledMessages().get(0));

        // Change send time to 1 hour later
        MessageScheduledTime newMessageScheduledTime = new MessageScheduledTime(LocalDateTime.now().plusHours(1));
        scheduler.setScheduleTime(scheduledMessage, newMessageScheduledTime);
        ScheduledMessage updatedScheduledMessage = scheduler.getScheduledMessages().get(0);
        assertEquals(newMessageScheduledTime, updatedScheduledMessage.getScheduledTime());

        // Change send time to before now
        assertThrows(InvalidValueException.class, () -> {
            MessageScheduledTime newMessageScheduledTime2 = new MessageScheduledTime(LocalDateTime.now().minusHours(1));
            scheduler.setScheduleTime(scheduledMessage, newMessageScheduledTime2);
        });

        // Try to change send time of a message that is not scheduled
        assertThrows(InvalidValueException.class, () -> {
            ScheduledMessage unscheduledMessage = new ScheduledMessage(new Member("Bob"), messageContent, chatRoom, messageScheduledTime);
            MessageScheduledTime newMessageScheduledTime2 = new MessageScheduledTime(LocalDateTime.now().plusHours(1));
            scheduler.setScheduleTime(unscheduledMessage, newMessageScheduledTime2);
        });
    }

    @Test
    void editScheduledMessageContent() {
        Scheduler scheduler = new Scheduler();
        MessageContent messageContent = new MessageContent("Hello");
        MessageScheduledTime messageScheduledTime = new MessageScheduledTime(LocalDateTime.now().plusHours(3));
        Member member = new Member("John Seed");
        ChatRoom chatRoom = new ChatRoom(new ChatRoomName("DDD"));
        chatRoom.addMember(member);

        ScheduledMessage scheduledMessage = new ScheduledMessage(member, messageContent, chatRoom, messageScheduledTime);
        scheduler.scheduleMessage(scheduledMessage);
        assertEquals(1, scheduler.getScheduledMessages().size());
        assertEquals(scheduledMessage, scheduler.getScheduledMessages().get(0));

        // Change message content
        MessageContent newMessageContent = new MessageContent("Hello World");
        scheduler.setContent(scheduledMessage, newMessageContent);
        ScheduledMessage updatedScheduledMessage = scheduler.getScheduledMessages().get(0);
        assertEquals(newMessageContent, updatedScheduledMessage.getContent());

        // Try to change message content of a message that is not scheduled
        assertThrows(InvalidValueException.class, () -> {
            ScheduledMessage unscheduledMessage = new ScheduledMessage(new Member("Bob"), messageContent, chatRoom, messageScheduledTime);
            MessageContent newMessageContent2 = new MessageContent("Hello World");
            scheduler.setContent(unscheduledMessage, newMessageContent2);
        });
    }
    @Test
    void messageSentByOutsider() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            ChatRoomName chatRoomName = new ChatRoomName("DDD");
            ChatRoom chatRoom = new ChatRoom(chatRoomName);
            // outsider does not belong to the chat room
            Member outsider = new Member("Bobby");
            MessageContent messageContent = new MessageContent("Spam message");
            MessageScheduledTime messageScheduledTime = new MessageScheduledTime(mockDateTime.plusHours(1));
            assertThrows(InvalidValueException.class, () -> {
                ScheduledMessage message = new ScheduledMessage(outsider, messageContent, chatRoom, messageScheduledTime);
            });
        }
    }

    @Test
    void sendScheduledMessage() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            ChatRoomName chatRoomName = new ChatRoomName("DDD");
            ChatRoom chatRoom = new ChatRoom(chatRoomName);
            Member sender = new Member("John Seed");
            chatRoom.addMember(sender);
            MessageContent messageContent = new MessageContent("Hello");
            // Schedule message to be sent 10 seconds later
            MessageScheduledTime messageScheduledTime = new MessageScheduledTime(mockDateTime.plusSeconds(10));
            ScheduledMessage scheduledMessage = new ScheduledMessage(sender, messageContent, chatRoom, messageScheduledTime);
            assertEquals(0, chatRoom.getMessages().size());

            Scheduler scheduler = new Scheduler();
            SendService sendService = new ExampleSendService(scheduler);
            SendResult result = scheduledMessage.send(sendService);

            assertEquals(1, scheduler.getScheduledMessages().size());
            result.addHandler(new SendResultHandler() {
                @Override
                public void onSendSuccess(SentMessage sentMessage) {
                    assertEquals(messageContent, sentMessage.getContent());
                    assertEquals(sender, sentMessage.getSender());
                    assertEquals(chatRoom, sentMessage.getChatRoom());
                    assertEquals(MessageSentTime.now(), sentMessage.getSentTime());
                }

                @Override
                public void onSendFailure() {
                    fail();
                }
            });

            // Set time now to 10 seconds later
            mock.when(LocalDateTime::now).thenReturn(mockDateTime.plusSeconds(10));

            // TODO
//            assertEquals(0, scheduler.getScheduledMessages().size());
        }
    }
}