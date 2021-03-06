package com.ezgroceries.shoppinglist.cocktail.web.in.dto;

import java.util.Objects;
import java.util.UUID;

/**
* This class was created to solve issues with serialisation with UUID for some of the POST calls in ShoppingListController
*/


public class AddCocktailResponse {
private UUID cocktailId;

public AddCocktailResponse() {

}

public AddCocktailResponse(UUID cocktailId) {
    this.cocktailId = cocktailId;
}

public UUID getCocktailId() {
    return cocktailId;
}

public void setCocktailId(UUID cocktailId) {
    this.cocktailId = cocktailId;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddCocktailResponse that = (AddCocktailResponse) o;
    return Objects.equals(cocktailId, that.cocktailId);
}
}
