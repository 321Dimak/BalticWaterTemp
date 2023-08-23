package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
