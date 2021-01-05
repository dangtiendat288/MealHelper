package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.GroceriesAdapter;
import com.example.mealhelper.databinding.FragmentGroceriesBinding;
import com.example.mealhelper.model.Ingredient;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.ArrayList;
import java.util.List;


public class GroceriesFragment extends Fragment {

    FragmentGroceriesBinding mBinding;
    static MealViewModel mMealViewModel;
    GroceriesAdapter mAdapterGroceries;
    List<Ingredient> mIngredientList;
    private Observer<Boolean> mDeletedIngredientObserver;

    public static void updateGroceries() {
        mMealViewModel.fetchAllIngredient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentGroceriesBinding.inflate(inflater, container, false);
        View v = mBinding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapterGroceries = new GroceriesAdapter(getActivity());
        mIngredientList = new ArrayList<>();
        mBinding.rvGroceries.setAdapter(mAdapterGroceries);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);

        mDeletedIngredientObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mMealViewModel.fetchAllIngredient();
            }
        };

        mMealViewModel.fetchAllIngredient();
        mMealViewModel.getAllIngredient().observe(getActivity(), ingredients -> {
            mAdapterGroceries.submitList(ingredients);
        });

        mAdapterGroceries.setOnItemClickedListener(new GroceriesAdapter.OnItemClickedListener() {
            @Override
            public void onCheckBoxClicked(Ingredient ingredient) {
//                new Handler(Looper.getMainLooper()).postDelayed(()->{
                    mMealViewModel.deleteIngredient(ingredient);
                    mMealViewModel.getDeletedIngredient().observe(getActivity(),mDeletedIngredientObserver);
//                    },2000);

            }
        });
    }


}