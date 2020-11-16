package com.ezgroceries.shoppinglist.dto;

import java.util.*;

public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;
    private Set<String> ingredients; //todo -> best place to include ingredients to the outside world?

    public ShoppingListResource(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = new HashSet<>();
    }

    public ShoppingListResource(UUID shoppingListId, String name, Set<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
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

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListResource that = (ShoppingListResource) o;
        return shoppingListId.equals(that.shoppingListId) &&
                name.equals(that.name) &&
                Objects.equals(ingredients, that.ingredients);
    }
}