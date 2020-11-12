package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.CocktailReference;
import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        ShoppingListResource testShoppingList = new ShoppingListResource(shoppingListId, "MisterG", ingredients);

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
        ShoppingListResource testShoppingList = new ShoppingListResource(shoppingListId, "MisterG", ingredients);
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
        ShoppingListResource testShoppingList = new ShoppingListResource(shoppingListId, testName);

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
    public void addCocktailsToShoppingList() throws Exception {

        //arrange
        UUID shoppingListId = UUID.randomUUID();

        UUID cocktailId = UUID.randomUUID();
        CocktailReference testCocktailReference = new CocktailReference(cocktailId);
        List<CocktailReference> testCocktailReferences = Arrays.asList(testCocktailReference);

        given(shoppingListService.addCocktails(any(UUID.class), testCocktailReferences))
                .willReturn(testCocktailReferences);

        //act and assert
        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails",shoppingListId)
                .content(asJsonString(testCocktailReferences))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..cocktailId", hasItem(Arrays.asList(testCocktailReference))));

        //verify
        verify(shoppingListService, times(1)).addCocktails(any(UUID.class), testCocktailReferences);

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
