package net.javaguides.springboot.controller;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.dto.UserDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User")
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    // @Autowired
    private UserService userService;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());



    // http://localhost:8080/api/users/hello
    @GetMapping("hello")
    public String hello() {
        logger.info("Hello, World!");
        return "Hello, World!";
    }

    // http://localhost:8080/api/users
    @Operation(summary = "Create a new User", description = "Create a new User")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/users/1
    @Operation(summary = "Get User by Id", description = "Get User by Id")
    @ApiResponse(responseCode = "200", description = "User found successfully")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // http://localhost:8080/api/users
    @Operation(summary = "Get All Users", description = "Get All Users")
    @ApiResponse(responseCode = "200", description = "All Users found successfully")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUSers();
        logger.info("Get all users");
        return ResponseEntity.ok(users);
    }

    // http://localhost:8080/api/users/1
    @Operation(summary = "Update User by Id", description = "Update User by Id")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id,
            @Valid @RequestBody UserDto user) {
        user.setId(id);
        UserDto updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete User by Id", description = "Delete User by Id")
    @ApiResponse
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User sucessfully deleted!");
    }

    @GetMapping("id/{id}")
    public UserDto getUserByIdSql(@PathVariable long id) {
        return userService.getUserByIdSql(id);
    }

    @GetMapping("email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmailSql(email);
    }


    // a specific exception handler for the current controller
    // @ExceptionHandler(ResourceNotFoundException.class)
    // public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
    // ResourceNotFoundException exception, WebRequest webRequest) {
    // ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
    // webRequest.getDescription(false), "USER_NOT_FOUND");
    // return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    // }

}
