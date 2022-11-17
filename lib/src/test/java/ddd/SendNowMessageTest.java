package ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SendNowMessageTest {

    private static final LocalDateTime mockDateTime = LocalDateTime.of(2022, 1, 1, 1, 0, 0);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void sendMessageNow() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            ChatRoomName chatRoomName = new ChatRoomName("DDD");
            ChatRoom chatRoom = new ChatRoom(chatRoomName);
            Member sender = new Member("John Seed");
            chatRoom.addMember(sender);
            MessageContent messageContent = new MessageContent("Hello World");
            SendNowMessage message = new SendNowMessage(sender, messageContent, chatRoom);

            SendService sendService = new ExampleSendService(new Scheduler());
            SendResult sendResult = message.send(sendService);
            sendResult.addHandler(new SendResultHandler() {
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

        }
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
            assertThrows(InvalidValueException.class, () -> {
                SendNowMessage message = new SendNowMessage(outsider, messageContent, chatRoom);
            });
        }
    }
}