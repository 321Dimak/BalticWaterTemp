package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class LocationTest {

    private Location location;

    @BeforeEach
    public void setUp() {
        location = new Location();
    }

    @Test
    public void testSetAndGetId() {
        String id = "loc123";
        location.setId(id);
        assertEquals(id, location.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "Test Location";
        location.setName(name);
        assertEquals(name, location.getName());
    }

    @Test
    public void testSetAndGetTemperature() {
        double temperature = 20.5;
        location.setTemperature(temperature);
        assertEquals(temperature, location.getTemperature());
    }

    @Test
    public void testSetAndGetLevel() {
        double level = 5.5;
        location.setLevel(level);
        assertEquals(level, location.getLevel());
    }

    @Test
    public void testSetAndGetLevelHistory() {
        LevelHistory levelHistory = mock(LevelHistory.class);
        location.setLevelHistory(levelHistory);
        assertEquals(levelHistory, location.getLevelHistory());
    }
}
