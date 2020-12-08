package com.ezgroceries.shoppinglist.meal.service;

import com.ezgroceries.shoppinglist.meal.service.dto.MealResource;
import com.ezgroceries.shoppinglist.meal.web.out.MealDBClient;
import com.ezgroceries.shoppinglist.meal.web.out.MealDbConverter;
import com.ezgroceries.shoppinglist.meal.web.out.dto.MealDBResponse;
import com.ezgroceries.shoppinglist.meal.model.Meal;
import com.ezgroceries.shoppinglist.meal.web.out.dto.MealDBResource;
import com.ezgroceries.shoppinglist.meal.model.repository.MealRepository;
import com.ezgroceries.shoppinglist.shoppinglist.web.in.dto.AddMealRequest;
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

        for (MealDBResource mealDbsResource : mealDBResponse.getMealDbs()) {
            //convert mealDb from TheMealDB to Mealresource
            MealResource mealResource = mealDbConverter.convertMealDbToMeal(mealDbsResource);

            //check meal database for this meal. if it exists > update with data from API, otherwise insert into meal table
            Optional<Meal> meal = mealRepository.findByMealDbId(mealDbsResource.getIdMeal());
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
        //todo refactor ispresent?
        for (AddMealRequest addMealRequest : addMealRequests) {
            Optional<Meal> meal = mealRepository.findById(addMealRequest.getMealId());
            if (meal.isPresent()) {
                meals.add(meal.get());
            }
        }
        return meals;
    }
}
