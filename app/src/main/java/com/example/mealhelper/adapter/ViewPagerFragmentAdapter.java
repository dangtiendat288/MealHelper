package com.example.mealhelper.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mealhelper.fragment.BuildMealPlanFragment;
import com.example.mealhelper.fragment.GroceriesFragment;
import com.example.mealhelper.fragment.MealPlanFragment;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {
    int mLength;
    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity, int length) {
        super(fragmentActivity);
        mLength = length;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MealPlanFragment();
            case 1:
                return new GroceriesFragment();
            case 2:
                return new BuildMealPlanFragment();
        }
        return new MealPlanFragment();
    }

    @Override
    public int getItemCount() {
        return mLength;
    }
}
