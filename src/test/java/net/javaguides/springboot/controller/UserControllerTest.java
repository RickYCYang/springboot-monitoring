package net.javaguides.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(UserController.class)
class UserControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Autowired
        private ObjectMapper objectMapper; // For JSON serialization/deserialization, convert DTO to
                                           // JSON or JSON to DTO

        @Captor
        private ArgumentCaptor<UserDto> userDtoCaptor;

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
        void createUser_shouldReturn201Created() throws Exception {
                // Arrange
                // when(userService.createUser(userDto)).thenReturn(userDto);
                when(userService.createUser(org.mockito.ArgumentMatchers.any(UserDto.class)))
                                .thenReturn(userDto);
                ArgumentCaptor<UserDto> captor = ArgumentCaptor.forClass(UserDto.class);

                // Act
                // prepare the request to create a user
                MvcResult result = mockMvc
                                .perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(userDto)))
                                .andReturn();
                String json = result.getResponse().getContentAsString();
                int status = result.getResponse().getStatus();

                // Assert
                assertEquals(201, status);
                assertNotNull(json);
                // Deserialize the JSON response back to UserDto, and verify the values
                UserDto responseUser = objectMapper.readValue(json, UserDto.class);
                assertEquals(userDto.getFirstName(), responseUser.getFirstName());
                assertEquals(user.getLastName(), responseUser.getLastName());
                assertEquals(userDto.getEmail(), responseUser.getEmail());

                // Verify that the userService.createUser method was called with the correct UserDto
                verify(userService).createUser(org.mockito.ArgumentMatchers.any(UserDto.class));

                // Verify that the captured UserDto matches the expected values
                verify(userService).createUser(captor.capture());
                UserDto capturedDto = captor.getValue();
                assertEquals(userDto.getFirstName(), capturedDto.getFirstName());
                assertEquals(user.getLastName(), capturedDto.getLastName());
                assertEquals(userDto.getEmail(), capturedDto.getEmail());

                // Verify that the userService.createUser method was called with the correct UserDto
                verify(userService).createUser(
                                argThat(dto -> dto.getFirstName().equals(userDto.getFirstName())
                                                && dto.getLastName().equals(userDto.getLastName())
                                                && dto.getEmail().equals(userDto.getEmail())));
        }
}
