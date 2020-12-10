package com.ezgroceries.shoppinglist.meal.external;

import com.ezgroceries.shoppinglist.meal.controller.dto.MealResource;
import com.ezgroceries.shoppinglist.meal.model.Meal;
import com.ezgroceries.shoppinglist.meal.external.dto.MealDBResource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MealDbConverter {

    public MealResource convertMealDbToMeal (MealDBResource mealDbResource) {

        Set<String> ingredients = new HashSet<>(Arrays.asList(
                mealDbResource.getStrIngredient1(),
                mealDbResource.getStrIngredient2(),
                mealDbResource.getStrIngredient3(),
                mealDbResource.getStrIngredient4(),
                mealDbResource.getStrIngredient5(),
                mealDbResource.getStrIngredient6(),
                mealDbResource.getStrIngredient7(),
                mealDbResource.getStrIngredient8(),
                mealDbResource.getStrIngredient9(),
                mealDbResource.getStrIngredient10(),
                mealDbResource.getStrIngredient11(),
                mealDbResource.getStrIngredient12(),
                mealDbResource.getStrIngredient13(),
                mealDbResource.getStrIngredient14(),
                mealDbResource.getStrIngredient15()));

        //themealdb returns spaces for empty ingredients whereas thecocktaildb returns null;
        ingredients.remove("");

        return new MealResource(UUID.randomUUID(),
                mealDbResource.getIdMeal(),
                mealDbResource.getStrMeal(),
                mealDbResource.getStrInstructions(),
                mealDbResource.getStrMealThumb(), ingredients);
    }

    public MealDBResource convertMealToMealDb(Meal meal) {

        String[] ingredients = meal.getIngredients().toArray(new String[15]);

        return new MealDBResource(meal.getMealDbId(),
                         meal.getName(),
                         meal.getInstructions(),
                         meal.getImage(),
                         ingredients[0],
                         ingredients[1],
                         ingredients[2],
                         ingredients[3],
                         ingredients[4],
                         ingredients[5],
                         ingredients[0],
                         ingredients[7],
                         ingredients[8],
                         ingredients[9],
                         ingredients[10],
                         ingredients[11],
                         ingredients[12],
                         ingredients[13],
                         ingredients[14]);
    }
}
