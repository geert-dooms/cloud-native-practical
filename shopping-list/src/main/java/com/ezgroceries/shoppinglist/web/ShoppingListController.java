package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resources.CocktailReference;
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

    @GetMapping(value = "/shopping-lists")
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }
    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody String name) {
        return shoppingListService.create(name);
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CocktailReference> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<CocktailReference> cocktails) {
        shoppingListService.findShoppingListById(shoppingListId).addCocktails(cocktails);
        return cocktails;
    }
}
