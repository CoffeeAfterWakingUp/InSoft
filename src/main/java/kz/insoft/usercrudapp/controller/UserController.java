package kz.insoft.usercrudapp.controller;


import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getUsersView() {
        List<User> users = userService.findAll();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }

    @PostMapping
    public String addUser(@ModelAttribute User user) {
        log.info("New user is about to be created: {}", user);
        boolean created = userService.create(user);
        return created ? "redirect:/users?success" : "redirect:/users?fail";
    }

    // /delete/1
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        return deleted ? "redirect:/users?success" : "redirect:/users?fail";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
        boolean updated = userService.update(user, id);
        return updated ? "redirect:/users?success" : "redirect:/users?fail";
    }



}
