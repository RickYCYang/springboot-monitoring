package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.entity.User;

public interface UserService {
    User createUser(User user);

    User getUserById(long id);

    List<User> getAllUSers();

    User updateUser(User user);

    void deleteUser(long id);
}
