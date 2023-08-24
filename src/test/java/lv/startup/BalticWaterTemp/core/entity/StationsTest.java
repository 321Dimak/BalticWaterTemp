package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StationsTest {

    private Stations stations;

    @BeforeEach
    public void setUp() {
        stations = new Stations();
    }

    @Test
    public void testSetAndGetId() {
        String id = "station123";
        stations.setId(id);
        assertEquals(id, stations.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "Test Station";
        stations.setName(name);
        assertEquals(name, stations.getName());
    }
}
