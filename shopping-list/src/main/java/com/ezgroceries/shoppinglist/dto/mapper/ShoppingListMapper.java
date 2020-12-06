package com.ezgroceries.shoppinglist.dto.mapper;

import com.ezgroceries.shoppinglist.controller.request.*;;
import com.ezgroceries.shoppinglist.dto.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class ShoppingListMapper {

/*    public ShoppingList DtoToEntity(ShoppingListResource shoppingListResource) {
        return new ShoppingList(shoppingListResource.getShoppingListId(),
                                shoppingListResource.getName());
    }*/

    public ShoppingList toShoppingList(NewShoppingListRequest newShoppingListRequest) {
        return new ShoppingList(UUID.randomUUID(),
                                newShoppingListRequest.getName());
    }

    public ShoppingListResource toShoppingListResource(ShoppingList shoppingList) {

        Set<String> ingredients = new HashSet<>();

        //todo - best way to fetch ingredients?
        shoppingList.getCocktails().forEach(cocktail -> ingredients.addAll(cocktail.getIngredients()));
        shoppingList.getMeals().forEach(meal -> ingredients.addAll(meal.getIngredients()));

        return new ShoppingListResource(shoppingList.getShoppingListId(),
                                        shoppingList.getName(),
                                        ingredients);
    }
}
