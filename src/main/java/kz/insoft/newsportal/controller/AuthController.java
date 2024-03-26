package kz.insoft.newsportal.controller;

import jakarta.validation.Valid;
import kz.insoft.newsportal.dto.RegisterDTO;
import kz.insoft.newsportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(name = "")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("register", new RegisterDTO());
        return modelAndView;
    }

    @PostMapping("/register/new-user")
    public String newUser(@ModelAttribute(value = "register") @Valid RegisterDTO registerDTO,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        String msg = authService.registerUser(registerDTO);
        if (msg != null && msg.isEmpty()) {
            msg = "User is registered successfully!";
        }

        model.addAttribute("message", msg);
        return "register";
    }
}
