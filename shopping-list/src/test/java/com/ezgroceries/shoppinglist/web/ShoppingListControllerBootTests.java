package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

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
        given(shoppingListService.findShoppingListById(shoppingListId))
                .willReturn(new ShoppingList(shoppingListId, "MisterG"));

        //act and assert
        mockMvc.perform(get("/shopping-lists/{shoppingListId}", shoppingListId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("shoppingListId").value(shoppingListId.toString()))
                .andExpect(jsonPath("name").value("MisterG"));

        //verify
        verify(shoppingListService, times(1)).findShoppingListById((shoppingListId));


    }
}
