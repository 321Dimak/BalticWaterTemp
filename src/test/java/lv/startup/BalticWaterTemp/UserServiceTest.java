package lv.startup.BalticWaterTemp;

import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import lv.startup.BalticWaterTemp.core.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@test.com";
        User user = new User();
        when(userRepository.findByEmail(email)).thenReturn(user);
        User result = userService.findByEmail(email);
        assertEquals(user, result);
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(users);
        List<UserDto> result = userService.findAllUsers();
        assertEquals(users.size(), result.size());
    }
}
