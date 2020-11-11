package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.CocktailResource;
import com.ezgroceries.shoppinglist.model.Cocktail;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CocktailMapper {

    public static Cocktail DtoToEntity(CocktailResource cocktailResource) {
        return new Cocktail(cocktailResource.getCocktailId(),
                            cocktailResource.getDrinkId(),
                            cocktailResource.getName(),
                            cocktailResource.getIngredients());
    }

    public static CocktailResource EntitytoDto(Cocktail cocktail) {
        return new CocktailResource(cocktail.getCocktailId(),
                                    cocktail.getDrinkId(),
                                    cocktail.getName(),
                                    null,
                                    null,
                                    null,
                                    cocktail.getIngredients());
    }

}
