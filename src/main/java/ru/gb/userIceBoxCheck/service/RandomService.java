package ru.gb.userIceBoxCheck.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomService {
    int rnd = new Random().nextInt(0, 3);

    public List<String> randomList(){

        List<String> randomList = new ArrayList<>();
        if(rnd == 0){
            randomList.add("chicken");
            randomList.add("milk");
            randomList.add("eggs");
            randomList.add("bread");
        } else if (rnd == 1) {
            randomList.add("tuna");
            randomList.add("noodles");
            randomList.add("milk");
            randomList.add("cheese");
        } else if (rnd == 2) {
            randomList.add("beacon");
            randomList.add("eggs");
            randomList.add("garlic");
            randomList.add("tomatoes");
        }
        return randomList;
    }
}
