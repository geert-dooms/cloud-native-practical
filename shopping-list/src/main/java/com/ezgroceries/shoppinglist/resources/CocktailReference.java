package com.ezgroceries.shoppinglist.resources;

import java.util.UUID;

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
