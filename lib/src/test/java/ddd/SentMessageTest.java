package ddd;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SentMessageTest {

    @Test
    void getSentTime() {
        Member sender = new Member("Steve Bob");
        MessageContent content = new MessageContent("Hello World!");
        ChatRoomName chatRoomName = new ChatRoomName("Room #1");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        chatRoom.addMember(sender);

        var sentDateTime = LocalDateTime.now();
        MessageSentTime sentTime = new MessageSentTime(sentDateTime);
        SentMessage sentMessage =chatRoom.createSentMessage(sender, content, sentTime);
        assertEquals(sentTime, sentMessage.getSentTime());
    }

    @Test
    void fromSendNowMessage() {
        Member sender = new Member("Steve Bob");
        MessageContent content = new MessageContent("Hello World!");
        ChatRoomName chatRoomName = new ChatRoomName("Room #1");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        chatRoom.addMember(sender);

        var sentDateTime = LocalDateTime.now();
        MessageSentTime sentTime = new MessageSentTime(sentDateTime);
        SendNowMessage sendNowMessage = new SendNowMessage(sender, content, chatRoom);
        SentMessage sentMessage = SentMessage.fromMessage(sendNowMessage, sentTime);
        assertEquals(sender, sentMessage.getSender());
        assertEquals(content, sentMessage.getContent());
        assertEquals(chatRoom, sentMessage.getChatRoom());
        assertEquals(sentTime, sentMessage.getSentTime());
    }

    @Test
    void fromSendableMessage() {
        Member sender = new Member("Steve Bob");
        MessageContent content = new MessageContent("Hello World!");
        ChatRoomName chatRoomName = new ChatRoomName("Room #1");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        chatRoom.addMember(sender);

        var sentDateTime = LocalDateTime.now();
        MessageSentTime sentTime = new MessageSentTime(sentDateTime);
        SendableMessage sendableMessage = new SendNowMessage(sender, content, chatRoom);
        SentMessage sentMessage = SentMessage.fromMessage(sendableMessage, sentTime);
        assertEquals(sender, sentMessage.getSender());
        assertEquals(content, sentMessage.getContent());
        assertEquals(chatRoom, sentMessage.getChatRoom());
        assertEquals(sentTime, sentMessage.getSentTime());
    }
}