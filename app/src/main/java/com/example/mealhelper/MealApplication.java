package com.example.mealhelper;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;

public class MealApplication extends Application {
//    MealViewModel mMealViewModel;
//    Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
//        mContext = getApplicationContext();
//        mMealViewModel = new ViewModelProvider((ViewModelStoreOwner) mContext).get(MealViewModel.class);
//
//        mMealViewModel.fetchMostPopularMeal("h");
//        mMealViewModel.getMostPopularMeal().observe((LifecycleOwner) mContext, new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
//                for (Meal meal : mealList) {
//                    mMealViewModel.insertMeal(meal);
//                }
//            }
//        });
//
//        mMealViewModel.fetchRecentlyCreatedMeal("b");
//        mMealViewModel.getRecentlyCreatedMeal().observe((LifecycleOwner) mContext, new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
//                for (Meal meal : mealList) {
//                    mMealViewModel.insertMeal(meal);
//                }
//            }
//        });
//
//        mMealViewModel.fetchBreakfastMeal("c");
//        mMealViewModel.getBreakfastMeal().observe((LifecycleOwner) mContext, new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
//                for (Meal meal : mealList) {
//                    mMealViewModel.insertMeal(meal);
//                }
//            }
//        });


    }
}
