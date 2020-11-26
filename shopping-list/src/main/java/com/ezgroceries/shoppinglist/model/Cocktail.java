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

    @Column(name = "glass")
    private String glass;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "image")
    private String image;

    @ManyToMany(mappedBy = "cocktails")
    private Set<ShoppingList> shoppingLists = new HashSet<ShoppingList>();

    public Cocktail() {

    }

    public Cocktail(UUID cocktailId, String drinkId, String name, Set<String> ingredients, String glass, String instructions, String image) {
        this.cocktailId = cocktailId;
        this.drinkId = drinkId;
        this.name = name;
        this.ingredients = ingredients;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
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

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
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
