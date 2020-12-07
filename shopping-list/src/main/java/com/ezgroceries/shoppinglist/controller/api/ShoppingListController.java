package com.ezgroceries.shoppinglist.controller.api;

import com.ezgroceries.shoppinglist.dto.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.dto.response.AddCocktailResponse;
import com.ezgroceries.shoppinglist.dto.response.AddMealResponse;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.controller.request.*;
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
    public List<AddCocktailResponse> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<AddCocktailRequest> addCocktailRequests) {
        return shoppingListService.addCocktails(shoppingListId, addCocktailRequests);
    }

    @PostMapping(value = "/shopping-lists/{shoppingListId}/meals")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddMealResponse> addMealsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<AddMealRequest> addMealRequests) {
        return shoppingListService.addMeals(shoppingListId, addMealRequests);
    }
}
