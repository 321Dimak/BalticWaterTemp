package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class NotificationTest {

    private Notification notification;

    @BeforeEach
    public void setUp() {
        notification = new Notification();
    }

    @Test
    public void testSetAndGetUserEmail() {
        String userEmail = "test@example.com";
        notification.setUserEmail(userEmail);
        assertEquals(userEmail, notification.getUserEmail());
    }

    @Test
    public void testSetAndGetLocationId() {
        String locationId = "location123";
        notification.setLocationId(locationId);
        assertEquals(locationId, notification.getLocationId());
    }

    @Test
    public void testSetAndGetTemperature() {
        double temperature = 20.5;
        notification.setTemperature(temperature);
        assertEquals(temperature, notification.getTemperature());
    }

    @Test
    public void testSetAndGetLevel() {
        double level = 5.5;
        notification.setLevel(level);
        assertEquals(level, notification.getLevel());
    }

    @Test
    public void testSetAndGetUser() {
        User user = mock(User.class);
        notification.setUser(user);
        assertEquals(user, notification.getUser());
    }

    @Test
    public void testSetAndGetLocation() {
        Location location = mock(Location.class);
        notification.setLocation(location);
        assertEquals(location, notification.getLocation());
    }
}