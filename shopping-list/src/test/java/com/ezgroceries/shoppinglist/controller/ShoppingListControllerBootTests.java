package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.controller.api.ShoppingListController;
import com.ezgroceries.shoppinglist.dto.model.ShoppingListResource;
import com.ezgroceries.shoppinglist.dto.response.AddCocktailResponse;
import com.ezgroceries.shoppinglist.dto.response.AddMealResponse;
import com.ezgroceries.shoppinglist.controller.request.*;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingListController.class)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc(addFilters = false)
public class ShoppingListControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    public void getShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        Set<String> ingredients = new HashSet<>(Arrays.asList("Salt", "Lime Juice", "Tequila", "Triple Sec"));
        ShoppingListResource testShoppingList = new ShoppingListResource(shoppingListId, "MisterG", "misterg", ingredients);

        given(shoppingListService.getShoppingList(any(UUID.class)))
                .willReturn(testShoppingList);

        //act and assert
        mockMvc.perform(get("/shopping-lists/{shoppingListId}", shoppingListId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("$..name").value("MisterG"))
                .andExpect(jsonPath("$..ingredients", hasItem(Arrays.asList("Salt", "Lime Juice", "Tequila", "Triple Sec"))));

        //verify
        verify(shoppingListService, times(1)).getShoppingList((any(UUID.class)));

    }

    @Test
    public void getAllShoppingLists() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        Set<String> ingredients = new HashSet<>(Arrays.asList("Salt", "Lime Juice", "Tequila", "Triple Sec"));
        ShoppingListResource testShoppingList = new ShoppingListResource(shoppingListId, "MisterG", "misterg", ingredients);
        List<ShoppingListResource> testShoppingLists = new ArrayList<>(Arrays.asList(testShoppingList));

        given(shoppingListService.getAllShoppingLists()).willReturn(testShoppingLists);

        //act and assert
        mockMvc.perform(get("/shopping-lists/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("$..name").value("MisterG"))
                .andExpect(jsonPath("$..ingredients" , hasItem(Arrays.asList("Salt", "Lime Juice", "Tequila", "Triple Sec"))));

        //verify
        verify(shoppingListService, times(1)).getAllShoppingLists();

    }

    @Test
    public void createShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        String testName = "MisterG";
        NewShoppingListRequest newShoppingListRequest = new NewShoppingListRequest(testName);
        ShoppingListResource testShoppingListResource = new ShoppingListResource(shoppingListId, testName);

        given(shoppingListService.create(any(NewShoppingListRequest.class)))
                .willReturn(testShoppingListResource);

        //act and assert
        mockMvc.perform(post("/shopping-lists/")
                .content(asJsonString(newShoppingListRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("name").value("MisterG"));

        //verify
        verify(shoppingListService, times(1)).create(any(NewShoppingListRequest.class));

    }

    @Test
    public void addCocktailsToShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();

        UUID cocktailId = UUID.randomUUID();
        AddCocktailRequest testAddCocktailRequest = new AddCocktailRequest(cocktailId);
        AddCocktailResponse testAddCocktailResponse = new AddCocktailResponse(cocktailId);
        List<AddCocktailRequest> testAddCocktailRequests = Arrays.asList(testAddCocktailRequest);
        List<AddCocktailResponse> testAddCocktailResponses = Arrays.asList(testAddCocktailResponse);

        given(shoppingListService.addCocktails(shoppingListId, testAddCocktailRequests))
                .willReturn(testAddCocktailResponses);

        //act and assert
        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails",shoppingListId)
                .content(asJsonString(testAddCocktailRequests))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..cocktailId").exists());

        //verify
        verify(shoppingListService, times(1)).addCocktails(any(UUID.class), anyList());

    }

    @Test
    public void addMealsToShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();

        UUID mealId = UUID.randomUUID();
        AddMealRequest testAddMealRequest = new AddMealRequest(mealId);
        AddMealResponse testAddMealResponse = new AddMealResponse(mealId);
        List<AddMealRequest> testAddMealRequests = Arrays.asList(testAddMealRequest);
        List<AddMealResponse> testAddMealResponses = Arrays.asList(testAddMealResponse);

        given(shoppingListService.addMeals(shoppingListId, testAddMealRequests))
                .willReturn(testAddMealResponses);

        //act and assert
        mockMvc.perform(post("/shopping-lists/{shoppingListId}/meals",shoppingListId)
                .content(asJsonString(testAddMealRequests))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..mealId").exists());

        //verify
        verify(shoppingListService, times(1)).addMeals(any(UUID.class), anyList());

    }
    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
