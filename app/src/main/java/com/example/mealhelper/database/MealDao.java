package com.example.mealhelper.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mealhelper.model.Ingredient;
import com.example.mealhelper.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface MealDao {

    @Insert
    Maybe<Long> insert(Meal meal);

    @Update
    Maybe<Integer> update(Meal meal);

    @Delete
    Maybe<Void> delete(Meal meal);

    @Insert
    Maybe<Long> insert(Ingredient ingredient);

    @Update
    Maybe<Integer> update(Ingredient ingredient);

    @Delete
    Maybe<Void> delete(Ingredient ingredient);

    @Query("SELECT * FROM ingredient_table ORDER BY ingredient")
    Maybe<List<Ingredient>> getAllIngredients();

    @Query("SELECT * FROM MEAL_TABLE ORDER BY strMeal")
    Maybe<List<Meal>> getAllMeals();

    @Query("SELECT * FROM MEAL_TABLE WHERE idMeal = :idMeal")
    Maybe<List<Meal>> getMealWithID(int idMeal);

    @Query("SELECT * FROM MEAL_TABLE WHERE strMeal LIKE + :initChar")
    Maybe<List<Meal>> getMealsStartWithAChar(String initChar);


//    @Query("SELECT * FROM MEAL_TABLE WHERE strMeal LIKE + 'H%'")
//    Maybe<List<Meal>> getMealsStartWithH();

    @Query("SELECT * FROM MEAL_TABLE WHERE strMeal LIKE + 'H%'")
    Maybe<List<Meal>> getMealsStartWithH();

    @Query("SELECT * FROM MEAL_TABLE WHERE isAdded = 1")
    Maybe<List<Meal>> getAddedMeals();

    @Query("SELECT * FROM MEAL_TABLE WHERE isFav = 1")
    Maybe<List<Meal>> getFavMeals();

    @Query("SELECT * FROM MEAL_TABLE WHERE isBuilt = 1")
    Maybe<List<Meal>> getBuiltMeals();


}

