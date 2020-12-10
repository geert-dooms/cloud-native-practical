package com.ezgroceries.shoppinglist.cocktail.repository;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CocktailRepository extends CrudRepository<Cocktail, UUID> {

    Optional<Cocktail> findByDrinkId(String drinkId);

    List<Cocktail> findByNameContainingIgnoreCase(String name);
}
