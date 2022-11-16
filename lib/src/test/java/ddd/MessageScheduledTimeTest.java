package ddd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class MessageScheduledTimeTest {

    private static final LocalDateTime mockDateTime = LocalDateTime.of(2022, 1, 1, 1, 0, 0);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendNow() {
        // initialize mock
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            MessageScheduledTime scheduledTime = new MessageScheduledTime(mockDateTime);
            // should not throw exception
            assertEquals(mockDateTime, scheduledTime.LocalDateTime());
        }
    }

    @Test
    public void sendAfterNow() {
        // initialize mock
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            var sendTime = mockDateTime.plusHours(2);
            MessageScheduledTime scheduledTime = new MessageScheduledTime(sendTime);
            // should not throw exception
            assertEquals(sendTime, scheduledTime.LocalDateTime());
        }
    }

    @Test
    public void sendBeforeNow() {
        // initialize mock
        try (MockedStatic<LocalDateTime> mock = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(LocalDateTime::now).thenReturn(mockDateTime);

            var sendTime = mockDateTime.minusHours(2);
            assertThrows(InvalidValueException.class, () -> {
                new MessageScheduledTime(sendTime);
            });
        }
    }
}