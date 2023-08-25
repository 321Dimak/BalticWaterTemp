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
    public void testSetAndGetLevelHistory() {
        LevelHistory levelHistory = mock(LevelHistory.class);
        location.setLevelHistory(levelHistory);
        assertEquals(levelHistory, location.getLevelHistory());
    }
}
