package com.ezgroceries.shoppinglist.cocktail.service;

import com.ezgroceries.shoppinglist.cocktail.service.dto.CocktailResource;
import com.ezgroceries.shoppinglist.cocktail.web.out.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.web.out.DrinkConverter;
import com.ezgroceries.shoppinglist.cocktail.web.out.dto.CocktailDBResponse;
import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.web.out.dto.DrinkResource;
import com.ezgroceries.shoppinglist.cocktail.model.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.shoppinglist.web.in.dto.AddCocktailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CocktailService {

    private final CocktailDBClient cocktailDBClient;
    private final DrinkConverter drinkConverter;
    private final CocktailRepository cocktailRepository;
    private final CocktailMapper cocktailMapper;

    //todo > REFACTOR later on during hardening lab
    public List<CocktailResource> searchCocktails(String search) {

        //perform new query to external API TheCocktailDB.com
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);


        List<CocktailResource> cocktailResources = new ArrayList<>();

        for (DrinkResource drinks : cocktailDBResponse.getDrinks()) {
            //convert drink from TheCocktailDB to Cocktailresource
            CocktailResource cocktailResource = drinkConverter.convertDrinkToCocktail(drinks);

            //check cocktail database for this cocktail. if it exists > update with data from API, otherwise insert into cocktail table
            Optional<Cocktail> cocktail = cocktailRepository.findByDrinkId(drinks.getIdDrink());
            if (cocktail.isPresent()) {
                cocktailResource.setCocktailId(cocktail.get().getCocktailId());
                //todo > also update database with most recent details from thecocktaildb?
            } else {
                cocktailRepository.save(cocktailMapper.toCocktail(cocktailResource));
            }

            cocktailResources.add(cocktailResource);
        }
        return cocktailResources;
    }

    public List<Cocktail> findCocktailsById(List<AddCocktailRequest> addCocktailRequests) {
        List<Cocktail> cocktails = new ArrayList<>();
        //todo refactor ispresent?
        for (AddCocktailRequest addCocktailRequest : addCocktailRequests) {
            Optional<Cocktail> cocktail = cocktailRepository.findById(addCocktailRequest.getCocktailId());
            if (cocktail.isPresent()) {
                cocktails.add(cocktail.get());
            }
        }
        return cocktails;
    }
}
