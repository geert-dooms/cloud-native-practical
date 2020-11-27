package com.ezgroceries.shoppinglist.client;

import com.ezgroceries.shoppinglist.model.MealDb;

import java.util.List;

public class MealDBResponse {

    private List<MealDb> mealDbs;

    public MealDBResponse() {
    }

    public MealDBResponse(List<MealDb> mealDbs) {
        this.mealDbs = mealDbs;
    }

    public List<MealDb> getMealDbs() {
        return mealDbs;
    }

    public void setMeals(List<MealDb> mealDbs) {
        this.mealDbs = mealDbs;
    }

}
