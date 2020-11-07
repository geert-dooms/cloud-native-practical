package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService { //todo > use interface instead

    private List<ShoppingList> shoppingLists;

    public ShoppingListService(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public ShoppingList create(String name) {
        ShoppingList shoppingList = new ShoppingList(UUID.randomUUID(), name);
        //todo > is using new ok here?
        //todo > should randomUUID be inside the ShoppingList class? (or in a separate 'shoppinglistcreator' class?)
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
