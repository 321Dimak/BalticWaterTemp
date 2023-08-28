package lv.startup.BalticWaterTemp.core.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelThresholdDTOTest {

    private LevelThresholdDTO levelThresholdDTO;

    @BeforeEach
    public void setUp() {
        levelThresholdDTO = new LevelThresholdDTO();
    }

    @Test
    public void testSetAndGetUserEmail() {
        String userEmail = "test@example.com";
        levelThresholdDTO.setUserEmail(userEmail);
        assertEquals(userEmail, levelThresholdDTO.getUserEmail());
    }

    @Test
    public void testSetAndGetLocationId() {
        String locationId = "location123";
        levelThresholdDTO.setLocationId(locationId);
        assertEquals(locationId, levelThresholdDTO.getLocationId());
    }

    @Test
    public void testSetAndGetLevel() {
        double level = 5.5;
        levelThresholdDTO.setLevel(level);
        assertEquals(level, levelThresholdDTO.getLevel());
    }

}
