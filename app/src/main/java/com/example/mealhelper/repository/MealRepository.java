package com.example.mealhelper.repository;

import android.app.Application;

import com.example.mealhelper.api.ApiRequest;
import com.example.mealhelper.api.RetrofitInit;
import com.example.mealhelper.database.MealDao;
import com.example.mealhelper.database.MealDatabase;
import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public class MealRepository {
    private static MealRepository mInstance;
    private ApiRequest mApiRequest;
    private MealDao mMealDao;

    private MealRepository(Application application) {
        mApiRequest = RetrofitInit.getInstance();
        mMealDao = MealDatabase.getInstance(application).getMealDao();

    }

    public static MealRepository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new MealRepository(application);
        }
        return mInstance;
    }

    public Maybe<Long> insert(Meal meal) {
        return mMealDao.insert(meal);
    }

    public Maybe<Integer> update(Meal meal) {
        return mMealDao.update(meal);
    }

    public Maybe<Void> delete(Meal meal) {
        return mMealDao.delete(meal);
    }

    public Maybe<List<Meal>> fetchAllMeals() {
        return mMealDao.getAllMeals();
    }

    public Maybe<ApiResponse> fetchMostPopularMeal(String s) {
        return mApiRequest.fetchMostPopularMeal(s);
    }

    public Maybe<ApiResponse> fetchRecentlyCreatedMeal(String s) {
        return mApiRequest.fetchRecentlyCreatedMeal(s);
    }

    public Maybe<ApiResponse> fetchBreakfastMeal(String s) {
        return mApiRequest.fetchBreakfastMeal(s);
    }
}
