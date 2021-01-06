package com.example.mealhelper.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mealhelper.MainActivity;
import com.example.mealhelper.R;
import com.example.mealhelper.adapter.ReviewPlanAdapter;
import com.example.mealhelper.databinding.FragmentReviewPlanBinding;
import com.example.mealhelper.model.Ingredient;
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
        setHasOptionsMenu(true);
//        View v = inflater.inflate(R.layout.fragment_review_plan, container, false);
        mRvReview = v.findViewById(R.id.rvReviewPlan);
//        mLayoutBuildPlan = mBtnBuildThisMealPlan.findViewById(R.id.layoutBuildPlan);
//        mBtnBuildThisMealPlan = mLayoutBuildPlan.findViewById(R.id.btnBuildThisMealPlan);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.review_plan_toolbar_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.deleteAll){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View promptView = layoutInflater.inflate(R.layout.confirm_delete_dialog, null);
            AlertDialog.Builder deleteDialogBuilder =
                    new AlertDialog.Builder(getActivity(),R.style.RoundedCornersDialog);
            deleteDialogBuilder.setView(promptView);
            AlertDialog deleteDialog = deleteDialogBuilder.show();
            Button btnDelete = promptView.findViewById(R.id.btn_dialog_yes);
            Button btnCancel = promptView.findViewById(R.id.btn_dialog_no);
            btnDelete.setOnClickListener(view -> {
                for(Meal meal:mAddedMeals){
                    meal.setIsAdded(false);
                    mMealViewModel.updateMeal(meal);
                }

                deleteDialog.dismiss();
            });
            btnCancel.setOnClickListener(view -> {
                deleteDialog.dismiss();
            });

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mAddedMeals = new ArrayList<>();

        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.tbReviewPlan);
        mBinding.tbReviewPlan.setNavigationIcon(R.drawable.ic_back);
        mBinding.tbReviewPlan.setTitle("");

        mBinding.tbReviewPlan.setNavigationOnClickListener(
                view -> getActivity().getSupportFragmentManager().popBackStack());


        mReviewPlanAdapter = new ReviewPlanAdapter(getActivity());
        mRvReview.setAdapter(mReviewPlanAdapter);
        mRvReview.setHasFixedSize(true);
        mMealViewModel = new ViewModelProvider(getActivity()).get(MealViewModel.class);
        mRemoveMealObserver = integer -> {
//            Toast.makeText(getActivity(), "Remove meal successfully!", Toast.LENGTH_SHORT).show();
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

        mMealViewModel.getUpdatedMeal().observe(getActivity(), mRemoveMealObserver);


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
                    insertIngredient(meal);
                    meal.setIsBuilt(true);
                    meal.setIsAdded(false);
                    mMealViewModel.updateMeal(meal);
                }
                new Handler(Looper.myLooper()).postDelayed(() -> {
                    getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
                    if (meals.size() == 0) {
                        mBinding.viewBuildThisMealPlan.getRoot().setVisibility(View.GONE);
                    } else {
                        mBinding.viewBuildThisMealPlan.getRoot().setVisibility(View.VISIBLE);
                    }
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
        GroceriesFragment.updateGroceries();
        FavoriteFragment.updateFavMeals();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ABC", "Review OnDestroy");
    }

    void insertIngredient(Meal meal) {
        if (!meal.getIsBuilt()) {
            if (meal.getStrIngredient1() == null) {
            } else if (!meal.getStrIngredient1().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient1(), meal.getStrMeasure1()));
            if (meal.getStrIngredient2() == null) {
            } else if (!meal.getStrIngredient2().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient2(), meal.getStrMeasure2()));
            if (meal.getStrIngredient3() == null) {
            } else if (!meal.getStrIngredient3().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient3(), meal.getStrMeasure3()));
            if (meal.getStrIngredient4() == null) {
            } else if (!meal.getStrIngredient4().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient4(), meal.getStrMeasure4()));
            if (meal.getStrIngredient5() == null) {
            } else if (!meal.getStrIngredient5().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient5(), meal.getStrMeasure5()));
            if (meal.getStrIngredient6() == null) {
            } else if (!meal.getStrIngredient6().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient6(), meal.getStrMeasure6()));
            if (meal.getStrIngredient7() == null) {
            } else if (!meal.getStrIngredient7().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient7(), meal.getStrMeasure7()));
            if (meal.getStrIngredient8() == null) {
            } else if (!meal.getStrIngredient8().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient8(), meal.getStrMeasure8()));
            if (meal.getStrIngredient9() == null) {
            } else if (!meal.getStrIngredient9().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient9(), meal.getStrMeasure9()));
            if (meal.getStrIngredient10() == null) {
            } else if (!meal.getStrIngredient10().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient10(), meal.getStrMeasure10()));
            if (meal.getStrIngredient11() == null) {
            } else if (!meal.getStrIngredient11().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient11(), meal.getStrMeasure11()));
            if (meal.getStrIngredient12() == null) {
            } else if (!meal.getStrIngredient12().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient12(), meal.getStrMeasure12()));
            if (meal.getStrIngredient13() == null) {
            } else if (!meal.getStrIngredient13().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient13(), meal.getStrMeasure13()));
            if (meal.getStrIngredient14() == null) {
            } else if (!meal.getStrIngredient14().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient14(), meal.getStrMeasure14()));
            if (meal.getStrIngredient15() == null) {
            } else if (!meal.getStrIngredient15().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient15(), meal.getStrMeasure15()));
            if (meal.getStrIngredient16() == null) {
            } else if (!meal.getStrIngredient16().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient16(), meal.getStrMeasure16()));
            if (meal.getStrIngredient17() == null) {
            } else if (!meal.getStrIngredient17().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient17(), meal.getStrMeasure17()));
            if (meal.getStrIngredient18() == null) {
            } else if (!meal.getStrIngredient18().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient18(), meal.getStrMeasure18()));
            if (meal.getStrIngredient19() == null) {
            } else if (!meal.getStrIngredient19().isEmpty())
                mMealViewModel.insertIngredient(new Ingredient(meal.getStrIngredient19(), meal.getStrMeasure19()));
        }
    }
}