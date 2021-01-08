package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.MealAdapter;
import com.example.mealhelper.adapter.MealPlanFragmentAdapter;
import com.example.mealhelper.databinding.FragmentAllMealBinding;
import com.example.mealhelper.databinding.FragmentMealPlanBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;

public class AllMealFragment extends Fragment {
    private FragmentAllMealBinding mBinding;
    public static MealViewModel mMealViewModel;
    MealAdapter mMealAdapter;
    Observer<List<Meal>> mCartObserver;
    int mCountMeal = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentAllMealBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mMealAdapter = new MealAdapter(getActivity());

        mBinding.rvAllMeal.setHasFixedSize(true);
        mBinding.rvAllMeal.setAdapter(mMealAdapter);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.tbAllMeal);
        mBinding.tbAllMeal.setNavigationIcon(R.drawable.ic_close);
        mBinding.tbAllMeal.setTitle("");

        mBinding.tbAllMeal.setNavigationOnClickListener(
                view -> getActivity().getSupportFragmentManager().popBackStack());

        mCartObserver = meals -> {
            List<Meal> mealList = meals;
            mCountMeal = mealList.size();
            Log.d("ABC", "SIZE" + mCountMeal);
            if (mCountMeal > 0) {
                mBinding.btnMealCart.tvMeals.setVisibility(View.VISIBLE);
                mBinding.btnMealCart.tvMealsNo.setVisibility(View.VISIBLE);
                mBinding.btnMealCart.tvMealsNo.setText(mCountMeal + "");
            } else {
                mBinding.btnMealCart.tvMeals.setVisibility(View.GONE);
                mBinding.btnMealCart.tvMealsNo.setVisibility(View.GONE);
            }
        };

        mMealViewModel.fetchAllMeal();
        mMealViewModel.getAllMeal().observe(getActivity(), meals -> {
            mMealAdapter.submitList(meals);
        });

        mMealViewModel.getInsertedMeal().observe(getActivity(), aLong -> {
            Log.d("ABC", aLong + "");
            updateList();
        });

        mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
            Log.d("ABC", integer + "");
            updateList();
        });

        mMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
            @Override
            public void onClicked(Meal meal) {
                Bundle bundle = new Bundle();
                MealDetailFragment fragment = new MealDetailFragment();
                bundle.putParcelable("meal", meal);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.linearLayoutMain, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onAddClicked(Meal meal) {
                meal.setIsAdded(true);
                mMealViewModel.updateMeal(meal);
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
            }
        });

        mBinding.btnMealCart.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler(Looper.myLooper()).postDelayed(() -> {
                    ReviewPlanFragment fragment = new ReviewPlanFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.linearLayoutMain, fragment)
                            .addToBackStack(null)
                            .commit();
                }, 100);
            }
        });

        mMealViewModel.fetchAddedMeals();
        mMealViewModel.getAddedMeals().observe(getActivity(),mCartObserver);


    }

    private void updateList() {
        mMealViewModel.fetchAllMeal();
        mMealViewModel.fetchAddedMeals();

    }
}