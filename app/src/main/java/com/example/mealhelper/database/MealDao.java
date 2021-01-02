package com.example.mealhelper.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM MEAL_TABLE ORDER BY idMeal")
    Maybe<List<Meal>> getAllMeals();

    @Query("SELECT * FROM MEAL_TABLE WHERE idMeal = :idMeal")
    Maybe<List<Meal>> getMealWithID(int idMeal);

    @Query("SELECT * FROM MEAL_TABLE WHERE strMeal LIKE + :initChar")
    Maybe<List<Meal>> getMealsStartWithAChar(String initChar);

    @Query("SELECT * FROM MEAL_TABLE WHERE strMeal LIKE + 'H%'")
    Maybe<List<Meal>> getMealsStartWithH();

    @Query("SELECT * FROM MEAL_TABLE WHERE strMeal LIKE + 'H%'")
    Maybe<List<Meal>> getMealsStartWithH();



}

