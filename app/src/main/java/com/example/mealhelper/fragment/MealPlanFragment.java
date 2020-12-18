package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.MealAdapter;
import com.example.mealhelper.databinding.FragmentMealPlanBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;

public class MealPlanFragment extends Fragment {
    private FragmentMealPlanBinding mMealPlanBinding;
    public static MealViewModel mMealViewModel;
    MealAdapter mMealAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMealPlanBinding = FragmentMealPlanBinding.inflate(inflater,container,false);
        View v = mMealPlanBinding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mMealAdapter = new MealAdapter(getActivity());

        mMealPlanBinding.rvMealPlan.setHasFixedSize(true);
        mMealPlanBinding.rvMealPlan.setAdapter(mMealAdapter);

        mMealViewModel.fetchAllMeal();
        mMealViewModel.getAllMeal().observe(getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                Log.d("ABC","Here");
                mMealAdapter.submitList(meals);
            }
        });
    }
}