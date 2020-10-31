package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.resources.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {
    private List<ShoppingList> shoppingListList;

    public ShoppingListService(List<ShoppingList> shoppingLists) {
        this.shoppingListList = shoppingLists;
    }

/*    public List<ShoppingList> getAllGifts() {
        return this.shoppingListList;
    }*/

    public shoppingList createShoppingList(String name) {
        this.shoppingListList.add(name);
    }
}
