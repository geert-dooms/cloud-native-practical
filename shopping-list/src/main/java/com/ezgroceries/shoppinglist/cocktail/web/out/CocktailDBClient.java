package com.ezgroceries.shoppinglist.cocktail.web.out;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.model.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.cocktail.web.out.dto.CocktailDBResponse;
import com.ezgroceries.shoppinglist.cocktail.web.out.dto.DrinkResource;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
        List<DrinkResource> drinkResources = new ArrayList<>();

        List<Cocktail> cocktails = cocktailRepository.findByNameContainingIgnoreCase(search);
        for (Cocktail cocktail : cocktails) {
            drinkResources.add(drinkConverter.convertCocktailToDrink(cocktail));
        }

        return new CocktailDBResponse(drinkResources);
    }
}