package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ReviewPlanAdapter;
import com.example.mealhelper.viewModel.MealViewModel;


public class ReviewPlanFragment extends Fragment {
    RecyclerView mRvReview;
    ReviewPlanAdapter mReviewPlanAdapter;
    MealViewModel mMealViewModel;
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
        mMealViewModel.fetchAllMeal();
        mMealViewModel.getAllMeal().observe(getActivity(),
                meals -> mReviewPlanAdapter.submitList(meals));

    }
}