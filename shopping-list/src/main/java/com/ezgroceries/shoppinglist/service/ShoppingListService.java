package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.converter.ShoppingListMapper;
import com.ezgroceries.shoppinglist.dto.CocktailReference;
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
public class ShoppingListService { //todo > use interface instead

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListMapper shoppingListMapper;
    private final CocktailService cocktailService;

    public ShoppingListResource create(String name) {
        ShoppingListResource shoppingListResource = new ShoppingListResource(UUID.randomUUID(), name);
        //todo > is using new ok here?
        //todo > should randomUUID be inside the ShoppingList class? (or in a separate 'shoppinglistcreator' class?)
        shoppingListRepository.save(ShoppingListMapper.DtoToEntity(shoppingListResource));
        return shoppingListResource;
    }

    public List<ShoppingListResource> getAllShoppingLists() {
        List<ShoppingListResource> shoppingListResources = new ArrayList<>();
        shoppingListRepository.findAll().forEach(shoppingList -> shoppingListResources.add(ShoppingListMapper.EntityToDto(shoppingList)));
        return shoppingListResources;
    }

    public ShoppingListResource findShoppingListById(UUID shoppingListId) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        if (shoppingList.isPresent()) {
            return ShoppingListMapper.EntityToDto(shoppingList.get());
        } else {
            return null; //todo refactor (exception?)
        }
    }

    public List<CocktailReference> addCocktails(UUID shoppingListId, List<CocktailReference> cocktailReferences) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        System.out.println("shoppinglist" + shoppingList);
        if (shoppingList.isPresent()) {
            cocktailService.findCocktailsById(cocktailReferences).forEach(cocktail ->
                shoppingList.get().addCocktail(cocktail));

        }
        return cocktailReferences;
    }
}
