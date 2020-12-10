package com.ezgroceries.shoppinglist.cocktail.external.dto;

import java.util.List;

public class CocktailDBResponse {

    private List<DrinkResource> drinkResources;

    public CocktailDBResponse() {
    }

    public CocktailDBResponse(List<DrinkResource> drinkResources) {
        this.drinkResources = drinkResources;
    }

    public List<DrinkResource> getDrinks() {
        return drinkResources;
    }

    public void setDrinks(List<DrinkResource> drinkResources) {
        this.drinkResources = drinkResources;
    }

}
