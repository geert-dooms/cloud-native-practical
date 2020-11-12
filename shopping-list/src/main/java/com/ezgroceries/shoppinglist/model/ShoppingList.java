package com.ezgroceries.shoppinglist.model;

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

    @ManyToMany
    @JoinTable(name = "cocktail_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
    private Set<Cocktail> cocktails = new HashSet<>();

    public ShoppingList() {
    }

    public ShoppingList(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
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

    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

    public Set<Cocktail> getCocktails() {
        return cocktails;
    }
}
