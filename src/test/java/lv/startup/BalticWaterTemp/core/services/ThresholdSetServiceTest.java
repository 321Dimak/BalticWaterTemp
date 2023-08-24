package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.database.LocationRepository;
import lv.startup.BalticWaterTemp.core.database.NotificationRepository;
import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.dto.LevelThresholdDTO;
import lv.startup.BalticWaterTemp.core.dto.TempThresholdDTO;
import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.entity.Notification;
import lv.startup.BalticWaterTemp.core.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ThresholdSetServiceTest {

    @InjectMocks
    ThresholdSetService thresholdSetService;

    @Mock
    NotificationRepository notificationRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    LocationRepository locationRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetTempThreshold() {
        // Given
        TempThresholdDTO dto = new TempThresholdDTO();
        User mockUser = new User();
        Location mockLocation = new Location();

        when(userRepository.findByEmail(dto.getUserEmail())).thenReturn(mockUser);
        when(locationRepository.findByLocationId(dto.getLocationId())).thenReturn(mockLocation);
        when(notificationRepository.findByUserEmailAndLocationId(dto.getUserEmail(), dto.getLocationId())).thenReturn(null);

        thresholdSetService.setTempThreshold(dto);

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    public void testSetLevelThreshold() {
        LevelThresholdDTO dto = new LevelThresholdDTO();
        User mockUser = new User();
        Location mockLocation = new Location();

        when(userRepository.findByEmail(dto.getUserEmail())).thenReturn(mockUser);
        when(locationRepository.findByLocationId(dto.getLocationId())).thenReturn(mockLocation);
        when(notificationRepository.findByUserEmailAndLocationId(dto.getUserEmail(), dto.getLocationId())).thenReturn(null);

        thresholdSetService.setLevelThreshold(dto);

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
}
