package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.MealPlanFragmentAdapter;
import com.example.mealhelper.databinding.FragmentFavoriteBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;


public class FavoriteFragment extends Fragment {
    FragmentFavoriteBinding mBinding;
    static MealViewModel mMealViewModel;
    MealPlanFragmentAdapter mMealAdapter;

    public static void updateFavMeals(){
        mMealViewModel.fetchFavMeals();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFavoriteBinding.inflate(inflater,container,false);
        View v = mBinding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mMealAdapter = new MealPlanFragmentAdapter(getActivity());

        mBinding.rvFav.setHasFixedSize(true);
        mBinding.rvFav.setAdapter(mMealAdapter);

        mMealAdapter.setOnItemClickedListener(meal -> {
            Bundle bundle = new Bundle();
            MealDetailFragment fragment = new MealDetailFragment();
            bundle.putParcelable("meal", meal);
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.linearLayoutMain, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        mMealViewModel.fetchFavMeals();
        mMealViewModel.getFavMeals().observe(getActivity(),meals -> {
            mMealAdapter.submitList(meals);
        });
    }
}