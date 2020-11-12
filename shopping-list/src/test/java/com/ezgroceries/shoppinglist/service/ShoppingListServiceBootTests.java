package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.dto.CocktailReference;
import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repository.ShoppingListRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ShoppingListServiceBootTests {


    @InjectMocks
    private ShoppingListService shoppingListService;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private CocktailService cocktailService;


    @Test
    public void getAllShoppingListsTest() {
        //prepare shoppinglists
        List<ShoppingList> testShoppingLists = new ArrayList<>();

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(shoppingListId, "MisterG");
        Cocktail cocktail = new Cocktail(UUID.randomUUID(), "123456", "Margarita", new HashSet<>(Arrays.asList("Tequila")));
        shoppingList.addCocktail(cocktail);
        testShoppingLists.add(shoppingList);

        UUID shoppingList2Id = UUID.randomUUID();
        ShoppingList shoppingList2 = new ShoppingList(shoppingList2Id, "Jos");
        Cocktail cocktail2 = new Cocktail(UUID.randomUUID(), "654321", "White Russian", new HashSet<>(Arrays.asList("Vodka")));
        shoppingList2.addCocktail(cocktail2);
        testShoppingLists.add(shoppingList2);

        //prepare shoppinglistresources
        List<ShoppingListResource> testShoppingListResources = new ArrayList<>();

        testShoppingListResources.add(new ShoppingListResource(shoppingListId, "MisterG", new HashSet<>(Arrays.asList("Tequila"))));
        testShoppingListResources.add(new ShoppingListResource(shoppingList2Id, "Jos", new HashSet<>(Arrays.asList("Vodka"))));


        when(shoppingListRepository.findAll()).thenReturn(testShoppingLists);

        //execute
        List<ShoppingListResource> shoppingListResources = shoppingListService.getAllShoppingLists();

        //verify
        assertEquals(testShoppingListResources, shoppingListResources);

        verify(shoppingListRepository, times(1)).findAll();
    }

    @Test
    public void getShoppingListTest() {
        //prepare shoppinglist

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG");
        Cocktail cocktail = new Cocktail(UUID.randomUUID(), "123456", "Margarita", new HashSet<>(Arrays.asList("Tequila")));
        testShoppingList.addCocktail(cocktail);

        //prepare shoppinglistresource
        ShoppingListResource testShoppingListResource = new ShoppingListResource(shoppingListId, "MisterG", new HashSet<>(Arrays.asList("Tequila")));

        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(testShoppingList));

        //execute
        ShoppingListResource shoppingListResource = shoppingListService.getShoppingList(shoppingListId);

        //verify
        assertEquals(testShoppingListResource, shoppingListResource);

        verify(shoppingListRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    public void createShoppingListTest() {

        //prepare
        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG");

        ShoppingListResource testShoppingListResource = new ShoppingListResource(shoppingListId, "MisterG");

        when(shoppingListRepository.save(testShoppingList)).thenReturn(testShoppingList);

        //execute
        ShoppingListResource shoppingListResource = shoppingListService.create("MisterG");

        //verify
        //todo only testing on name because service will random generate uuid upon creation > other way?
        assertEquals(testShoppingListResource.getName(), shoppingListResource.getName());

        verify(shoppingListRepository, times(1)).save(any(ShoppingList.class));

    }

    @Test
    public void addCocktailsToShoppingList() {

        //prepare
        UUID cocktailId = UUID.randomUUID();
        UUID cocktailId2 = UUID.randomUUID();
        Cocktail cocktail = new Cocktail(cocktailId, "123456", "Margarita", new HashSet<>(Arrays.asList("Tequila")));
        Cocktail cocktail2 = new Cocktail(cocktailId2, "654321", "White Russian", new HashSet<>(Arrays.asList("Vodka")));

        List<Cocktail> testCocktails = new ArrayList<>();
        testCocktails.add(cocktail);
        testCocktails.add(cocktail2);

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG");
        testShoppingList.addCocktail(cocktail);
        testShoppingList.addCocktail(cocktail2);


        List<CocktailReference> testCocktailReferences = new ArrayList<>();
        testCocktailReferences.add(new CocktailReference(cocktailId));
        testCocktailReferences.add(new CocktailReference(cocktailId2));

        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(testShoppingList));
        when(cocktailService.findCocktailsById(testCocktailReferences)).thenReturn(testCocktails);
        when(shoppingListRepository.save(testShoppingList)).thenReturn(testShoppingList);

        //execute
        List<CocktailReference> cocktailReferences = shoppingListService.addCocktails(shoppingListId, testCocktailReferences);

        //assert

        assertEquals(testCocktailReferences, cocktailReferences);

        verify(shoppingListRepository, times(1)).findById(shoppingListId);
        verify(cocktailService, times(1)).findCocktailsById(testCocktailReferences);
        verify(shoppingListRepository, times(1)).save(testShoppingList);


    }


}
