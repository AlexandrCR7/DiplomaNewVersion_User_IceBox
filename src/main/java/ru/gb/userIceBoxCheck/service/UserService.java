package ru.gb.userIceBoxCheck.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.gb.userIceBoxCheck.exeptions.UserNotFoundException;
import ru.gb.userIceBoxCheck.model.User;
import ru.gb.userIceBoxCheck.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Getter
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User by %d not found".formatted(id)));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

}
