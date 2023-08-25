package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FavoriteLocationTest {

    private FavoriteLocation favoriteLocation;

    @BeforeEach
    public void setUp() {
        favoriteLocation = new FavoriteLocation();
    }

    @Test
    public void testSetAndGetUserEmail() {
        String userEmail = "test@example.com";
        favoriteLocation.setId(new FavoriteLocationKey(userEmail, "location123"));
        assertEquals(userEmail, favoriteLocation.getId().getUserEmail());
    }

    @Test
    public void testSetAndGetLocationId() {
        String userEmail = "test@example.com";
        String locationId = "location123";
        favoriteLocation.setId(new FavoriteLocationKey(userEmail, "location123"));
        assertEquals(locationId, favoriteLocation.getId().getLocationId());
    }

    @Test
    public void testSetAndGetUser() {
        User user = mock(User.class);
        favoriteLocation.setUser(user);
        assertEquals(user, favoriteLocation.getUser());
    }

    @Test
    public void testSetAndGetLocation() {
        Location location = mock(Location.class);
        favoriteLocation.setLocation(location);
        assertEquals(location, favoriteLocation.getLocation());
    }
}
