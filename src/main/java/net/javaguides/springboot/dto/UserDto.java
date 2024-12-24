package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "User Data Transfer Object")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private long id;

    @Schema(description = "User's first name", example = "Rick")
    // user's first name should not be null or empty
    @NotEmpty(message = "user's first name should not be null or empty")
    private String firstName;

    @Schema(description = "User's last name", example = "Yang")
    // user's last name should not be null or empty
    @NotEmpty(message = "user's lastName name should not be null or empty")
    private String lastName;

    @Schema(description = "User's email", example = "rickyang2910@gmail.com")
    // user's email should not be null or empty
    // email address should be valid
    @NotEmpty(message = "user's email name should not be null or empty")
    @Email(message = "email address should be valid")
    private String email;
}
