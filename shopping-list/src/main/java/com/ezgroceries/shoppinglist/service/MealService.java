package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.controller.client.MealDBClient;
import com.ezgroceries.shoppinglist.dto.mapper.MealDbConverter;
import com.ezgroceries.shoppinglist.dto.mapper.MealMapper;
import com.ezgroceries.shoppinglist.controller.client.MealDBResponse;
import com.ezgroceries.shoppinglist.controller.request.*;
import com.ezgroceries.shoppinglist.dto.model.MealResource;
import com.ezgroceries.shoppinglist.model.Meal;
import com.ezgroceries.shoppinglist.dto.model.MealDb;
import com.ezgroceries.shoppinglist.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealDBClient mealDBClient;
    private final MealDbConverter mealDbConverter;
    private final MealRepository mealRepository;
    private final MealMapper mealMapper;

    //todo > REFACTOR later on during hardening lab
    public List<MealResource> searchMeals(String search) {

        //perform new query to external API TheMealDB.com
        MealDBResponse mealDBResponse = mealDBClient.searchMeals(search);


        List<MealResource> mealResources = new ArrayList<>();

        for (MealDb mealDbs : mealDBResponse.getMealDbs()) {
            //convert mealDb from TheMealDB to Mealresource
            MealResource mealResource = mealDbConverter.convertMealDbToMeal(mealDbs);

            //check meal database for this meal. if it exists > update with data from API, otherwise insert into meal table
            Optional<Meal> meal = mealRepository.findByMealDbId(mealDbs.getIdMeal());
            if (meal.isPresent()) {
                mealResource.setMealId(meal.get().getMealId());
                //todo > also update database with most recent details from themealdb?
            } else {
                mealRepository.save(mealMapper.toMeal(mealResource));
            }

            mealResources.add(mealResource);
        }
        return mealResources;
    }

    public List<Meal> findMealsById(List<AddMealRequest> addMealRequests) {
        List<Meal> meals = new ArrayList<>();
        //todo how to best handle optional?
        for (AddMealRequest addMealRequest : addMealRequests) {
            Optional<Meal> meal = mealRepository.findById(addMealRequest.getMealId());
            if (meal.isPresent()) {
                meals.add(meal.get());
            }
        }
        return meals;
    }
}
