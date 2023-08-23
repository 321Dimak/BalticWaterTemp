package lv.startup.BalticWaterTemp;

import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmail() {
        User mockUser = new User("test@email.com", "TestUser", "password123");
        when(userRepository.findByEmail("test@email.com")).thenReturn(mockUser);

        User found = userRepository.findByEmail("test@email.com");

        assertNotNull(found);
        assertEquals("test@email.com", found.getEmail());
    }
}
