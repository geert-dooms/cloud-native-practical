package com.ezgroceries.shoppinglist.shoppinglist.service;

import com.ezgroceries.shoppinglist.cocktail.controller.dto.AddCocktailResponse;
import com.ezgroceries.shoppinglist.meal.controller.dto.AddMealResponse;
import com.ezgroceries.shoppinglist.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.shoppinglist.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.NewShoppingListRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.AddCocktailRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.AddMealRequest;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import com.ezgroceries.shoppinglist.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingListService { //todo > use interface instead?

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;
    private final MealService mealService;
    private final ShoppingListMapper shoppingListMapper;

    public ShoppingListResource create(NewShoppingListRequest newShoppingListRequest, String username) {
        ShoppingList shoppingList = shoppingListRepository.save(shoppingListMapper.toShoppingList(newShoppingListRequest, username));
        return shoppingListMapper.toShoppingListResource(shoppingList);
    }

    @PostFilter("filterObject.username == authentication.name")
    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListResource> shoppingListResources = new ArrayList<>();
        findAllShoppingLists().forEach(shoppingList -> shoppingListResources.add(shoppingListMapper.toShoppingListResource(shoppingList)));
        return shoppingListResources;
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public ShoppingListResource getShoppingList(UUID shoppingListId) {
        return shoppingListMapper.toShoppingListResource(findOneShoppingList(shoppingListId));
    }

    public List<AddCocktailResponse> addCocktails(UUID shoppingListId, List<AddCocktailRequest> addCocktailRequests) {
        List<AddCocktailResponse> addCocktailResponses = new ArrayList<>();

        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isPresent()) {
            cocktailService.findCocktailsById(addCocktailRequests).forEach(cocktail -> {
                    shoppingList.get().addCocktail(cocktail);
                    addCocktailResponses.add(new AddCocktailResponse(cocktail.getCocktailId()));
                        });
            shoppingListRepository.save(shoppingList.get());
        }

        return addCocktailResponses;
    }

    public List<AddMealResponse> addMeals(UUID shoppingListId, List<AddMealRequest> addMealRequests) {

        List<AddMealResponse> addMealResponses = new ArrayList<>();

        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        //todo refactor isPresent
        if (shoppingList.isPresent()) {
            mealService.findMealsById(addMealRequests).forEach(meal -> {
                    shoppingList.get().addMeal(meal);
                    addMealResponses.add(new AddMealResponse(meal.getMealId()));
                        });
            shoppingListRepository.save(shoppingList.get());
        }
        return addMealResponses;
    }


    public ShoppingList findOneShoppingList(UUID shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);

        return shoppingList.orElseThrow();
    }

    public Iterable<ShoppingList> findAllShoppingLists() {
        return shoppingListRepository.findAll();
    }
}
