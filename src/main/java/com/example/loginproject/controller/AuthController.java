package com.example.loginproject.controller;

import com.example.loginproject.dto.LoginDTO;
import com.example.loginproject.dto.RegisterDTO;
import com.example.loginproject.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("register", new RegisterDTO());
        return modelAndView;
    }

    @PostMapping("/register/new-user")
    public String registerUser(@Valid @ModelAttribute(value = "register") RegisterDTO registerDTO,
                               Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "/register";
        }
        String message = authService.register(registerDTO);
        if (!message.equals("")) {
            model.addAttribute("message", message);
            return "/register";
        }

        return "redirect:/login";
    }

}
