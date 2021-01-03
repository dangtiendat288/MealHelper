package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealhelper.MainActivity;
import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ReviewPlanAdapter;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.Objects;


public class ReviewPlanFragment extends Fragment {
    RecyclerView mRvReview;
    ReviewPlanAdapter mReviewPlanAdapter;
    MealViewModel mMealViewModel;
    Observer<Integer> mObserver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review_plan, container, false);
        mRvReview = v.findViewById(R.id.rvReviewPlan);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReviewPlanAdapter = new ReviewPlanAdapter(getActivity());
        mRvReview.setAdapter(mReviewPlanAdapter);
        mRvReview.setHasFixedSize(true);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mObserver = integer -> {
            Toast.makeText(getActivity(), "Remove meal successfully!", Toast.LENGTH_SHORT).show();
//            updateAddedMeal();
            mMealViewModel.fetchAddedMeals();
        };

        updateAddedMeal();


        mReviewPlanAdapter.setOnItemClickedListener(new ReviewPlanAdapter.OnItemClickedListener() {
            @Override
            public void onClicked(Meal meal) {

            }

            @Override
            public void onAddClicked(Meal meal) {

            }

            @Override
            public void onRemoveButtonClicked(Meal meal) {
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
                mMealViewModel.getUpdatedMeal().observe(getActivity(), mObserver);
//                mMealViewModel.deleteMeal(meal);
//                mMealViewModel.getDeletedMeal().observe(getActivity(), new Observer<Boolean>() {
//                    @Override
//                    public void onChanged(Boolean aBoolean) {
//                        updateMeal();
//                        Toast.makeText(getActivity(), "Remove meal successfully!", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }

    private void updateAddedMeal() {
        mMealViewModel.fetchAddedMeals();
        mMealViewModel.getAddedMeals().observe(getActivity(),
                meals -> mReviewPlanAdapter.submitList(meals));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ABC", "Review OnDetach");
        mMealViewModel.getUpdatedMeal().removeObserver(mObserver);
        mMealViewModel = null;
        MainActivity.reloadBuildMealFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ABC", "Review OnDestroy");
    }
}