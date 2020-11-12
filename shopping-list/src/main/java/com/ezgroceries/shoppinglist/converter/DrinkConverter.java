package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.CocktailResource;
import com.ezgroceries.shoppinglist.dto.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DrinkConverter {

    //todo -> refactor (cleaner; is there a better way to handle these 15 strings?)
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

        ingredients.removeIf(Objects::isNull);

        //todo should UUID be generated here? or in entity?
        return new CocktailResource(UUID.randomUUID(),
                drink.getIdDrink(),
                drink.getStrDrink(),
                drink.getStrGlass(),
                drink.getStrInstructions(),
                drink.getStrDrinkThumb(), ingredients);
    }
}
