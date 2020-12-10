package com.ezgroceries.shoppinglist.shoppinglist.controller;

import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.cocktail.controller.dto.AddCocktailResponse;
import com.ezgroceries.shoppinglist.meal.controller.dto.AddMealResponse;
import com.ezgroceries.shoppinglist.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.AddCocktailRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.AddMealRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.NewShoppingListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping(value = "/shopping-lists")
    public List<ShoppingListResource> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody NewShoppingListRequest newShoppingListRequest, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return shoppingListService.create(newShoppingListRequest, userDetails.getUsername());
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
