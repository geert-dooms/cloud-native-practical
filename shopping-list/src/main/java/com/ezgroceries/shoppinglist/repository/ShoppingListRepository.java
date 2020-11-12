package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, UUID> {
}
