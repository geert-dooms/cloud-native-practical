package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, UUID> {

    @PostAuthorize("returnObject.username == principal.username")
    Optional<ShoppingList> findById(UUID uuid);

    Iterable<ShoppingList> findAll();
}
