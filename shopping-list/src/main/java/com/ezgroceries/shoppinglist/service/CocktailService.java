package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.client.CocktailDBClient;
import com.ezgroceries.shoppinglist.converter.DrinkConverter;
import com.ezgroceries.shoppinglist.converter.CocktailMapper;
import com.ezgroceries.shoppinglist.client.CocktailDBResponse;
import com.ezgroceries.shoppinglist.dto.AddCocktailRequest;
import com.ezgroceries.shoppinglist.dto.CocktailResource;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.Drink;
import com.ezgroceries.shoppinglist.repository.CocktailRepository;
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

    //todo > REFACTOR later on during hardening lab
    public List<CocktailResource> searchCocktails(String search) {

        //perform new query to external API TheCocktailDB.com
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);


        List<CocktailResource> cocktailResources = new ArrayList<>();

        for (Drink drinks : cocktailDBResponse.getDrinks()) {
            //convert drink from TheCocktailDB to Cocktailresource
            CocktailResource cocktailResource = drinkConverter.convertDrinkToCocktail(drinks);


            //check cocktail database for this cocktail. if it exists > update with data from API, otherwise insert into cocktail table
            Optional<Cocktail> cocktail = cocktailRepository.findByDrinkId(drinks.getIdDrink());
            if (cocktail.isPresent()) {
                cocktailResource.setCocktailId(cocktail.get().getCocktailId());
                //todo > also update database with most recent details from thecocktaildb?
            } else {
                cocktailRepository.save(CocktailMapper.DtoToEntity(cocktailResource));
            }

            cocktailResources.add(cocktailResource);
        }
        return cocktailResources;
    }

    public List<Cocktail> findCocktailsById(List<AddCocktailRequest> addCocktailRequests) {
        List<Cocktail> cocktails = new ArrayList<>();
        //todo how to best handle optional?
        for (AddCocktailRequest addCocktailRequest : addCocktailRequests) {
            Optional<Cocktail> cocktail = cocktailRepository.findById(addCocktailRequest.getCocktailId());
            if (cocktail.isPresent()) {
                cocktails.add(cocktail.get());
            }
        }
        return cocktails;
    }
}
