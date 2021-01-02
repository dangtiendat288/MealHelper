package com.example.mealhelper.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealhelper.model.Meal;

@Database(entities = Meal.class, version = 1)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase mInstance;
    public abstract MealDao getMealDao();

    public static MealDatabase getInstance(Context context){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    MealDatabase.class,"meal_database").build();
        }
        return mInstance;
    }
}
