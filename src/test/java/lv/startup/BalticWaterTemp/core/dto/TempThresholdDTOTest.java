package lv.startup.BalticWaterTemp.core.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempThresholdDTOTest {

    private TempThresholdDTO tempThresholdDTO;

    @BeforeEach
    public void setUp() {
        tempThresholdDTO = new TempThresholdDTO();
    }

    @Test
    public void testSetAndGetUserEmail() {
        String userEmail = "test@example.com";
        tempThresholdDTO.setUserEmail(userEmail);
        assertEquals(userEmail, tempThresholdDTO.getUserEmail());
    }

    @Test
    public void testSetAndGetLocationId() {
        String locationId = "location123";
        tempThresholdDTO.setLocationId(locationId);
        assertEquals(locationId, tempThresholdDTO.getLocationId());
    }

    @Test
    public void testSetAndGetTemperature() {
        double temperature = 20.5;
        tempThresholdDTO.setTemperature(temperature);
        assertEquals(temperature, tempThresholdDTO.getTemperature());
    }

//    @Test
//    public void testSetAndGetLevel() {
//        double level = 5.5;
//        tempThresholdDTO.setLevel(level);
//        assertEquals(level, tempThresholdDTO.getLevel());
//    }
}
