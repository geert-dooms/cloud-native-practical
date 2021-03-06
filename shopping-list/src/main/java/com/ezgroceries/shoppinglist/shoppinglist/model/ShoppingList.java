package com.ezgroceries.shoppinglist.shoppinglist.model;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.meal.model.Meal;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @Column(name = "id")
    private UUID shoppingListId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @ManyToMany
    @JoinTable(name = "cocktail_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
    private final Set<Cocktail> cocktails = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "meal_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private final Set<Meal> meals = new HashSet<>();

    public ShoppingList() {
    }

    public ShoppingList(UUID shoppingListId, String name, String username) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

    public Set<Cocktail> getCocktails() {
        return cocktails;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public Set<Meal> getMeals() {
        return meals;
    }
}
