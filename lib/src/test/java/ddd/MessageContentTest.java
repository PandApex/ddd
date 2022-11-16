package ddd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageContentTest {

    private static String repeat(String s, int n) {
        if (s == null) {
            return null;
        }
        return s.repeat(Math.max(0, n));
    }

    @Test
    void messageContentOverLength() {
        String message = repeat("本日の会議資料を添付いたします。", 300);
        assertThrows(InvalidValueException.class, () -> {new MessageContent(message);});
    }

    @Test
    void messageContentWithinLength() {
        String message = "こんにちは、私はドメイン駆動設計の本を読んでいます。これはテストです。";
        MessageContent messageContent = new MessageContent(message);

        // Should not throw exception
        assertEquals(message, messageContent.getText());
    }

    @Test
    void messageContentOnThreshold() {
        String message = repeat("a", 200);
        MessageContent messageContent = new MessageContent(message);

        // Should not throw exception
        assertEquals(message, messageContent.getText());
    }

    @Test
    void messageContentOverThreshold() {
        String message = repeat("a", 201);
        assertThrows(InvalidValueException.class, () -> {new MessageContent(message);});
    }

    @Test
    void messageContentWithEmoji() {
        String message = "こんにちは、私はドメイン駆動設計の本を読んでいます。これはテストです。😀";
        MessageContent messageContent = new MessageContent(message);

        // Should not throw exception
        assertEquals(message, messageContent.getText());
    }
}