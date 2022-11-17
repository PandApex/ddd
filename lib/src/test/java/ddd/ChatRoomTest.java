package ddd;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    @Test
    void getName() {
        ChatRoomName chatRoomName = new ChatRoomName("DDD");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        assertEquals(chatRoomName, chatRoom.getName());
    }

    @Test
    void noMember() {
        ChatRoomName chatRoomName = new ChatRoomName("DDD");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        assertEquals(0, chatRoom.getMembers().size());
    }

    @Test
    void addAndRemoveMembers() {
        ChatRoomName chatRoomName = new ChatRoomName("DDD");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        Member member1 = new Member("John Seed");
        chatRoom.addMember(member1);
        assertEquals(member1, chatRoom.getMembers().get(0));
        assertEquals(1, chatRoom.getMembers().size());

        Member member2 = new Member("Apple Jack");
        chatRoom.addMember(member2);
        assertEquals(member2, chatRoom.getMembers().get(1));
        assertEquals(2, chatRoom.getMembers().size());

        chatRoom.removeMember(member2);
        assertEquals(member1, chatRoom.getMembers().get(0));
        assertEquals(1, chatRoom.getMembers().size());

        // Should not throw exception
        chatRoom.removeMember(member2);

        chatRoom.removeMember(member1);
        assertEquals(0, chatRoom.getMembers().size());
    }

    @Test
    void noMessage() {
        ChatRoomName chatRoomName = new ChatRoomName("DDD");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        assertEquals(0, chatRoom.getMessages().size());
    }


    @Test
    void addSentMessages() {
        ChatRoomName chatRoomName = new ChatRoomName("DDD");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        Member member1 = new Member("John Seed");
        chatRoom.addMember(member1);
        Member member2 = new Member("Apple Jack");
        chatRoom.addMember(member2);

        var sentTimeDate1 = LocalDateTime.now();
        SentMessage sentMessage1 = chatRoom.createSentMessage(member1, new MessageContent("Hello World"), new MessageSentTime(sentTimeDate1));
        assertEquals(sentMessage1, chatRoom.getMessages().get(0));
        assertEquals(1, chatRoom.getMessages().size());

        var sentTimeDate2 = LocalDateTime.now();
        SentMessage sentMessage2 = chatRoom.createSentMessage(member2, new MessageContent("Hello World2"), new MessageSentTime(sentTimeDate2));
        assertEquals(sentMessage2, chatRoom.getMessages().get(1));
        assertEquals(2, chatRoom.getMessages().size());
    }
}