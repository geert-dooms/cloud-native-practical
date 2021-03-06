package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.cocktail.web.in.CocktailController;
import com.ezgroceries.shoppinglist.cocktail.service.dto.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CocktailController.class)
@AutoConfigureTestDatabase
public class CocktailControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @Test
    public void searchCocktails() throws Exception {

        //arrange;
        UUID cocktailId = UUID.randomUUID();
        Set<String> ingredients = new HashSet<>(Arrays.asList("Salt", "Lime Juice", "Tequila", "Triple Sec"));
        String search = "Margarita";

        List<CocktailResource> testCocktailResources = Arrays.asList(new CocktailResource(
                        cocktailId,"123456" ,
                "Margarita",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", ingredients));

        given(cocktailService.searchCocktails(anyString())).willReturn(testCocktailResources);

        //act and assert
        mockMvc.perform(get("/cocktails")
                .param("search", search))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..cocktailId").value(cocktailId.toString()))
                .andExpect(jsonPath("$..name").value("Margarita"))
                .andExpect(jsonPath("$..glass").value("Cocktail glass"))
                .andExpect(jsonPath("$..instructions").value("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten.."))
                .andExpect(jsonPath("$..image").value("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg"))
                .andExpect(jsonPath("$..ingredients").exists());
        //todo > match op set werkt niet (met value) - of gaat dit wel met .equals?

        //verify
        verify(cocktailService, times(1)).searchCocktails(anyString());

    }





}
