package com.ezgroceries.shoppinglist.resources;

import com.ezgroceries.shoppinglist.web.CocktailController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;
    private List<CocktailReference> cocktails;//todo -> map? only UUID or cocktail pojo?

    public ShoppingList(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.cocktails = new ArrayList<>();
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

    public List<CocktailReference> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailReference>cocktails) {
        this.cocktails = cocktails;
    }

    public void addCocktails(List<CocktailReference> cocktails) {
        this.cocktails.addAll(cocktails);
    }
}