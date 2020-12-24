package com.example.mealhelper.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mealhelper.fragment.IngredientsFragment;
import com.example.mealhelper.fragment.InstructionsFragment;
import com.example.mealhelper.fragment.TagsFragment;
import com.example.mealhelper.model.Meal;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    Meal mMeal;
    Fragment mFragment = null;

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity, Meal meal) {
        super(fragmentActivity);
        mMeal = meal;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 0:
                mFragment = new IngredientsFragment();
                setArguments();
                return mFragment;
            case 1:
                mFragment = new InstructionsFragment();
                setArguments();
                return mFragment;
            case 2:
                mFragment = new TagsFragment();
                setArguments();
                return mFragment;
        }
        return new IngredientsFragment();
    }

    private void setArguments() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("meal",mMeal);
        mFragment.setArguments(bundle);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
