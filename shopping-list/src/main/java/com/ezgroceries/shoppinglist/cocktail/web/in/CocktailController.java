package com.ezgroceries.shoppinglist.cocktail.web.in;

import com.ezgroceries.shoppinglist.cocktail.service.dto.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CocktailController {

    private final CocktailService cocktailService;

    @GetMapping(value = "/cocktails", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CocktailResource> searchCocktails(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
    }
}
