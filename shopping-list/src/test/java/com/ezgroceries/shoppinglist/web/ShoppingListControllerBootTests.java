package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resources.CocktailReference;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    @Disabled
    public void getShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG");
        testShoppingList.addIngredients(Arrays.asList("Alcohol"));

        given(shoppingListService.findShoppingListById(any(UUID.class)))
                .willReturn(testShoppingList);

        //act and assert
        mockMvc.perform(get("/shopping-lists/{shoppingListId}", shoppingListId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("name").value("MisterG"))
                .andExpect(jsonPath("ingredients").value(Arrays.asList("Alcohol")));

        //verify
        verify(shoppingListService, times(1)).findShoppingListById((any(UUID.class)));

    }

    @Test
    public void getAllShoppingLists() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, "MisterG");
        testShoppingList.addIngredients(Arrays.asList("Alcohol"));
        List<ShoppingList> testShoppingLists = Arrays.asList(testShoppingList);

        given(shoppingListService.getAllShoppingLists())
                .willReturn(testShoppingLists);

        //act and assert
        mockMvc.perform(get("/shopping-lists/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("$..name").value("MisterG"))
                .andExpect(jsonPath("$..ingredients" , hasItem(Arrays.asList("Alcohol"))));

        //verify
        verify(shoppingListService, times(1)).getAllShoppingLists();

    }

    @Test
    public void createShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        String testName = "MisterG";
        ShoppingList testShoppingList = new ShoppingList(shoppingListId, testName);

        given(shoppingListService.create(anyString()))
                .willReturn(testShoppingList);

        //act and assert
        mockMvc.perform(post("/shopping-lists/")
                .content(testName)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("name").value("MisterG"));

        //verify
        verify(shoppingListService, times(1)).create(anyString());

    }

    @Test
    @Disabled
    public void addCocktailsToShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();
        UUID cocktailId = UUID.randomUUID();
        CocktailReference testCocktailReference = new CocktailReference(cocktailId);
        List<CocktailReference> testCocktailReferences = Arrays.asList(testCocktailReference);

        //given(shoppingListService.findShoppingListById(any(UUID.class)).addIngredients(anyList()));

        //act and assert
        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails",shoppingListId)
                .content(asJsonString(testCocktailReferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..cocktailId").value(cocktailId.toString()));

        //verify
        // verify(shoppingListService, times(1)).create(anyString());

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

/*    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CocktailReference> addCocktailsToShoppingList(@PathVariable UUID shoppingListId, @RequestBody List<CocktailReference> cocktailReferences) {
        shoppingListService.findShoppingListById(shoppingListId).addCocktails(cocktailReferences);
        shoppingListService.findShoppingListById(shoppingListId).addIngredients(Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt","Triple Sec"));
        return cocktailReferences;
    }*/

}
