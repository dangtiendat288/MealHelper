package com.example.mealhelper.repository;

import android.app.Application;

import com.example.mealhelper.api.ApiRequest;
import com.example.mealhelper.api.RetrofitInit;
import com.example.mealhelper.database.MealDao;
import com.example.mealhelper.database.MealDatabase;
import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Ingredient;
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

    public Maybe<Long> insert(Ingredient ingredient) {
        return mMealDao.insert(ingredient);
    }

    public Maybe<Integer> update(Ingredient ingredient) {
        return mMealDao.update(ingredient);
    }

    public Maybe<Void> delete(Ingredient ingredient) {
        return mMealDao.delete(ingredient);
    }

    public Maybe<List<Ingredient>> fetchAllIngredients() {
        return mMealDao.getAllIngredients();
    }

    public Maybe<List<Meal>> fetchAllMeals() {
        return mMealDao.getAllMeals();
    }

    public Maybe<List<Meal>> fetchMealWithID(int idMeal) {
        return mMealDao.getMealWithID(idMeal);
    }

    public Maybe<List<Meal>> fetchMealStartWithAChar(String initChar) {
        return mMealDao.getMealsStartWithAChar(initChar);
    }

    public Maybe<List<Meal>> fetchMealStartWithH() {
        return mMealDao.getMealsStartWithH();
    }

    public Maybe<List<Meal>> fetchAddedMeals() {
        return mMealDao.getAddedMeals();
    }

    public Maybe<List<Meal>> fetchFavMeals() {
        return mMealDao.getFavMeals();
    }

    public Maybe<List<Meal>> fetchBuiltMeals() {
        return mMealDao.getBuiltMeals();
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
