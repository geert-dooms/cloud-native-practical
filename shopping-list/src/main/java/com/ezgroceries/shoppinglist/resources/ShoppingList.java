package com.ezgroceries.shoppinglist.resources;

import java.util.List;
import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;
    private List<Cocktail> cocktails;

    public ShoppingList(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
        return (cocktail.getCocktailId());
    }
}
