package com.ezgroceries.shoppinglist.model;

import java.util.UUID;

    /**
    * This class was created to solve issues with serialisation with UUID for some of the POST calls in ShoppingListController
    */
public class CocktailReference {
    private UUID cocktailId;

    public CocktailReference() {

    }

    public CocktailReference(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }
}
