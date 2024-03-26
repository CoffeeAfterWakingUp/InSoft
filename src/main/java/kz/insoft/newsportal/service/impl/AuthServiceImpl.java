package kz.insoft.newsportal.service.impl;

import kz.insoft.newsportal.dto.RegisterDTO;
import kz.insoft.newsportal.entity.User;
import kz.insoft.newsportal.service.AuthService;
import kz.insoft.newsportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String registerUser(RegisterDTO registerDTO) {
        if (registerDTO != null) {
            String email = registerDTO.getEmail();
            String password = registerDTO.getPassword();
            String rePassword = registerDTO.getRePassword();

            User user = userService.getByEmail(email);

            if (user != null) {
                return "User with this email already exists!";
            }

            if (!password.equals(rePassword)) {
                return "RePassword and Password must be equal!";
            }

            user = new User(registerDTO.getFirstName(), registerDTO.getLastName(), email, passwordEncoder.encode(password));
            return userService.create(user) ? "" : "Oopps, something happened while registering!";
        }
        return "";
    }
}
