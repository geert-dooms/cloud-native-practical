package com.ezgroceries.shoppinglist.model;

import com.ezgroceries.shoppinglist.util.StringSetConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @Column(name = "id")
    private UUID mealId;

    @Column(name = "id_mealdb")
    private String mealDbId;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "meals")
    private Set<ShoppingList> shoppingLists = new HashSet<ShoppingList>();

    public Meal() {

    }

    public Meal(UUID mealId, String mealDbId, String name, Set<String> ingredients, String instructions, String image) {
        this.mealId = mealId;
        this.mealDbId = mealDbId;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.image = image;
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
