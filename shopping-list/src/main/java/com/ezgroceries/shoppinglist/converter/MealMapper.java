package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.MealResource;
import com.ezgroceries.shoppinglist.model.Meal;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {

    public static Meal DtoToEntity(MealResource mealResource) {
        return new Meal(mealResource.getMealId(),
                            mealResource.getMealDbId(),
                            mealResource.getName(),
                            mealResource.getIngredients(),
                            mealResource.getInstructions(),
                            mealResource.getImage());
    }

    public static MealResource EntitytoDto(Meal meal) {
        return new MealResource(meal.getMealId(),
                                    meal.getMealDbId(),
                                    meal.getName(),
                                    meal.getInstructions(),
                                    meal.getImage(),
                                    meal.getIngredients());
    }

}
