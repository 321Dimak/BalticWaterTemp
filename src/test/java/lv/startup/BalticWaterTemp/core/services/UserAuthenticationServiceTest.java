package lv.startup.BalticWaterTemp.core.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserAuthenticationServiceTest {

    @InjectMocks
    private UserAuthenticationService userAuthenticationService;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLoggedInUserEmailWithAuthenticatedUser() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("test@email.com");
        SecurityContextHolder.setContext(securityContext);

        String email = userAuthenticationService.getLoggedInUserEmail();

        assertEquals("test@email.com", email);
    }

    @Test
    void getLoggedInUserEmailWithNoAuthenticatedUser() {
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        String email = userAuthenticationService.getLoggedInUserEmail();

        assertNull(email);
    }

}