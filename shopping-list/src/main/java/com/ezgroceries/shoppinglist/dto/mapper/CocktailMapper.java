package com.ezgroceries.shoppinglist.dto.mapper;

import com.ezgroceries.shoppinglist.dto.model.CocktailResource;
import com.ezgroceries.shoppinglist.model.Cocktail;
import org.springframework.stereotype.Component;

@Component
public class CocktailMapper {

    public Cocktail toCocktail(CocktailResource cocktailResource) {
        return new Cocktail(cocktailResource.getCocktailId(),
                            cocktailResource.getDrinkId(),
                            cocktailResource.getName(),
                            cocktailResource.getIngredients(),
                            cocktailResource.getGlass(),
                            cocktailResource.getInstructions(),
                            cocktailResource.getImage());
    }
/*

    public CocktailResource toCocktailResource(Cocktail cocktail) {
        return new CocktailResource(cocktail.getCocktailId(),
                                    cocktail.getDrinkId(),
                                    cocktail.getName(),
                                    cocktail.getGlass(),
                                    cocktail.getInstructions(),
                                    cocktail.getImage(),
                                    cocktail.getIngredients());
    }
*/

}
