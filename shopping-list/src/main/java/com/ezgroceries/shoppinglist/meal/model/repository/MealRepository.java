package com.ezgroceries.shoppinglist.meal.model.repository;

import com.ezgroceries.shoppinglist.meal.model.Meal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MealRepository extends CrudRepository<Meal, UUID> {

    Optional<Meal> findByMealDbId(String mealDbId);

    List<Meal> findByNameContainingIgnoreCase(String name);
}
