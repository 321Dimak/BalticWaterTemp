package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationRepositoryTest {

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByLocationId() {
        String testLocationId = "location123";
        Location mockLocation = new Location();
        mockLocation.setId(testLocationId);

        when(locationRepository.findByLocationId(testLocationId)).thenReturn(mockLocation);

        Location location = locationRepository.findByLocationId(testLocationId);
        assertEquals(testLocationId, location.getId());
    }
}
