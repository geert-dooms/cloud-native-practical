package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.resources.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private List<ShoppingList> shoppingLists;

    public ShoppingListService(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public ShoppingList create(String name) {
        ShoppingList shoppingList = new ShoppingList(UUID.randomUUID(), name);  //todo > is using new ok here?
        this.shoppingLists.add(shoppingList);
        return shoppingList;
    }

    public List<ShoppingList> getAllShoppingLists() {
        return(shoppingLists);
    }

    public ShoppingList findShoppingListById(UUID shoppingListId) {
        for (ShoppingList shoppinglist : shoppingLists) {
            if (shoppinglist.getShoppingListId().equals(shoppingListId)) {
                return shoppinglist;
            }
        }
        return null;
    }
}
