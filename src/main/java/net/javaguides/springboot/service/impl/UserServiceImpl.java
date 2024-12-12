package net.javaguides.springboot.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    // @Autowired
    private UserRepository userRepository;

    // @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA entity
        // User user = UserMapper.mapToUser(userDto); // convert to DTO by ourselves
        // User user = modelMapper.map(userDto, User.class); // convert to DTO by ModelMapper lib
        User user = AutoUserMapper.MAPPER.mapToUser(userDto); // convert to DTO by MapStruct lib

        User savedUser = userRepository.save(user);

        // Convert User JPA entity into UserDto
        // return UserMapper.mapToUserDto(savedUser); // convert to JPA entity by ourselves
        // return modelMapper.map(savedUser, UserDto.class); // convert to DTO by ModelMapper lib
        return AutoUserMapper.MAPPER.mapToUserDto(savedUser); // convert to DTO by MapStruct lib
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id).get();
        // return UserMapper.mapToUserDto(user); // convert to DTO by ourselves
        // return modelMapper.map(user, UserDto.class); // convert to DTO by ModelMapper lib
        return AutoUserMapper.MAPPER.mapToUserDto(user); // convert to DTO by MapStruct lib
    }

    @Override
    public List<UserDto> getAllUSers() {
        List<User> users = userRepository.findAll();
        // convert to DTO by ourselves
        // return users.stream().map(UserMapper::mapToUserDto).toList();

        // convert to DTO by ModelMapper lib
        // return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();

        // convert to DTO by MapStruct lib
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDto(updatedUser); // convert to DTO by ourselves
        // return modelMapper.map(updatedUser, UserDto.class); // convert to DTO by ModelMapper lib
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser); // convert to DTO by MapStruct lib
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
