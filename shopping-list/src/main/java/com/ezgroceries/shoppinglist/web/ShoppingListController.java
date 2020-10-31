package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @PostMapping(value = "/shopping-lists")
    public ShoppingList createShoppingList(@RequestBody String name) {
        return shoppingListService.addShoppingList(name);
    }
}
