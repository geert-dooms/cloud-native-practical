package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.MealResource;
import com.ezgroceries.shoppinglist.service.MealService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MealController.class)
public class MealControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @Test
    public void searchMeals() throws Exception {

        //arrange;
        UUID mealId = UUID.randomUUID();
        Set<String> ingredients = new HashSet<>(Arrays.asList("Vegetable Oil", "Beef Gravy", "Potatoes", "Cheese Curds"));
        String search = "Poutine";

        List<MealResource> testMealResources = Arrays.asList(new MealResource(
                mealId,"123456" ,
                "Poutine",
                "Cook",
                "https://www.themealdb.com/images/media/drink/wpxpvu1439905379.jpg",
                ingredients));

        given(mealService.searchMeals(anyString())).willReturn(testMealResources);

        //todo > best way to do this?
        //act and assert
        mockMvc.perform(get("/meals")
                .param("search", search))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..mealId").value(mealId.toString()))
                .andExpect(jsonPath("$..name").value("Poutine"))
                .andExpect(jsonPath("$..instructions").value("Cook"))
                .andExpect(jsonPath("$..image").value("https://www.themealdb.com/images/media/drink/wpxpvu1439905379.jpg"));
        //        .andExpect(jsonPath("$..ingredients", hasItem(ingredients)));

        //verify
        verify(mealService, times(1)).searchMeals(anyString());

    }





}
