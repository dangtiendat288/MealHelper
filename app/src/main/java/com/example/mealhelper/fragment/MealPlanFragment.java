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
import com.example.mealhelper.adapter.MealPlanFragmentAdapter;
import com.example.mealhelper.databinding.FragmentMealPlanBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;

public class MealPlanFragment extends Fragment {
    private FragmentMealPlanBinding mMealPlanBinding;
    public static MealViewModel mMealViewModel;
    MealPlanFragmentAdapter mMealAdapter;

    public static void updateBuiltMeals(){
        mMealViewModel.fetchBuiltMeals();
    }

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
        Log.d("ABC","Color bg "+ getResources().getColor(R.color.colorPrimary));

        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mMealAdapter = new MealPlanFragmentAdapter(getActivity());

        mMealPlanBinding.rvMealPlan.setHasFixedSize(true);
        mMealPlanBinding.rvMealPlan.setAdapter(mMealAdapter);

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

        mMealViewModel.fetchBuiltMeals();
        mMealViewModel.getBuiltMeals().observe(getActivity(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
//                Log.d("ABC","Here");
                mMealAdapter.submitList(meals);
                if(meals.size()==0){
                    mMealPlanBinding.rvMealPlan.setVisibility(View.GONE);
                    mMealPlanBinding.tvMealPlan.setVisibility(View.GONE);
                    mMealPlanBinding.layoutFirstMeal.setVisibility(View.VISIBLE);
                }
                else{
                    mMealPlanBinding.rvMealPlan.setVisibility(View.VISIBLE);
                    mMealPlanBinding.tvMealPlan.setVisibility(View.VISIBLE);
                    mMealPlanBinding.layoutFirstMeal.setVisibility(View.GONE);
                }
            }
        });

        mMealPlanBinding.viewStartBuildNewPlan.btnStartBuildNewPlan.setOnClickListener(view -> {
            BuildMealPlanFragment fragment = new BuildMealPlanFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.linearLayoutMain, fragment)
                    .addToBackStack("buildMealPlan")
                    .commit();
        });
    }
}