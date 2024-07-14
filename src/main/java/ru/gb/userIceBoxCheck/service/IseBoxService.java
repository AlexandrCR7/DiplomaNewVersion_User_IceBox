package ru.gb.userIceBoxCheck.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.userIceBoxCheck.client.RecipeClient;
import ru.gb.userIceBoxCheck.model.IceBox;
import ru.gb.userIceBoxCheck.repository.IceBoxRepository;
import ru.gb.userIceBoxCheck.request.IngredientRequest;

import java.util.*;

@Service
@AllArgsConstructor
@Getter
public class IseBoxService {

    private final RecipeClient recipeClient;

    private final IceBoxRepository iceBoxRepository;

    public List<IceBox> findAll(){
        return iceBoxRepository.findAll();
    }

    @Transactional
    public Optional<IceBox> findById(Long id){
        return iceBoxRepository.findById(id);
    }

    public void deleteById(Long id){
        iceBoxRepository.deleteById(id);
    }

    public void saveIseBox(IceBox iceBox){
        iceBoxRepository.save(iceBox);
    }

    public IceBox addIceBox(String ingredients){
        IceBox iceBox = new IceBox();
        iceBox.setIngredients(ingredients);
        return iceBox;
    }

    @GetMapping("/test")
    public List<IngredientRequest> testing(){
        return recipeClient.getIngredients();
    }

    //добавить логику добавления продуктов в холодильник, цикл по ингредиентам,
    // достаем ингредиенты, сплитим через пробел, последний убираем
    public IceBox fillIceBox(Long id) {
        List<IngredientRequest> ingredientRequests = recipeClient.getIngredients();
        IceBox iceBox = iceBoxRepository.getReferenceById(id);
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

}
