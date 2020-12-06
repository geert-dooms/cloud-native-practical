package com.ezgroceries.shoppinglist.dto.mapper;

import com.ezgroceries.shoppinglist.dto.model.MealResource;
import com.ezgroceries.shoppinglist.model.Meal;
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
