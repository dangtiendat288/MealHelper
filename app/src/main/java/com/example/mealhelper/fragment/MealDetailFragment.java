package com.example.mealhelper.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ViewPagerFragmentAdapter;
import com.example.mealhelper.databinding.FragmentMealDetailBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.view.YoutubeActivity;
import com.example.mealhelper.viewModel.MealViewModel;
import com.google.android.material.tabs.TabLayoutMediator;


public class MealDetailFragment extends Fragment {
    FragmentMealDetailBinding mMealDetailBinding;
    MealViewModel mMealViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMealDetailBinding = FragmentMealDetailBinding.inflate(inflater, container, false);
        View v = mMealDetailBinding.getRoot();
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FavoriteFragment.updateFavMeals();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mMealDetailBinding.toolbar);
        mMealDetailBinding.toolbar.setNavigationIcon(R.drawable.ic_back_with_background);
        mMealDetailBinding.toolbar.setTitle("");

        mMealDetailBinding.toolbar.setNavigationOnClickListener(
                view -> getActivity().getSupportFragmentManager().popBackStack());

        Meal meal = this.getArguments().getParcelable("meal");
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);

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


        if(meal.getFav()){
            mMealDetailBinding.ivFav.setSelected(true);
        }
        else{
            mMealDetailBinding.ivFav.setSelected(false);
        }

        mMealDetailBinding.ivFav.setOnClickListener(view -> {
            if(!view.isSelected()){
                view.setSelected(true);
                meal.setFav(true);
                mMealViewModel.updateMeal(meal);
                Toast.makeText(getActivity(), "This meal is added to your favorite list!", Toast.LENGTH_SHORT).show();
            }
            else{
                view.setSelected(false);
                meal.setFav(false);
                mMealViewModel.updateMeal(meal);
                Toast.makeText(getActivity(), "This meal is removed from your favorite list!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}