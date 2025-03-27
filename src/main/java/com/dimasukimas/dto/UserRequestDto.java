package com.dimasukimas.dto;


import com.dimasukimas.util.PasswordMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@PasswordMatch
public record UserRequestDto(

        @NotBlank(message = "Login must not be blank")
        @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{3,20}$", message = "Login can contain only latin letters, digits and special character (_ or -) ")
        String login,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        @Pattern(regexp = "^(?=\\S+$)(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,20}$",
                message = "Password must be 8-20 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character (!@#$%^&*).")
        String password,

        String repeatPassword

) {
}


