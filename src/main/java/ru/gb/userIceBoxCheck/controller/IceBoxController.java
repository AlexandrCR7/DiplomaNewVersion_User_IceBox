package ru.gb.userIceBoxCheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.userIceBoxCheck.client.RecipeClient;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.repository.IceBoxRepository;
import ru.gb.userIceBoxCheck.request.IngredientRequest;
import ru.gb.userIceBoxCheck.service.IseBoxService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/iceBox")
public class IceBoxController {

    @Autowired
    private IseBoxService iseBoxService;
    @Autowired
    private IceBoxRepository iceBoxRepository;

    /**
     * Для теста, потом удалить.
     */
    private RecipeClient recipeClient;

    @GetMapping
    public List<IceBox> findAll(){
        return iseBoxService.findAll();
    }

    // меняет данные в базе данных, но при этом не отрабатывает код, не показывает измененный холодильник
    @GetMapping("/change/{id}")
    public IceBox show(@PathVariable Long id){
        return iseBoxService.fillIceBox(id);
    }

    // поменял на лист, т.к не мог вызвать у сета поле категории, а если и получалось его вызывать то он добавлял в
    // строку много лишней информации, название класса, название поля + содержимое поля
    @GetMapping("/test")
    public List<IngredientRequest> testing(){
        return recipeClient.getIngredients();
    }
    @GetMapping("/show/{id}")
    public Optional<IceBox> showById(@PathVariable Long id){
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

    /**
     * Пример - тестовый : @GetMapping("/test")
     * создать энд поинт, который заполняет холодильник.
     * должен быть id холодильника, который нужно заполнить
     * будет вызываться iceBoxService, который будет дергать метод по заполнению холодоса
     * в iceboxService внедрить recipeClient
     * В методе по заполнению холодильника дернуть метод getIngredients, получим множество продуктов,
     * и передаем id холодильника в icebox service, в icebox service вытаскиваем холодильник по id
     */
}
