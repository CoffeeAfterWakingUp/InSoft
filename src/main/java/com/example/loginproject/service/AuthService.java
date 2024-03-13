package com.example.loginproject.service;

import com.example.loginproject.dto.RegisterDTO;
import com.example.loginproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterDTO registerDTO) {
        if (registerDTO != null) {
            String email = registerDTO.getEmail();
            String password = registerDTO.getPassword();
            String rePassword = registerDTO.getRePassword();

            User user = userService.getByEmail(email);
            if (user != null) {
                return "User with this email has already registered!";
            }
            if (!password.equals(rePassword)) {
                return "Password and rePassword are different!";
            }
            user = new User(registerDTO.getFirstName(),
                    registerDTO.getLastName(),
                    registerDTO.getEmail(),
                    passwordEncoder.encode(password));
            userService.create(user);
            return "";
        }
        return "Oops!";
    }
}
