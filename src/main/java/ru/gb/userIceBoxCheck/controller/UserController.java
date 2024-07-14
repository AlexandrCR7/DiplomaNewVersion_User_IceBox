package ru.gb.userIceBoxCheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.userIceBoxCheck.model.User;
import ru.gb.userIceBoxCheck.repository.UserRepository;
import ru.gb.userIceBoxCheck.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    /**
     * почистить контроллер, сделать так же как и в инргредиентах
     */
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User findById(Long id){
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        userService.deleteUser(id);
        return "User has been deleted";
    }
}
