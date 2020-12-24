package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ViewPagerFragmentAdapter;
import com.example.mealhelper.databinding.FragmentMealDetailBinding;
import com.example.mealhelper.model.Meal;
import com.google.android.material.tabs.TabLayoutMediator;


public class MealDetailFragment extends Fragment {
    FragmentMealDetailBinding mMealDetailBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMealDetailBinding = FragmentMealDetailBinding.inflate(inflater, container, false);
        View v = mMealDetailBinding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Meal meal = this.getArguments().getParcelable("meal");
        mMealDetailBinding.tvMealName.setText(meal.getStrMeal());
        mMealDetailBinding.detailPager.setAdapter(new ViewPagerFragmentAdapter(getActivity(), meal));
        new TabLayoutMediator(mMealDetailBinding.detailTabLayout, mMealDetailBinding.detailPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Ingredients");
                            break;
                        case 1:
                            tab.setText("Instructions");
                            break;
                        case 2:
                            tab.setText("Video");
                            break;
                    }
                }).attach();

        Glide.with(getActivity())
                .load(meal.getStrMealThumb())
                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(mMealDetailBinding.image);

    }
}