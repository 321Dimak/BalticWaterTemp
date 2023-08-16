package lv.startup.BalticWaterTemp.service;

import lv.startup.BalticWaterTemp.dto.UserDto;
import lv.startup.BalticWaterTemp.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
