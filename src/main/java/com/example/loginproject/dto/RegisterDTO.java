package com.example.loginproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "First Name must be not blank!")
    private String firstName;

    @NotBlank(message = "Last Name must not be blank!")
    private String lastName;

    @Email(message = "Email must be in right format!")
    @NotBlank(message = "Email must not be blank!")
    private String email;

    @Size(min = 8, max = 30, message = "Password's length must be around 8 and 30!")
    @NotBlank(message = "Password must not be blank!")
    private String password;

    @NotBlank(message = "RePassword must not be blank!")
    private String rePassword;
}
