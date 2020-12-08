package com.ezgroceries.shoppinglist.meal.web.out.dto;

import java.util.List;

public class MealDBResponse {

    private List<MealDBResource> mealDBResources;

    public MealDBResponse() {
    }

    public MealDBResponse(List<MealDBResource> mealDBResources) {
        this.mealDBResources = mealDBResources;
    }

    public List<MealDBResource> getMealDbs() {
        return mealDBResources;
    }

    public void setMeals(List<MealDBResource> mealDBResources) {
        this.mealDBResources = mealDBResources;
    }

}
