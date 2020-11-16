package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.AddCocktailRequest;
import com.ezgroceries.shoppinglist.dto.NewShoppingListRequest;
import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping(value = "/shopping-lists/{shoppingListId}")
    public ShoppingListResource getShoppingList(@PathVariable UUID shoppingListId) {
        return shoppingListService.getShoppingList(shoppingListId);
    }

    //todo > return set instead of list?
    @GetMapping(value = "/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody NewShoppingListRequest newShoppingListRequest) {
        return shoppingListService.create(newShoppingListRequest);
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddCocktailRequest> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<AddCocktailRequest> addCocktailRequests) {
        return shoppingListService.addCocktails(shoppingListId, addCocktailRequests);
    }
}
