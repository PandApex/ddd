package ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageSentTimeTest {

    private static final LocalDateTime mockDateTime = LocalDateTime.of(2022, 1, 1, 1, 0, 0);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void now() {
        // initialize mock
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            MessageSentTime sentTime = MessageSentTime.now();
            assertEquals(sentTime.LocalDateTime(), LocalDateTime.of(2022, 1, 1, 1, 0, 0));
        }
    }

    @Test
    public void constructor() {
        LocalDateTime sentDateTime = LocalDateTime.of(2022, 3, 1, 1, 0, 0);
        MessageSentTime sentTime = new MessageSentTime(sentDateTime);
        assertEquals(sentDateTime, sentTime.LocalDateTime());
    }

    @Test
    public void sentNow() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            MessageSentTime sentTime = new MessageSentTime(mockDateTime);
            // should not throw exception
            assertEquals(mockDateTime, sentTime.LocalDateTime());
        }
    }

    @Test
    public void sentBeforeNow() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            var sentBeforeTime = mockDateTime.minusHours(2);
            MessageSentTime sentTime = new MessageSentTime(sentBeforeTime);
            // should not throw exception
            assertEquals(sentBeforeTime, sentTime.LocalDateTime());
        }
    }

    @Test
    public void sentAfterNow() {
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            var sentAfterTime = mockDateTime.plusHours(2);
            assertThrows(InvalidValueException.class, () -> {
                new MessageSentTime(sentAfterTime);
            });
        }
    }
}