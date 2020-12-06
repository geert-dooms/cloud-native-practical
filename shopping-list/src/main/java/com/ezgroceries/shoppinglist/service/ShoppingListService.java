package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.dto.mapper.ShoppingListMapper;
import com.ezgroceries.shoppinglist.dto.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.dto.response.AddCocktailResponse;
import com.ezgroceries.shoppinglist.dto.response.AddMealResponse;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.controller.request.NewShoppingListRequest;
import com.ezgroceries.shoppinglist.controller.request.AddCocktailRequest;
import com.ezgroceries.shoppinglist.controller.request.AddMealRequest;
import lombok.RequiredArgsConstructor;
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

    public ShoppingListResource create(NewShoppingListRequest newShoppingListRequest) {
        ShoppingList shoppingList = shoppingListRepository.save(shoppingListMapper.toShoppingList(newShoppingListRequest));
        return shoppingListMapper.toShoppingListResource(shoppingList);
    }

    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListResource> shoppingListResources = new ArrayList<>();
        shoppingListRepository.findAll().forEach(shoppingList -> shoppingListResources.add(shoppingListMapper.toShoppingListResource(shoppingList)));
        return shoppingListResources;
    }


    public ShoppingListResource getShoppingList(UUID shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isPresent()) {
            return shoppingListMapper.toShoppingListResource(shoppingList.get());
        } else {
            return null; //todo refactor (exception?)
        }
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
        if (shoppingList.isPresent()) {
            mealService.findMealsById(addMealRequests).forEach(meal -> {
                    shoppingList.get().addMeal(meal);
                    addMealResponses.add(new AddMealResponse(meal.getMealId()));
                        });
            shoppingListRepository.save(shoppingList.get());
        }
        return addMealResponses;
    }
}
