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

    /**
     * Стандартный метод CRUD
     * @return возвращает список юзеров
     */
    public List<User> getAll(){
        return userRepository.findAll();
    }

    /**
     * Стандартный метод CRUD
     * @param id - принимает на вход id искомого юзера
     * @return - возвращает искомого юзера
     * При отсутствии в репозитории юзера по запрашиваемому id обрабатывает ошибку
     */
    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User by %d not found".formatted(id)));
    }

    /**
     * Стандартный метод CRUD
     * @param id - принимает на вход id искомого юзера
     * Удаляет искомого юзера
     */
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    /**
     * Стандартный метод CRUD
     * @param user - принимает юзера на вход и сохраняет его
     */
    public void saveUser(User user){
        userRepository.save(user);
    }

}
