package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Notification;
import lv.startup.BalticWaterTemp.core.entity.NotificationKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationRepositoryTest {

    @Mock
    private NotificationRepository notificationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUserEmailAndLocationId() {
        String testUserEmail = "test@example.com";
        String testLocationId = "location123";
        Notification mockNotification = new Notification();
        NotificationKey notificationKey = new NotificationKey(testUserEmail, testLocationId);
        mockNotification.setId(notificationKey);

        when(notificationRepository.findByUserEmailAndLocationId(testUserEmail, testLocationId))
                .thenReturn(mockNotification);

        Notification notification = notificationRepository.findByUserEmailAndLocationId(testUserEmail, testLocationId);
        assertEquals(testUserEmail, notification.getId().getUserEmail());
        assertEquals(testLocationId, notification.getId().getLocationId());
    }
}
