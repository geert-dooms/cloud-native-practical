package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ShoppingListMapper {

    public static ShoppingList DtoToEntity(ShoppingListResource shoppingListResource) {
        return new ShoppingList(shoppingListResource.getShoppingListId(),
                                shoppingListResource.getName());
    }

    public static ShoppingListResource EntityToDto(ShoppingList shoppingList) {

        Set<String> ingredients = new HashSet<>();

        //todo - best way to fetch ingredients?
        shoppingList.getCocktails().forEach(cocktail -> ingredients.addAll(cocktail.getIngredients()));

        return new ShoppingListResource(shoppingList.getShoppingListId(),
                                        shoppingList.getName(),
                                        ingredients);
    }
}
