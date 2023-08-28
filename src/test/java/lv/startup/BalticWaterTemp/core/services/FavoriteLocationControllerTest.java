package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.dto.FavoriteLocationDTO;
import lv.startup.BalticWaterTemp.web_ui.controllers.FavoriteLocationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FavoriteLocationControllerTest {

    @InjectMocks
    private FavoriteLocationController favoriteLocationController;

    @Mock
    private FavoriteLocationService favoriteLocationService;

    @Mock
    private UserAuthenticationService userAuthenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFavoriteLocation() {
        String userEmail = "test@example.com";
        String locationId = "location123";

        when(userAuthenticationService.getLoggedInUserEmail()).thenReturn(userEmail);

        ResponseEntity<String> response = favoriteLocationController.saveFavoriteLocation(locationId);

        verify(userAuthenticationService).getLoggedInUserEmail();
        verify(favoriteLocationService).saveFavoriteLocation(new FavoriteLocationDTO(userEmail, locationId));
        assertEquals("Favorite location saved.", response.getBody());
    }
}
