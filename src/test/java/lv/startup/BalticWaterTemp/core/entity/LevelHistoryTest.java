package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class LevelHistoryTest {

    private LevelHistory levelHistory;

    @BeforeEach
    public void setUp() {
        levelHistory = new LevelHistory();
    }

    @Test
    public void testSetAndGetLocationId() {
        String locationId = "location123";
        levelHistory.setLocationId(locationId);
        assertEquals(locationId, levelHistory.getLocationId());
    }

    @Test
    public void testSetAndGetOneDayAgo() {
        double oneDayAgo = 5.5;
        levelHistory.setOneDayAgo(oneDayAgo);
        assertEquals(oneDayAgo, levelHistory.getOneDayAgo());
    }

    @Test
    public void testSetAndGetLocation() {
        Location location = mock(Location.class);
        levelHistory.setLocation(location);
        assertEquals(location, levelHistory.getLocation());
    }
}
