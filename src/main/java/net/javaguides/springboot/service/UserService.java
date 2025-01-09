package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(long id);

    List<UserDto> getAllUSers();

    UserDto updateUser(UserDto user);

    void deleteUser(long id);

    UserDto getUserByIdSql(long id);

    UserDto getUserByEmailSql(String email);
}
