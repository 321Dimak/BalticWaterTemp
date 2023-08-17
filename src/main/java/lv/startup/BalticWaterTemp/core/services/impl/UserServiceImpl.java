package lv.startup.BalticWaterTemp.core.services.impl;

import lv.startup.BalticWaterTemp.core.database.JpaUserRepository;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private JpaUserRepository JpaUserRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(JpaUserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.JpaUserRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        JpaUserRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return JpaUserRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = JpaUserRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setName(name[0]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
