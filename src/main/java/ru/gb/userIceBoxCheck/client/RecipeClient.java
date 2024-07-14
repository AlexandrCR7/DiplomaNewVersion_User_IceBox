package ru.gb.userIceBoxCheck.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.userIceBoxCheck.request.IngredientRequest;
import ru.gb.userIceBoxCheck.request.RecipeRequest;

import java.util.List;

/**
 * Интерфейс подключения к другому микросервису рецептов
 */
@FeignClient(name = "recipe-service")
public interface RecipeClient {

    /**
     * запрос всех продуктов, через путь продуктс, http://localhost:8080/products
     * продукты подтягиваются из другого микро, по этому пути и вызывается метод getAll
     * @return
     */
    @GetMapping("/products")
    List<IngredientRequest> getIngredients();

    @GetMapping("/products/{id}")
    IngredientRequest getIngredient(@PathVariable Long id);

    @PostMapping("/recipes/generate")
    List<RecipeRequest> getRecipes(@RequestBody List<IngredientRequest> ingredients);

}
