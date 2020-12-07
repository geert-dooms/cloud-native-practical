package com.ezgroceries.shoppinglist.controller.client;

import com.ezgroceries.shoppinglist.dto.mapper.MealDbConverter;
import com.ezgroceries.shoppinglist.model.Meal;
import com.ezgroceries.shoppinglist.dto.model.MealDb;
import com.ezgroceries.shoppinglist.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "mealDBClient", url = "https://www.themealdb.com/api/json/v1/1", fallback = MealDBClientFallback.class)
public interface MealDBClient {

    @GetMapping(value = "search.php")
    MealDBResponse searchMeals(@RequestParam("s") String search);

}

@Component
@RequiredArgsConstructor
class MealDBClientFallback implements MealDBClient {

    private final MealRepository cocktailRepository;
    private final MealDbConverter mealDbConverter;

    @Override
    public MealDBResponse searchMeals(String search) {
        List<MealDb> mealDbs = new ArrayList<>();

        List<Meal> meals = cocktailRepository.findByNameContainingIgnoreCase(search);
        for (Meal meal : meals) {
            mealDbs.add(mealDbConverter.convertMealToMealDb(meal));
        }

        return new MealDBResponse(mealDbs);
    }
}