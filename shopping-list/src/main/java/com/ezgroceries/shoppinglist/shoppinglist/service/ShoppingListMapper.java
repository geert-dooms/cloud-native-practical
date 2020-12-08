package com.ezgroceries.shoppinglist.shoppinglist.service;

import com.ezgroceries.shoppinglist.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.shoppinglist.service.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppinglist.web.in.dto.NewShoppingListRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class ShoppingListMapper {

    public ShoppingList toShoppingList(NewShoppingListRequest newShoppingListRequest, String username) {
        return new ShoppingList(UUID.randomUUID(),
                                newShoppingListRequest.getName(),
                                username
                                );
    }

    public ShoppingListResource toShoppingListResource(ShoppingList shoppingList) {

        Set<String> ingredients = new HashSet<>();

        //todo - best way to fetch ingredients?
        shoppingList.getCocktails().forEach(cocktail -> ingredients.addAll(cocktail.getIngredients()));
        shoppingList.getMeals().forEach(meal -> ingredients.addAll(meal.getIngredients()));

        return new ShoppingListResource(shoppingList.getShoppingListId(),
                                        shoppingList.getName(),
                                        shoppingList.getUsername(),
                                        ingredients);
    }
}
