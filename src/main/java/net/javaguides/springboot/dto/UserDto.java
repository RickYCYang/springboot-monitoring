package net.javaguides.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private long id;

    // user's first name should not be null or empty
    @NotEmpty(message = "user's first name should not be null or empty")
    private String firstName;

    // user's last name should not be null or empty
    @NotEmpty(message = "user's lastName name should not be null or empty")
    private String lastName;

    // user's email should not be null or empty
    // email address should be valid
    @NotEmpty(message = "user's email name should not be null or empty")
    @Email(message = "email address should be valid")
    private String email;
}
