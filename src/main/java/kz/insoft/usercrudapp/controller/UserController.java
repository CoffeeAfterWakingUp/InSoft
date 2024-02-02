package kz.insoft.usercrudapp.controller;


import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @ModelAttribute
    public void addAttributes(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
    }

    @GetMapping
    public ModelAndView getUsersView() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute User user, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Errors: {}", errors);
            return "/users";
        }
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

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_user");
        User user = userService.findById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute User user, Errors errors) {
        if (errors.hasErrors()) {
            return "edit_user";
        }
        boolean updated = userService.update(user, id);
        return "redirect:/users/edit/" + id  +"?success";
    }



}
