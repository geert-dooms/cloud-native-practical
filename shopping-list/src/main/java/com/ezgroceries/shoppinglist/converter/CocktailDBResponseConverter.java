package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CocktailDBResponseConverter {

    private List<Cocktail> cocktails;

    //todo > is this the way to go for dependency inj?
    public CocktailDBResponseConverter(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public List<Cocktail> processSearchCocktailResponse(CocktailDBResponse cocktailDBResponse) {
        //todo > cleaning list because it stays 'in memory' > a new search should result in a new list; alternatively,
        //creating a new list everytime would not store in memory (?) and not allow us to add a cocktail to a shoppinglist
        cleanCocktailList(cocktails);
        cocktailDBResponse.getDrinks().forEach(drink -> cocktails.add(convertDrinkToCocktail(drink)));
        return cocktails;
    }

    //todo -> refactor (cleaner; is there a better way to handle these 15 strings?)
    private Cocktail convertDrinkToCocktail (Drink drink) {

        List<String> ingredients = new ArrayList<>();

        ingredients.addAll(Arrays.asList(
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

        return new Cocktail(UUID.randomUUID(),
                            drink.getStrDrink(),
                            drink.getStrGlass(),
                            drink.getStrInstructions(),
                            drink.getStrDrinkThumb(),
                            ingredients);
    }

    private void cleanCocktailList(List<Cocktail> cocktails) {
        cocktails.clear();
        return;
    }
}
