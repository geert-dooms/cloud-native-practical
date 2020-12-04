package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.MealResource;
import com.ezgroceries.shoppinglist.model.Meal;
import com.ezgroceries.shoppinglist.dto.MealDb;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MealDbConverter {

    //todo -> refactor (cleaner; is there a better way to handle these 15 strings?)
    public MealResource convertMealDbToMeal (MealDb mealDb) {

        Set<String> ingredients = new HashSet<>(Arrays.asList(
                mealDb.getStrIngredient1(),
                mealDb.getStrIngredient2(),
                mealDb.getStrIngredient3(),
                mealDb.getStrIngredient4(),
                mealDb.getStrIngredient5(),
                mealDb.getStrIngredient6(),
                mealDb.getStrIngredient7(),
                mealDb.getStrIngredient8(),
                mealDb.getStrIngredient9(),
                mealDb.getStrIngredient10(),
                mealDb.getStrIngredient11(),
                mealDb.getStrIngredient12(),
                mealDb.getStrIngredient13(),
                mealDb.getStrIngredient14(),
                mealDb.getStrIngredient15()));

        //themealdb returns spaces for empty ingredients whereas thecocktaildb returns null;
        ingredients.remove("");

        //todo should UUID be generated here? or in entity (e.g. with autogeneration)   ? - same remark for shoppinglist
        return new MealResource(UUID.randomUUID(),
                mealDb.getIdMeal(),
                mealDb.getStrMeal(),
                mealDb.getStrInstructions(),
                mealDb.getStrMealThumb(), ingredients);
    }

    public MealDb convertMealToMealDb(Meal meal) {

        String[] ingredients = meal.getIngredients().toArray(new String[15]);

        return new MealDb(meal.getMealDbId(),
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
