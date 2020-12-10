package com.ezgroceries.shoppinglist.meal.controller.dto;

import java.util.Objects;
import java.util.UUID;

/**
* This class was created to solve issues with serialisation with UUID for some of the POST calls in ShoppingListController
*/


public class AddMealResponse {

private UUID mealId;

public AddMealResponse() {

}

public AddMealResponse(UUID mealId) {
    this.mealId = mealId;
}

public UUID getMealId() {
    return mealId;
}

public void setMealId(UUID mealId) {
    this.mealId = mealId;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddMealResponse that = (AddMealResponse) o;
    return Objects.equals(mealId, that.mealId);
}
}
