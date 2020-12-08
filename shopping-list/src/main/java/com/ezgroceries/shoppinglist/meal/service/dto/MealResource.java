package com.ezgroceries.shoppinglist.meal.service.dto;

import java.util.Set;
import java.util.UUID;


public class MealResource {
    private UUID mealId;
    private String mealDbId;
    private String name;
    private String instructions;
    private String image;
    private Set<String> ingredients;

    public MealResource(UUID mealId, String mealDbId, String name, String instructions, String image, Set<String> ingredients) {
        this.mealId = mealId;
        this.mealDbId = mealDbId;
        this.name = name;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public UUID getMealId() {
        return mealId;
    }

    public void setMealId(UUID mealId) {
        this.mealId = mealId;
    }

    public String getMealDbId() {
        return mealDbId;
    }

    public void setMealDbId(String mealDbId) {
        this.mealDbId = mealDbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
