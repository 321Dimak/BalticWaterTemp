package lv.startup.BalticWaterTemp;

import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import lv.startup.BalticWaterTemp.core.services.UserService;
import lv.startup.BalticWaterTemp.web_ui.controllers.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void home() {
        String view = authController.home();
        assertEquals("index", view);
    }

    @Test
    void loginForm() {
        String view = authController.loginForm();
        assertEquals("login", view);
    }

    @Test
    void showRegistrationForm() {
        UserDto user = new UserDto();
        String view = authController.showRegistrationForm(model);
        assertEquals("register", view);
        verify(model).addAttribute("user", user);
    }

    @Test
    void registrationSuccess() {
        UserDto user = new UserDto();
        user.setEmail("test@test.com");
        when(userService.findByEmail(user.getEmail())).thenReturn(null);
        when(result.hasErrors()).thenReturn(false);
        String view = authController.registration(user, result, model);
        assertEquals("redirect:/register?success", view);
        verify(userService).saveUser(user);
    }

    @Test
    void registrationFailure() {
        UserDto user = new UserDto();
        user.setEmail("test@test.com");
        User existing = new User();
        existing.setEmail(user.getEmail());
        when(userService.findByEmail(user.getEmail())).thenReturn(existing);
        when(result.hasErrors()).thenReturn(true);
        String view = authController.registration(user, result, model);
        assertEquals("register", view);
        verify(result).rejectValue("email", null, "There is already an account registered with that email");
        verify(model).addAttribute("user", user);
    }

    @Test
    void listRegisteredUsers() {
        List<UserDto> users = Arrays.asList(new UserDto(), new UserDto());
        when(userService.findAllUsers()).thenReturn(users);
        String view = authController.listRegisteredUsers(model);
        assertEquals("users", view);
        verify(model).addAttribute("users", users);
    }
}