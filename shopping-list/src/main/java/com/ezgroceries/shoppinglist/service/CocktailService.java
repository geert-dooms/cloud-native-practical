package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.CocktailDBClient;
import com.ezgroceries.shoppinglist.converter.CocktailDBResponseConverter;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocktailService {

    @Autowired
    private CocktailDBClient cocktailDBClient;

    @Autowired
    private CocktailDBResponseConverter cocktailDBResponseConverter;

    public List<Cocktail> searchCocktails(String search) {

        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);

        return cocktailDBResponseConverter.processSearchCocktailResponse(cocktailDBResponse);
    }
}
