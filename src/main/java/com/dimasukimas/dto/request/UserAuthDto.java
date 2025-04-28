package com.dimasukimas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserAuthDto(

        @NotBlank(message = "Login must not be blank")
        @Size(min = 3, max = 20, message = "Login must be between 3 and 20 characters")
        @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]{3,20}$", message = "Login can contain only latin letters and digits")
        String login,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        @Pattern(regexp = "^(?=\\S+$)(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,20}$",
                message = "Password must be 6-20 characters long, contain at least one uppercase letter, one lowercase letter and one digit")
        String password
) {
}
