package lv.startup.BalticWaterTemp.core.services.impl;

import jakarta.mail.internet.MimeMessage;
import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.services.UserService;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository JpaUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void saveUser(UserDto userDto) {
        JpaUserRepository.save(new User(userDto.getEmail(), userDto.getName(), passwordEncoder.encode(userDto.getPassword())));
        sendWelcomeEmail(userDto);
    }

    @Override
    public User findByEmail(String email) {
        return JpaUserRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = JpaUserRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        return new UserDto(user.getEmail(), user.getUsername(), null);
    }
    private void sendWelcomeEmail(UserDto userDto) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<h3>Welcome " + userDto.getName() + "!</h3><p>Thank you for registering at BalticWaterTemp.</p>";
            helper.setText(htmlMsg, true);  // set to true to enable HTML formatting
            helper.setTo(userDto.getEmail());
            helper.setSubject("Welcome to BalticWaterTemp");
            helper.setFrom("balticwatertemperature@gmail.com");  // replace with your sending email
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            // Log the exception for debugging. You might want to handle this differently based on your application's needs.
            e.printStackTrace();
        }
    }
}
