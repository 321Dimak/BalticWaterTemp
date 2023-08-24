package lv.startup.BalticWaterTemp.core.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavoriteLocationDTOTest {

    private FavoriteLocationDTO favoriteLocationDTO;

    @BeforeEach
    public void setUp() {
        favoriteLocationDTO = new FavoriteLocationDTO();
    }

    @Test
    public void testSetAndGetUserEmail() {
        String userEmail = "test@example.com";
        favoriteLocationDTO.setUserEmail(userEmail);
        assertEquals(userEmail, favoriteLocationDTO.getUserEmail());
    }

    @Test
    public void testSetAndGetLocationId() {
        String locationId = "location123";
        favoriteLocationDTO.setLocationId(locationId);
        assertEquals(locationId, favoriteLocationDTO.getLocationId());
    }

}
