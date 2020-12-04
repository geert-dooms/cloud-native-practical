package com.ezgroceries.shoppinglist.client;

import com.ezgroceries.shoppinglist.dto.Drink;

import java.util.List;

public class CocktailDBResponse {

    private List<Drink> drinks;

    public CocktailDBResponse() {
    }

    public CocktailDBResponse(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

}
