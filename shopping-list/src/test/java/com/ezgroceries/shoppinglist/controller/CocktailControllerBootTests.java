package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.CocktailResource;
import com.ezgroceries.shoppinglist.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequiredArgsConstructor
@WebMvcTest(CocktailController.class)
public class CocktailControllerBootTests {

    private final MockMvc mockMvc;

    @MockBean
    private final CocktailService cocktailService;

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

        //todo > best way to do this?
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
                .andExpect(jsonPath("$..ingredients", hasItem(Arrays.asList("Salt", "Lime Juice", "Tequila", "Triple Sec"))));
        //todo > match op set werkt niet

        //verify
        verify(cocktailService, times(1)).searchCocktails(anyString());

    }





}
