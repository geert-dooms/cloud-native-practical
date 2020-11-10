package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.CocktailDBClient;
import com.ezgroceries.shoppinglist.converter.DrinkConverter;
import com.ezgroceries.shoppinglist.converter.CocktailMapper;
import com.ezgroceries.shoppinglist.dto.CocktailDBResponse;
import com.ezgroceries.shoppinglist.dto.CocktailReference;
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

    public List<CocktailResource> searchCocktails(String search) {

        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        List<CocktailResource> cocktailResources = new ArrayList<>();

        for (Drink drinks : cocktailDBResponse.getDrinks()) {
            Optional<Cocktail> cocktail = cocktailRepository.findByDrinkId(drinks.getIdDrink());
            if (cocktail.isPresent()) {
                //todo should the existing cocktail in DB not be updated with the new fetch from cocktailDB to have the most recent data?
                cocktailResources.add(CocktailMapper.EntitytoDto(cocktail.get()));
            } else {
                CocktailResource cocktailResource = drinkConverter.convertDrinkToCocktail(drinks);
                cocktailResources.add(cocktailResource);
                cocktailRepository.save(CocktailMapper.DtoToEntity(cocktailResource));
            }
        }
        return cocktailResources;
    }

    public List<Cocktail> findCocktailsById(List<CocktailReference> cocktailReferences) {
        List<Cocktail> cocktails = new ArrayList<>();
        //todo how to best handle optional?
        for (CocktailReference cocktailReference : cocktailReferences) {
            Optional<Cocktail> cocktail = cocktailRepository.findById(cocktailReference.getCocktailId());
            if (cocktail.isPresent()) {
                cocktails.add(cocktail.get());
            }
        }
        return cocktails;
    }
}
