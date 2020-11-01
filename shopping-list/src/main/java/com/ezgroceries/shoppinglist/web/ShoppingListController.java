package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody String name) {
        return shoppingListService.create(name);
    }

    @PostMapping(value = "/shopping-lists/{id}/cocktails")
    public List<UUID> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<UUID> cocktails) {
        return shoppingListService.addCocktails(cocktails);
    }
}
