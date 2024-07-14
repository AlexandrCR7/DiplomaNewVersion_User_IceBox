package ru.gb.userIceBoxCheck.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.gb.userIceBoxCheck.model.User;
import ru.gb.userIceBoxCheck.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

//    public User addUser(String name, List<String> products){
//        User user = new User();
//        user.setName(name);
//        user.setProducts(products);
//        return user;
//    }
}
