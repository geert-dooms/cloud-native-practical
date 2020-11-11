package com.ezgroceries.shoppinglist.model;

import com.ezgroceries.shoppinglist.converter.StringSetConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class Cocktail {

    @Id
    @Column(name = "id")
    private UUID cocktailId;

    @Column(name = "id_drink")
    private String drinkId;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;


    @ManyToMany(mappedBy = "cocktails")
    private Set<ShoppingList> shoppingLists = new HashSet<ShoppingList>();

    public Cocktail() {

    }

    public Cocktail(UUID cocktailId, String drinkId, String name, Set<String> ingredients) {
        this.cocktailId = cocktailId;
        this.drinkId = drinkId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
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
}
