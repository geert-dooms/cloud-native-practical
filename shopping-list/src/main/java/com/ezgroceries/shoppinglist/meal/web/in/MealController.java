package com.ezgroceries.shoppinglist.meal.web.in;

import com.ezgroceries.shoppinglist.meal.service.dto.MealResource;
import com.ezgroceries.shoppinglist.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping(value = "/meals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealResource> searchMeals(@RequestParam String search) {
        return mealService.searchMeals(search);
    }
}
