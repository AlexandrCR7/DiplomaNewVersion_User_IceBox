package ru.gb.userIceBoxCheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.userIceBoxCheck.client.RecipeClient;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.request.IngredientRequest;
import ru.gb.userIceBoxCheck.request.RecipeRequest;
import ru.gb.userIceBoxCheck.service.IseBoxService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/iceBox")
public class IceBoxController {


    private final IseBoxService iseBoxService;

    private RecipeClient recipeClient;

    @GetMapping
    public List<IceBox> findAll(){
        return iseBoxService.findAll();
    }

    /**
     * Наполняет холодильник из передаваемых ингредиентов из второго микросервиса
     * @param id - принимает на вход id холодильника, который нужно заполнить
     * @return - возвращает заполненный холодильник
     */
    @GetMapping("/change/{id}")
    public IceBox show(@PathVariable Long id){
        return iseBoxService.fillIceBox(id);
    }

    /**
     * Тестовый метод
     * @return возвращает все ингредиенты, которые существуют в другом микросервисе
     */
    @GetMapping("/test")
    public List<IngredientRequest> testing(){
        return recipeClient.getIngredients();
    }

    @GetMapping("/show/{id}")
    public IceBox showById(@PathVariable Long id){
        return iseBoxService.findById(id);
    }

    @DeleteMapping("/deleteIceBox/{id}")
    public String deleteIceBox(@PathVariable Long id){
        iseBoxService.deleteById(id);
        return "IceBox has been deleted";
    }

    @GetMapping("/{id}")
    public IngredientRequest ingredientRequests(@PathVariable Long id) {
        return recipeClient.getIngredient(id);
    }

    @GetMapping("/generate/{id}")
    public List<RecipeRequest> generateRecipe(@PathVariable Long id){
        System.out.println("Получен запрос с Id " + id);
        return iseBoxService.generateById(id);
    }
}
