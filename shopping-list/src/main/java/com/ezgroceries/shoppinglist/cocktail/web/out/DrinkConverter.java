package com.ezgroceries.shoppinglist.cocktail.web.out;

import com.ezgroceries.shoppinglist.cocktail.service.dto.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.web.out.dto.DrinkResource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DrinkConverter {

    //todo > use cocktail instead of cocktail resource?
    public CocktailResource convertDrinkToCocktail (DrinkResource drinkResource) {

            Set<String> ingredients = new HashSet<>(Arrays.asList(
                drinkResource.getStrIngredient1(),
                drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(),
                drinkResource.getStrIngredient4(),
                drinkResource.getStrIngredient5(),
                drinkResource.getStrIngredient6(),
                drinkResource.getStrIngredient7(),
                drinkResource.getStrIngredient8(),
                drinkResource.getStrIngredient9(),
                drinkResource.getStrIngredient10(),
                drinkResource.getStrIngredient11(),
                drinkResource.getStrIngredient12(),
                drinkResource.getStrIngredient13(),
                drinkResource.getStrIngredient14(),
                drinkResource.getStrIngredient15()));

        ingredients.remove(null);

        return new CocktailResource(UUID.randomUUID(),
                drinkResource.getIdDrink(),
                drinkResource.getStrDrink(),
                drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(),
                drinkResource.getStrDrinkThumb(), ingredients);
    }

    public DrinkResource convertCocktailToDrink(Cocktail cocktail) {

        String[] ingredients = cocktail.getIngredients().toArray(new String[15]);

        return new DrinkResource(cocktail.getDrinkId(),
                         cocktail.getName(),
                         cocktail.getGlass(),
                         cocktail.getInstructions(),
                         cocktail.getImage(),
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
