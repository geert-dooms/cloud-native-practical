package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.converter.ShoppingListMapper;
import com.ezgroceries.shoppinglist.dto.AddCocktailRequest;
import com.ezgroceries.shoppinglist.dto.NewShoppingListRequest;
import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repository.ShoppingListRepository;
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

    public ShoppingListResource create(NewShoppingListRequest newShoppingListRequest) {
        ShoppingList shoppingList = shoppingListRepository.save(ShoppingListMapper.DtoToEntity(newShoppingListRequest));
        return ShoppingListMapper.EntityToDto(shoppingList);
    }

    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListResource> shoppingListResources = new ArrayList<>();
        shoppingListRepository.findAll().forEach(shoppingList -> shoppingListResources.add(ShoppingListMapper.EntityToDto(shoppingList)));
        return shoppingListResources;
    }

    public ShoppingListResource getShoppingList(UUID shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isPresent()) {
            return ShoppingListMapper.EntityToDto(shoppingList.get());
        } else {
            return null; //todo refactor (exception?)
        }
    }

    public List<AddCocktailRequest> addCocktails(UUID shoppingListId, List<AddCocktailRequest> addCocktailRequests) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isPresent()) {
            cocktailService.findCocktailsById(addCocktailRequests).forEach(cocktail ->
                shoppingList.get().addCocktail(cocktail));
            shoppingListRepository.save(shoppingList.get());
        }
        return addCocktailRequests;
    }
}
