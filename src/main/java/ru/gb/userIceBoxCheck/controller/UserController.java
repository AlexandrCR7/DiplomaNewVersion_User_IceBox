package ru.gb.userIceBoxCheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.model.User;
import ru.gb.userIceBoxCheck.repository.UserRepository;
import ru.gb.userIceBoxCheck.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(Long id){
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        userService.deleteUser(id);
        return "User has been deleted";
    }

    @GetMapping("/newUser")
    public String newUser(Model model){
        model.addAttribute("newUser", userRepository.findAll());
        return "User has been created";
    }

    @PostMapping("/newUser")
    public String newUser(@RequestBody User user, Model model){
        userService.saveUser(userService.addUser(user.getProducts()));
        model.addAttribute("newUser", userService.getAll());
        return "User has been created";
    }

}
