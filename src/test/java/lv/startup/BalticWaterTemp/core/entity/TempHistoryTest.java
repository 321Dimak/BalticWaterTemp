package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TempHistoryTest {

    private TempHistory tempHistory;

    @BeforeEach
    public void setUp() {
        tempHistory = new TempHistory();
    }

    @Test
    public void testSetAndGetLocationId() {
        String locationId = "location123";
        tempHistory.setLocationId(locationId);
        assertEquals(locationId, tempHistory.getLocationId());
    }

    @Test
    public void testSetAndGetOneDayAgo() {
        double oneDayAgo = 20.5;
        tempHistory.setOneDayAgo(oneDayAgo);
        assertEquals(oneDayAgo, tempHistory.getOneDayAgo());
    }

    @Test
    public void testSetAndGetTwoDayAgo() {
        double twoDayAgo = 19.5;
        tempHistory.setTwoDayAgo(twoDayAgo);
        assertEquals(twoDayAgo, tempHistory.getTwoDayAgo());
    }
    @Test
    public void testSetAndGetLocation() {
        Location location = mock(Location.class);
        tempHistory.setLocation(location);
        assertEquals(location, tempHistory.getLocation());
    }
}
