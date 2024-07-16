package ru.gb.userIceBoxCheck.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.userIceBoxCheck.client.RecipeClient;
import ru.gb.userIceBoxCheck.exeptions.IceBoxIsNullException;
import ru.gb.userIceBoxCheck.exeptions.IceBoxNotFoundException;
import ru.gb.userIceBoxCheck.exeptions.UserNotFoundException;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.repository.IceBoxRepository;
import ru.gb.userIceBoxCheck.request.IngredientRequest;
import ru.gb.userIceBoxCheck.request.RecipeRequest;

import java.util.*;

@Service
@AllArgsConstructor
@Getter
public class IseBoxService {

    private final RecipeClient recipeClient;

    private final IceBoxRepository iceBoxRepository;

    /**
     * Стандартный метод CRUD
     * @return возвращает список холодильников
     */
    public List<IceBox> findAll(){
        return iceBoxRepository.findAll();
    }

    /**
     * Стандартный метод CRUD
     * @param id - принимает на вход id искомого холодильника
     * @return - возвращает искомый холодильник
     * При отсутствии в репозитории холодильника по запрашиваемому id обрабатывает ошибку
     */
    public IceBox findById(Long id){
        return iceBoxRepository.findById(id).orElseThrow(() ->
                new IceBoxNotFoundException("IceBox by %d not found".formatted(id)));
    }

    /**
     * Стандартный метод CRUD
     * @param id - принимает на вход id искомого холодильника
     * Удаляет искомый холодильник
     */
    public void deleteById(Long id){
        iceBoxRepository.deleteById(id);
    }

    /**
     * Стандартный метод CRUD
     * @param iceBox - принимает холодильник на вход и сохраняет его
     */
    public void saveIseBox(IceBox iceBox){
        iceBoxRepository.save(iceBox);
    }

    /**
     * Стандартный метод CRUD по созданию объекта
     * @param ingredients - принимает на вход параметр,
     * который будем менять в пустом холодильнике
     * @return - возвращаем готовый объект
     */
    public IceBox addIceBox(String ingredients){
        IceBox iceBox = new IceBox();
        iceBox.setIngredients(ingredients);
        return iceBox;
    }

    /**
     * Тестовый метод
     * @return возвращает все ингредиенты, которые существуют в другом микросервисе
     */
    @GetMapping("/test")
    public List<IngredientRequest> testing(){
        return recipeClient.getIngredients();
    }

    /**
     * Метод создан для заполнения холодильника ингредиентами, которые передаются от другого микросервиса
     * @param id - передает в метод id холодильника, который мы хотим найти
     * @return возвращает объект класса IceBox заполненный ингредиентами содержащимися в холодильнике
     */
    public IceBox fillIceBox(Long id) {
        List<IngredientRequest> ingredientRequests = recipeClient.getIngredients();
        IceBox iceBox = iceBoxRepository.findById(id).orElseThrow();
        Set<String> setList = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ingredientRequests.size(); i++) {
            setList.add(ingredientRequests.get(i).category());
        }
        for (String s : setList){
            stringBuilder.append(s).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        iceBox.setIngredients(String.valueOf(stringBuilder));
        iceBoxRepository.save(iceBox);
        return iceBox;
    }

    /**
     * Метод сравнивает список ингредиентов в холодильнике с ингредиентами хранящимися в рецептах,
     * затем сортирует их порядке убывания
     * @param id - передает id холодильника, в котором содержаться рецепты
     * @return возвращает отсортированный по релевантности список рецептов
     */
    public List<RecipeRequest> generateById(Long id) {
        IceBox iceBox = iceBoxRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("IceBox by %d not found".formatted(id)));
        String ingredients = iceBox.getIngredients();
        if(ingredients == null || ingredients.isEmpty()){
            throw new IceBoxIsNullException("IceBox by %d is null".formatted(id));
        }
        String[] ingredientsList = ingredients.split(" ");
        List<IngredientRequest> ingredientRequestList = new ArrayList<>();
        for (String str : ingredientsList) {
            ingredientRequestList.add(new IngredientRequest(str));
        }
        List<RecipeRequest> recipeRequests = recipeClient.getRecipes(ingredientRequestList);
        return recipeRequests.stream().sorted(Comparator.comparing((RecipeRequest recipeRequest) ->
                recipeRequest.ingredients().size()).reversed()).toList();
    }
}
