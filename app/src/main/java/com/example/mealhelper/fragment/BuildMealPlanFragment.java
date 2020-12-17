package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.MealAdapter;
import com.example.mealhelper.databinding.FragmentBuildMealPlanBinding;
import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;


public class BuildMealPlanFragment extends Fragment {
    private FragmentBuildMealPlanBinding mBuildMealPlanBinding;
    MealViewModel mMealViewModel;
    MealAdapter mMostPopularMealAdapter, mRecentlyCreatedMealAdapter, mBreakfastMealAdapter;
    int mCountMeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBuildMealPlanBinding = FragmentBuildMealPlanBinding.inflate(inflater, container, false);
        View v = mBuildMealPlanBinding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBuildMealPlanBinding.rvMostPopular.setHasFixedSize(true);
        mBuildMealPlanBinding.rvRecentlyCreated.setHasFixedSize(true);
        mBuildMealPlanBinding.rvBreakfast.setHasFixedSize(true);

        mMostPopularMealAdapter = new MealAdapter(getActivity());
        mRecentlyCreatedMealAdapter = new MealAdapter(getActivity());
        mBreakfastMealAdapter = new MealAdapter(getActivity());

        mBuildMealPlanBinding.rvMostPopular.setAdapter(mMostPopularMealAdapter);
        mBuildMealPlanBinding.rvRecentlyCreated.setAdapter(mRecentlyCreatedMealAdapter);
        mBuildMealPlanBinding.rvBreakfast.setAdapter(mBreakfastMealAdapter);

        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mMealViewModel.fetchMostPopularMeal("h");
        mMealViewModel.getMostPopularMeal().observe(getActivity(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                mMostPopularMealAdapter.submitList(apiResponse.getMeals());
            }
        });
        mMealViewModel.fetchRecentlyCreatedMeal("b");
        mMealViewModel.getRecentlyCreatedMeal().observe(getActivity(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                mRecentlyCreatedMealAdapter.submitList(apiResponse.getMeals());
            }
        });
        mMealViewModel.fetchBreakfastMeal("c");
        mMealViewModel.getBreakfastMeal().observe(getActivity(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                mBreakfastMealAdapter.submitList(apiResponse.getMeals());
            }
        });

        mMostPopularMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
            @Override
            public void onClicked(Meal meal) {
                Bundle bundle = new Bundle();
                MealDetailFragment fragment = new MealDetailFragment();
                bundle.putParcelable("meal",meal);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.linearLayoutMain,fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onAddClicked(Meal meal) {
                addMealCount();
                mMealViewModel.insertMeal(meal);
                mMealViewModel.getInsertedMeal().observe(getActivity(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        Toast.makeText(getActivity(), aLong + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                minusMealCount();
                mMealViewModel.deleteMeal(meal);
                mMealViewModel.getDeletedMeal().observe(getActivity(),aBoolean ->
                        Toast.makeText(getActivity(), aBoolean.toString(), Toast.LENGTH_SHORT).show());
            }
        });

        mRecentlyCreatedMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
            @Override
            public void onClicked(Meal meal) {

            }

            @Override
            public void onAddClicked(Meal meal) {
                addMealCount();
                mMealViewModel.insertMeal(meal);
                mMealViewModel.getInsertedMeal().observe(getActivity(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        Toast.makeText(getActivity(), aLong + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                minusMealCount();
                mMealViewModel.deleteMeal(meal);
                mMealViewModel.getDeletedMeal().observe(getActivity(),aBoolean ->
                        Toast.makeText(getActivity(), aBoolean.toString(), Toast.LENGTH_SHORT).show());
            }
        });

        mBreakfastMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
            @Override
            public void onClicked(Meal meal) {

            }

            @Override
            public void onAddClicked(Meal meal) {
                addMealCount();
                mMealViewModel.insertMeal(meal);
                mMealViewModel.getInsertedMeal().observe(getActivity(), new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong) {
                        Toast.makeText(getActivity(), aLong + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                minusMealCount();
                mMealViewModel.deleteMeal(meal);
                mMealViewModel.getDeletedMeal().observe(getActivity(),aBoolean ->
                        Toast.makeText(getActivity(), aBoolean.toString(), Toast.LENGTH_SHORT).show());
            }
        });

        //open cart fragment
        mBuildMealPlanBinding.btnMealCart.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void minusMealCount() {
        mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(--mCountMeal+"");
        if(mCountMeal==0) {
            mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.GONE);
            mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.GONE);
        }
    }

    private void addMealCount() {
        mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(++mCountMeal+"");
        if(mCountMeal>0){
            mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.VISIBLE);
            mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBuildMealPlanBinding = null;
    }
}