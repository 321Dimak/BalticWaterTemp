package lv.startup.BalticWaterTemp;

import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.security.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsernameSuccess() {
        User mockUser = new User("test@email.com", "TestUser", "password123");
        when(userRepository.findByEmail("test@email.com")).thenReturn(mockUser);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@email.com");

        assertNotNull(userDetails);
        assertEquals("test@email.com", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsernameFailure() {
        when(userRepository.findByEmail("invalid@email.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("invalid@email.com");
        });
    }
}
