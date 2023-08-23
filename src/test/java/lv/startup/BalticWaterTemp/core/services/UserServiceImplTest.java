package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.dto.UserDto;
import lv.startup.BalticWaterTemp.core.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MimeMessage mimeMessage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        UserDto userDto = new UserDto("TestName", "test@email.com", "testPassword");
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        userService.saveUser(userDto);

        verify(passwordEncoder).encode(userDto.getPassword());
        verify(userRepository).save(any(User.class));
        verify(mailSender).send(mimeMessage);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@email.com";
        User user = new User("TestName", email, "testPassword");
        when(userRepository.findByEmail(email)).thenReturn(user);

        userService.findByEmail(email);

        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testFindAllUsers() {
        userService.findAllUsers();

        verify(userRepository).findAll();
    }
}
