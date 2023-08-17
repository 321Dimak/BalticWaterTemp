package lv.startup.BalticWaterTemp.core.services.impl;

import lombok.RequiredArgsConstructor;
import lv.startup.BalticWaterTemp.core.database.JpaUserRepository;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JpaUserRepository JpaUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        JpaUserRepository.save(new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword())));
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
        return new UserDto(user.getName(), user.getEmail(), null);
    }
}
