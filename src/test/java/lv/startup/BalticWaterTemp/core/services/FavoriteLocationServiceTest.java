package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.database.FavoriteLocationRepository;
import lv.startup.BalticWaterTemp.core.database.LocationRepository;
import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.dto.FavoriteLocationDTO;
import lv.startup.BalticWaterTemp.core.entity.FavoriteLocation;
import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteLocationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private FavoriteLocationRepository favoriteLocationRepository;
    @InjectMocks
    private FavoriteLocationService favoriteLocationService;

    @Test
    public void testSaveFavoriteLocation_Success() {
        FavoriteLocationDTO dto = new FavoriteLocationDTO("user@example.com", "location123");
        User user = new User();
        Location location = new Location();
        when(userRepository.findByEmail(dto.getUserEmail())).thenReturn(user);
        when(locationRepository.findByLocationId(dto.getLocationId())).thenReturn(location);
        favoriteLocationService.saveFavoriteLocation(dto);
        verify(favoriteLocationRepository).save(any(FavoriteLocation.class));
    }

    @Test
    public void testSaveFavoriteLocationUserNotFound() {
        FavoriteLocationDTO dto = new FavoriteLocationDTO("user@example.com", "location123");
        when(userRepository.findByEmail(dto.getUserEmail())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> favoriteLocationService.saveFavoriteLocation(dto));
        verify(favoriteLocationRepository, never()).save(any(FavoriteLocation.class));
    }

    @Test
    public void testSaveFavoriteLocationLocationNotFound() {
        FavoriteLocationDTO dto = new FavoriteLocationDTO("user@example.com", "location123");
        User user = new User();
        when(userRepository.findByEmail(dto.getUserEmail())).thenReturn(user);
        when(locationRepository.findByLocationId(dto.getLocationId())).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> favoriteLocationService.saveFavoriteLocation(dto));
        verify(favoriteLocationRepository, never()).save(any(FavoriteLocation.class));
    }

    @Test
    public void testDeleteFavoriteLocation() {
        FavoriteLocationDTO dto = new FavoriteLocationDTO("user@example.com", "location123");
        favoriteLocationService.deleteFavoriteLocation(dto);
        verify(favoriteLocationRepository).deleteByUserEmailAndLocationId(dto.getUserEmail(), dto.getLocationId());
    }

    @Test
    public void testDeleteFavoriteLocationNotExist() {
        FavoriteLocationDTO dto = new FavoriteLocationDTO("user@example.com", "location123");
        doThrow(EmptyResultDataAccessException.class)
                .when(favoriteLocationRepository)
                .deleteByUserEmailAndLocationId(dto.getUserEmail(), dto.getLocationId());
        assertThrows(EmptyResultDataAccessException.class, () -> favoriteLocationService.deleteFavoriteLocation(dto));
    }
}