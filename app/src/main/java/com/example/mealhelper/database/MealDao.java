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
}

