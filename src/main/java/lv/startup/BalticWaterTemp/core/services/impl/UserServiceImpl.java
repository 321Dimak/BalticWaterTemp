package lv.startup.BalticWaterTemp.core.services.impl;

import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository JpaUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        JpaUserRepository.save(new User(userDto.getEmail(), userDto.getName(), passwordEncoder.encode(userDto.getPassword())));
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
}
