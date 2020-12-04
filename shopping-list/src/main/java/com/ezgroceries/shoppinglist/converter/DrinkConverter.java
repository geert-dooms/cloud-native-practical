package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.CocktailResource;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.dto.Drink;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DrinkConverter {

    //todo -> refactor (cleaner; is there a better way to handle these 15 strings?)
    //todo > use cocktail instead of cocktail resource?
    public CocktailResource convertDrinkToCocktail (Drink drink) {

            Set<String> ingredients = new HashSet<>(Arrays.asList(
                drink.getStrIngredient1(),
                drink.getStrIngredient2(),
                drink.getStrIngredient3(),
                drink.getStrIngredient4(),
                drink.getStrIngredient5(),
                drink.getStrIngredient6(),
                drink.getStrIngredient7(),
                drink.getStrIngredient8(),
                drink.getStrIngredient9(),
                drink.getStrIngredient10(),
                drink.getStrIngredient11(),
                drink.getStrIngredient12(),
                drink.getStrIngredient13(),
                drink.getStrIngredient14(),
                drink.getStrIngredient15()));

        ingredients.remove(null);

        //todo should UUID be generated here? or in entity (e.g. with autogeneration)   ? - same remark for shoppinglist
        return new CocktailResource(UUID.randomUUID(),
                drink.getIdDrink(),
                drink.getStrDrink(),
                drink.getStrGlass(),
                drink.getStrInstructions(),
                drink.getStrDrinkThumb(), ingredients);
    }

    public Drink convertCocktailToDrink(Cocktail cocktail) {

        String[] ingredients = cocktail.getIngredients().toArray(new String[15]);

        return new Drink(cocktail.getDrinkId(),
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
