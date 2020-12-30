package com.example.mealhelper.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ViewPagerFragmentAdapter;
import com.example.mealhelper.databinding.FragmentMealDetailBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.view.YoutubeActivity;
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
                            tab.setText("Tags");
                            break;
                    }
                }).attach();

        Glide.with(getActivity())
                .load(meal.getStrMealThumb())
                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(mMealDetailBinding.image);

        mMealDetailBinding.ivYoutube.setOnClickListener(view -> {
            if (meal.getStrYoutube().isEmpty()) {
                Toast.makeText(getActivity(), "There is no video available for this meal.", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                Intent intent = new Intent(getActivity(), YoutubeActivity.class);
                intent.putExtra("videoID", meal.getStrYoutube());
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }
}