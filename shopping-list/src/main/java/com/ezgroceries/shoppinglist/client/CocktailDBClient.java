package com.ezgroceries.shoppinglist.client;

import com.ezgroceries.shoppinglist.converter.DrinkConverter;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.Drink;
import com.ezgroceries.shoppinglist.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

}

@Component
@RequiredArgsConstructor
class CocktailDBClientFallback implements CocktailDBClient {

    private final CocktailRepository cocktailRepository;
    private final DrinkConverter drinkConverter;

    @Override
    public CocktailDBResponse searchCocktails(String search) {
        List<Drink> drinks = new ArrayList<>();

        List<Cocktail> cocktails = cocktailRepository.findByNameContainingIgnoreCase(search);
        for (Cocktail cocktail : cocktails) {
            drinks.add(drinkConverter.convertCocktailToDrink(cocktail));
        }

        return new CocktailDBResponse(drinks);
    }
}