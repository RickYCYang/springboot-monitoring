package net.javaguides.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.modelmapper.ModelMapper;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john@example.com");

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
    }

    @Test
    void createUser_shouldReturnUserDto_whenEmailNotExists() {
        // Arrange
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDto result = userService.createUser(userDto);

        // Assert
        assertNotNull(result);
        assertEquals(userDto.getFirstName(), result.getFirstName());
        assertEquals(userDto.getLastName(), result.getLastName());
        assertEquals(userDto.getEmail(), result.getEmail());
        verify(userRepository).findByEmail(userDto.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(userDto));
        verify(userRepository).findByEmail(userDto.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserById_shouldReturnUserDto_whenUserExists() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        UserDto result = userService.getUserById(user.getId());

        // Assert
        assertNotNull(result);
        assertEquals(userDto.getFirstName(), result.getFirstName());
        assertEquals(userDto.getLastName(), result.getLastName());
        assertEquals(userDto.getEmail(), result.getEmail());
        verify(userRepository).findById(user.getId());
    }

    @Test
    void getUserById_shouldThrowException_whenUserDoesNotExist() {
        // Arrange
        Long userId = user.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository).findById(user.getId());
    }
}
