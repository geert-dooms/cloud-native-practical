package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.dto.CocktailResource;
import com.ezgroceries.shoppinglist.service.CocktailService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocktailController {

    private CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(value = "/cocktails", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CocktailResource> searchCocktails(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
    }
}
