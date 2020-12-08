package com.ezgroceries.shoppinglist.shoppinglist.model.repository;

import com.ezgroceries.shoppinglist.shoppinglist.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, UUID> {
}
