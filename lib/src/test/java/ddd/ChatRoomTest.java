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
        SentMessage sentMessage1 = new SentMessage(member1, new MessageContent("Hello"), chatRoom, new MessageSentTime(sentTimeDate1));
        chatRoom.addSentMessage(sentMessage1);
        assertEquals(sentMessage1, chatRoom.getMessages().get(0));
        assertEquals(1, chatRoom.getMessages().size());

        var sentTimeDate2 = LocalDateTime.now();
        SentMessage sentMessage2 = new SentMessage(member2, new MessageContent("Hi"), chatRoom, new MessageSentTime(sentTimeDate2));
        chatRoom.addSentMessage(sentMessage2);
        assertEquals(sentMessage2, chatRoom.getMessages().get(1));
        assertEquals(2, chatRoom.getMessages().size());
    }

    @Test
    void addSentMessageOfDifferentRoom() {
        ChatRoomName chatRoomName = new ChatRoomName("DDD");
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        ChatRoomName chatRoomName2 = new ChatRoomName("DDD2");
        ChatRoom chatRoom2 = new ChatRoom(chatRoomName2);
        Member member = new Member("John Seed");
        chatRoom.addMember(member);

        var sentTimeDate = LocalDateTime.now();
        // Message for chatRoom2 not for expected chatRoom
        SentMessage sentMessage = new SentMessage(member, new MessageContent("Hello"), chatRoom2, new MessageSentTime(sentTimeDate));
        assertThrows(InvalidValueException.class, () -> {chatRoom.addSentMessage(sentMessage);});
    }
}