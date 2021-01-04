package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealhelper.MainActivity;
import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ReviewPlanAdapter;
import com.example.mealhelper.databinding.FragmentReviewPlanBinding;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ReviewPlanFragment extends Fragment {
    RecyclerView mRvReview;
    ReviewPlanAdapter mReviewPlanAdapter;
    MealViewModel mMealViewModel;
    List<Meal> mAddedMeals;
    Observer<Integer> mRemoveMealObserver;
    Observer<List<Meal>> mBuildMealObserver;
    //    Button mBtnBuildThisMealPlan;
//    ConstraintLayout mLayoutBuildPlan;
    FragmentReviewPlanBinding mBinding;
//    final ExecutorService mExecutorService = Executors.newFixedThreadPool(4);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentReviewPlanBinding.inflate(inflater, container, false);
        View v = mBinding.getRoot();
//        View v = inflater.inflate(R.layout.fragment_review_plan, container, false);
        mRvReview = v.findViewById(R.id.rvReviewPlan);
//        mLayoutBuildPlan = mBtnBuildThisMealPlan.findViewById(R.id.layoutBuildPlan);
//        mBtnBuildThisMealPlan = mLayoutBuildPlan.findViewById(R.id.btnBuildThisMealPlan);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mAddedMeals = new ArrayList<>();
        mReviewPlanAdapter = new ReviewPlanAdapter(getActivity());
        mRvReview.setAdapter(mReviewPlanAdapter);
        mRvReview.setHasFixedSize(true);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mRemoveMealObserver = integer -> {
            Toast.makeText(getActivity(), "Remove meal successfully!", Toast.LENGTH_SHORT).show();
//            updateAddedMeal();
            mMealViewModel.fetchAddedMeals();
        };

        mBuildMealObserver = meals -> {
            for (Meal meal : meals) {
                meal.setIsBuilt(true);
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
            }
//            MealPlanFragment.updateBuiltMeals();
        };

//        mBuildMealObserver = meals -> {
//            MainActivity.mExecutorService.execute(()->{
//                for (Meal meal : meals) {
//                    meal.setIsBuilt(true);
//                    mMealViewModel.updateMeal(meal);
//                }
//            });
//            getActivity().getSupportFragmentManager().popBackStack();
//        };

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
//                mAddedMeals.remove(meal);
//                mReviewPlanAdapter.submitList(mAddedMeals);
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
                mMealViewModel.getUpdatedMeal().observe(getActivity(), mRemoveMealObserver);

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

        mBinding.viewBuildThisMealPlan.btnBuildThisMealPlan.setOnClickListener(view -> {
//            mMealViewModel.fetchAddedMeals();
//            mMealViewModel.getAddedMeals().observe(getActivity(), mBuildMealObserver);
            if (mAddedMeals.size() == 0) {
                Toast.makeText(getActivity(), "Please select a meal!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                for (Meal meal : mAddedMeals) {
                    meal.setIsBuilt(true);
                    meal.setIsAdded(false);
                    mMealViewModel.updateMeal(meal);
                }
                new Handler().postDelayed(() -> {
                    getActivity().getSupportFragmentManager().popBackStack();
                }, 200);
            }
//        Toast.makeText(getActivity(), "Btn build Clicked", Toast.LENGTH_SHORT).show();

        });
    }

    private void updateAddedMeal() {
        mMealViewModel.fetchAddedMeals();
        mMealViewModel.getAddedMeals().observe(getActivity(),
                meals -> {
                    mAddedMeals = meals;
                    if(meals.size()==0){
                        mBinding.viewBuildThisMealPlan.getRoot().setVisibility(View.GONE);
                    }
                    else{mBinding.viewBuildThisMealPlan.getRoot().setVisibility(View.VISIBLE);}
                    mReviewPlanAdapter.submitList(meals);

                }
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ABC", "Review OnDetach");
        mMealViewModel.getUpdatedMeal().removeObserver(mRemoveMealObserver);
        mMealViewModel.getBuiltMeals().removeObserver(mBuildMealObserver);
        mMealViewModel = null;
//        MainActivity.reloadBuildMealFragment();
        MealPlanFragment.updateBuiltMeals();
        BuildMealPlanFragment.updateList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ABC", "Review OnDestroy");
    }
}