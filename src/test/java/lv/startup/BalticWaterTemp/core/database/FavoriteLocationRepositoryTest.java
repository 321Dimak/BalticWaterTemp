package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class FavoriteLocationRepositoryTest {

    @Mock
    private FavoriteLocationRepository favoriteLocationRepository;

    @Test
    public void testDeleteByUserEmailAndLocationId() {
        String userEmail = "user@example.com";
        String locationId = "location123";
        favoriteLocationRepository.deleteByUserEmailAndLocationId(userEmail, locationId);
        assertFalse(favoriteLocationRepository.existsById(userEmail));
    }
}