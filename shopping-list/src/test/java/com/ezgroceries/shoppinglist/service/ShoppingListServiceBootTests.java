package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import com.ezgroceries.shoppinglist.shoppinglist.service.ShoppingListMapper;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.cocktail.controller.dto.AddCocktailResponse;
import com.ezgroceries.shoppinglist.meal.controller.dto.AddMealResponse;
import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.meal.model.Meal;
import com.ezgroceries.shoppinglist.meal.service.MealService;
import com.ezgroceries.shoppinglist.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.shoppinglist.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.shoppinglist.service.ShoppingListService;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.AddCocktailRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.AddMealRequest;
import com.ezgroceries.shoppinglist.shoppinglist.controller.dto.NewShoppingListRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor
public class ShoppingListServiceBootTests {


    @InjectMocks
    private ShoppingListService shoppingListService;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private CocktailService cocktailService;

    @Mock
    private MealService mealService;


    private final ShoppingListMapper shoppingListMapper;


    @Test
    public void getAllShoppingListsTest() {
        //prepare shoppinglists
        List<ShoppingList> testShoppingLists = new ArrayList<>();

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(shoppingListId, "MisterG", "MisterG");
        Cocktail cocktail = new Cocktail(UUID.randomUUID(), "123456", "Margarita", new HashSet<>(Arrays.asList("Tequila")), "coupe", "shaken not stirred","image.jpg" );
        shoppingList.addCocktail(cocktail);
        testShoppingLists.add(shoppingList);

        UUID shoppingList2Id = UUID.randomUUID();
        ShoppingList shoppingList2 = new ShoppingList(shoppingList2Id, "Jos", "MisterG");
        Cocktail cocktail2 = new Cocktail(UUID.randomUUID(), "654321", "White Russian", new HashSet<>(Arrays.asList("Vodka")),"tumbler" ,"creamy" ,"dude.jpg" );
        shoppingList2.addCocktail(cocktail2);
        testShoppingLists.add(shoppingList2);

        //prepare shoppinglistresources
        List<ShoppingListResource> testShoppingListResources = new ArrayList<>();

        testShoppingListResources.add(new ShoppingListResource(shoppingListId, "MisterG","misterg" , new HashSet<>(Arrays.asList("Tequila"))));
        testShoppingListResources.add(new ShoppingListResource(shoppingList2Id, "Jos", "misterg", new HashSet<>(Arrays.asList("Vodka"))));


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
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG", "MisterG");
        Cocktail cocktail = new Cocktail(UUID.randomUUID(), "123456", "Margarita", new HashSet<>(Arrays.asList("Tequila")),"coupe", "shaken not stirred","image.jpg");
        testShoppingList.addCocktail(cocktail);

        //prepare shoppinglistresource
        ShoppingListResource testShoppingListResource = new ShoppingListResource(shoppingListId, "MisterG", "misterg", new HashSet<>(Arrays.asList("Tequila")));

        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(testShoppingList));

        //execute
        ShoppingListResource shoppingListResource = shoppingListService.getShoppingList(shoppingListId);

        //verify
        assertEquals(testShoppingListResource, shoppingListResource);

        verify(shoppingListRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    @Disabled
    public void createShoppingListTest() {
        // todo - fix
        //prepare
        NewShoppingListRequest newShoppingListRequest = new NewShoppingListRequest("MisterG");

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = shoppingListMapper.toShoppingList(newShoppingListRequest, "MisterG");


        ShoppingListResource testShoppingListResource = new ShoppingListResource(shoppingListId, "MisterG");

        when(shoppingListRepository.save(testShoppingList)).thenReturn(testShoppingList);

        //execute
        ShoppingListResource shoppingListResource = shoppingListService.create(newShoppingListRequest,"MisterG");

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
        Cocktail cocktail = new Cocktail(cocktailId, "123456", "Margarita", new HashSet<>(Arrays.asList("Tequila")),"coupe", "shaken not stirred","image.jpg");
        Cocktail cocktail2 = new Cocktail(cocktailId2, "654321", "White Russian", new HashSet<>(Arrays.asList("Vodka")),"tumbler" ,"creamy" ,"dude.jpg" );

        List<Cocktail> testCocktails = new ArrayList<>();
        testCocktails.add(cocktail);
        testCocktails.add(cocktail2);

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG", "MisterG");
        testShoppingList.addCocktail(cocktail);
        testShoppingList.addCocktail(cocktail2);


        List<AddCocktailRequest> testAddCocktailRequests = new ArrayList<>();
        testAddCocktailRequests.add(new AddCocktailRequest(cocktailId));
        testAddCocktailRequests.add(new AddCocktailRequest(cocktailId2));

        List<AddCocktailResponse> testAddCocktailResponses = new ArrayList<>();
        testAddCocktailResponses.add(new AddCocktailResponse(cocktailId));
        testAddCocktailResponses.add(new AddCocktailResponse(cocktailId2));

        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(testShoppingList));
        when(cocktailService.findCocktailsById(testAddCocktailRequests)).thenReturn(testCocktails);
        when(shoppingListRepository.save(testShoppingList)).thenReturn(testShoppingList);

        //execute
        List<AddCocktailResponse> addCocktailResponses= shoppingListService.addCocktails(shoppingListId, testAddCocktailRequests);

        //assert

        assertEquals(testAddCocktailResponses, addCocktailResponses);

        verify(shoppingListRepository, times(1)).findById(shoppingListId);
        verify(cocktailService, times(1)).findCocktailsById(testAddCocktailRequests);
        verify(shoppingListRepository, times(1)).save(testShoppingList);


    }

    @Test
    public void addMealsToShoppingList() {

        //prepare
        UUID mealId = UUID.randomUUID();
        UUID mealId2 = UUID.randomUUID();
        Meal meal = new Meal(mealId, "123456", "Poutine", new HashSet<>(Arrays.asList("Potato")), "Cook","image.jpg");
        Meal meal2 = new Meal(mealId2, "654321", "Pasta", new HashSet<>(Arrays.asList("Tomato")),"Boil" ,"picture.jpg" );

        List<Meal> testMeals = new ArrayList<>();
        testMeals.add(meal);
        testMeals.add(meal2);

        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG", "MisterG");
        testShoppingList.addMeal(meal);
        testShoppingList.addMeal(meal2);


        List<AddMealRequest> testAddMealRequests = new ArrayList<>();
        testAddMealRequests.add(new AddMealRequest(mealId));
        testAddMealRequests.add(new AddMealRequest(mealId2));

        List<AddMealResponse> testAddMealResponses = new ArrayList<>();
        testAddMealResponses.add(new AddMealResponse(mealId));
        testAddMealResponses.add(new AddMealResponse(mealId2));

        when(shoppingListRepository.findById(shoppingListId)).thenReturn(Optional.of(testShoppingList));
        when(mealService.findMealsById(testAddMealRequests)).thenReturn(testMeals);
        when(shoppingListRepository.save(testShoppingList)).thenReturn(testShoppingList);

        //execute
        List<AddMealResponse> addMealResponses = shoppingListService.addMeals(shoppingListId, testAddMealRequests);

        //assert

        assertEquals(testAddMealResponses, addMealResponses);

        verify(shoppingListRepository, times(1)).findById(shoppingListId);
        verify(mealService, times(1)).findMealsById(testAddMealRequests);
        verify(shoppingListRepository, times(1)).save(testShoppingList);


    }
}
