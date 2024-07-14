package ru.gb.userIceBoxCheck.request;

import java.util.List;

public record RecipeRequest(
        String recipe,
        String name,
        List<IngredientRequest> ingredients
) {
}
