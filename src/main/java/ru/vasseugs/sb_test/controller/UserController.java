package ru.vasseugs.sb_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasseugs.sb_test.domain.entity.UserEntity;
import ru.vasseugs.sb_test.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("newUser", new UserEntity());
        return "newUser";
    }

    @PostMapping()
    public String saveUser(@Valid @ModelAttribute("newUser") UserEntity user,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "newUser";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userService.getUser(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "noSuchUser";
        }
        return "editUser";
    }

    @PatchMapping
    public String saveEditedUser(@Valid @ModelAttribute("user") UserEntity user,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "editUser";
        }

        try {
            userService.editUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "noSuchUser";
        }
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
