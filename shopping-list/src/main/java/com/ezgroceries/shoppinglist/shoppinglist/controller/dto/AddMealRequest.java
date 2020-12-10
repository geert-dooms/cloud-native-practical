package com.ezgroceries.shoppinglist.shoppinglist.controller.dto;

import java.util.Objects;
import java.util.UUID;

/**
* This class was created to solve issues with serialisation with UUID for some of the POST calls in ShoppingListController
*/


public class AddMealRequest {

private UUID mealId;

public AddMealRequest() {

}

public AddMealRequest(UUID mealId) {
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
    AddMealRequest that = (AddMealRequest) o;
    return Objects.equals(mealId, that.mealId);
}
}
