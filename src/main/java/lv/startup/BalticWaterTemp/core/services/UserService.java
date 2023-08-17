package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
