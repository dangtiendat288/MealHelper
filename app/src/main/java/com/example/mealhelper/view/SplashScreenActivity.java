package com.example.mealhelper.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.mealhelper.MainActivity;
import com.example.mealhelper.R;
import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {
    public static final int duration = 2000;
    MealViewModel mMealViewModel;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        fetchMealsFromAPI();
        splashScreenStart();
    }


    private void fetchMealsFromAPI() {
        mContext = SplashScreenActivity.this;
        mMealViewModel = new ViewModelProvider((ViewModelStoreOwner) mContext).get(MealViewModel.class);

        mMealViewModel.fetchMostPopularMeal("h");
        mMealViewModel.getMostPopularMeal().observe((LifecycleOwner) mContext, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                List<Meal> mealList = apiResponse.getMeals();
                //add meal
                for (Meal meal : mealList) {
                    mMealViewModel.insertMeal(meal);
                }
            }
        });

        mMealViewModel.fetchRecentlyCreatedMeal("b");
        mMealViewModel.getRecentlyCreatedMeal().observe((LifecycleOwner) mContext, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                List<Meal> mealList = apiResponse.getMeals();
                //add meal
                for (Meal meal : mealList) {
                    mMealViewModel.insertMeal(meal);
                }
            }
        });

        mMealViewModel.fetchBreakfastMeal("c");
        mMealViewModel.getBreakfastMeal().observe((LifecycleOwner) mContext, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                List<Meal> mealList = apiResponse.getMeals();
                //add meal
                for (Meal meal : mealList) {
                    mMealViewModel.insertMeal(meal);
                }
            }
        });
    }


    private void splashScreenStart() {
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity
                        .this, MainActivity.class));
                finish();
            }
        }, duration);
    }
}
