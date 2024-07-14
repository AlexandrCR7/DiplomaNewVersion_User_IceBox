package ru.gb.userIceBoxCheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.userIceBoxCheck.client.RecipeClient;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.repository.IceBoxRepository;
import ru.gb.userIceBoxCheck.request.IngredientRequest;
import ru.gb.userIceBoxCheck.request.RecipeRequest;
import ru.gb.userIceBoxCheck.service.IseBoxService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/iceBox")
public class IceBoxController {


    private final IseBoxService iseBoxService;

    private final IceBoxRepository iceBoxRepository;

    /**
     * Для теста, потом удалить.
     */
    private RecipeClient recipeClient;

    @GetMapping
    public List<IceBox> findAll(){
        return iseBoxService.findAll();
    }
    @GetMapping("/change/{id}")
    public IceBox show(@PathVariable Long id){
        return iseBoxService.fillIceBox(id);
    }
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

    /**
     * Убрать модели, почистить контроллер как в ингредиентах
     * @param model
     * @return
     */
    @GetMapping("/newIceBox")
    public String newIceBox(Model model){
        model.addAttribute("newIceBox", iceBoxRepository.findAll());
        return "IceBox has been created";
    }

    @PostMapping("/newIceBox")
    public String newIceBox(@RequestBody IceBox iceBox, Model model){
        iseBoxService.saveIseBox(iseBoxService.addIceBox(iceBox.getIngredients()));
        model.addAttribute("iceBox", iseBoxService.findAll());
        return "IceBox has been created";
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
