package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.CocktailReference;
import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping(value = "/shopping-lists/{shoppingListId}")
    public ShoppingListResource getShoppingList(@PathVariable UUID shoppingListId) {
        return shoppingListService.findShoppingListById(shoppingListId);
    }

    //todo > return set instead of list?
    @GetMapping(value = "/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody String name) {
        return shoppingListService.create(name);
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailReference> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<CocktailReference> cocktailReferences) {
        return shoppingListService.addCocktails(shoppingListId, cocktailReferences);
    }
}
