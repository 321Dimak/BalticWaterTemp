package lv.startup.BalticWaterTemp;

import lv.startup.BalticWaterTemp.core.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("test@email.com", "TestUser", "password123");
    }

    @Test
    public void testConstructor() {
        assertNotNull(user);
        assertEquals("test@email.com", user.getEmail());
        assertEquals("TestUser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGettersAndSetters() {
        user.setEmail("new@email.com");
        user.setUsername("NewUser");
        user.setPassword("newpassword123");

        assertEquals("new@email.com", user.getEmail());
        assertEquals("NewUser", user.getUsername());
        assertEquals("newpassword123", user.getPassword());
    }
}
