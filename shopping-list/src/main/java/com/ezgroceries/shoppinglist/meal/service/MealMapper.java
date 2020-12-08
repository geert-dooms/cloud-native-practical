package com.ezgroceries.shoppinglist.meal.service;

import com.ezgroceries.shoppinglist.meal.model.Meal;
import com.ezgroceries.shoppinglist.meal.service.dto.MealResource;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {

    public Meal toMeal(MealResource mealResource) {
        return new Meal(mealResource.getMealId(),
                            mealResource.getMealDbId(),
                            mealResource.getName(),
                            mealResource.getIngredients(),
                            mealResource.getInstructions(),
                            mealResource.getImage());
    }

/*    public MealResource toMealResource(Meal meal) {
        return new MealResource(meal.getMealId(),
                                    meal.getMealDbId(),
                                    meal.getName(),
                                    meal.getInstructions(),
                                    meal.getImage(),
                                    meal.getIngredients());
    }*/

}
