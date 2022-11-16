package ddd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomNameTest {

    @Test
    void chatRoomNameOverLength() {
        String name = "Domain Driven Design";
        assertThrows(InvalidValueException.class, () -> {new ChatRoomName(name);});
    }

    @Test
    void chatRoomNameWithinLength() {
        String name = "DDD";
        ChatRoomName chatRoomName = new ChatRoomName(name);

        // Should reach here
        assertTrue(true);
    }

    @Test
    void chatRoomNameOnThreshold() {
        String name = "1234567890";
        ChatRoomName chatRoomName = new ChatRoomName(name);

        // Should reach here
        assertTrue(true);
    }

    @Test
    void chatRoomNameOverThreshold() {
        String name = "1234567890A";
        assertThrows(InvalidValueException.class, () -> {new ChatRoomName(name);});
    }

    @Test
    void chatRoomNameWithEmoji() {

    }
}