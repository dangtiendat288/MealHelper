package com.example.mealhelper.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.MealAdapter;
import com.example.mealhelper.databinding.FragmentBuildMealPlanBinding;
import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.viewModel.MealViewModel;

import java.util.List;


public class BuildMealPlanFragment extends Fragment {
    static private FragmentBuildMealPlanBinding mBuildMealPlanBinding;
    static MealViewModel mMealViewModel;
    static MealAdapter mMostPopularMealAdapter, mRecentlyCreatedMealAdapter, mBreakfastMealAdapter;
    static int mCountMeal;
    static Context mContext = null;
//    static View mBtnMealCart;
//    static TextView mTvMeal, mTvMealNo;
//    static final ExecutorService mExecutorService = Executors.newFixedThreadPool(4);
//    List<Meal> mMeals1, mMeals2 , mMeals3;

    public static void updateList() {
//        mMealViewModel.getAddedMeals().observe((LifecycleOwner) mContext, meals -> {
//            mCountMeal = meals.size();
//            Toast.makeText(mContext, "mCountMeal " + mCountMeal, Toast.LENGTH_SHORT).show();
//            mTvMealNo.setText(mCountMeal+"");
//        });
//        Log.d("ABC", mCountMeal+" mCountMeal");
//        mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(mCountMeal);
        mMealViewModel.fetchMealsStartWithAChar("H%");
        mMealViewModel.fetchMealsStartWithAChar("B%");
        mMealViewModel.fetchMealsStartWithAChar("C%");
        mMealViewModel.fetchAddedMeals();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ABC", "Builder OnCreateView");
        // Inflate the layout for this fragment
        mBuildMealPlanBinding = FragmentBuildMealPlanBinding.inflate(inflater, container, false);
        View v = mBuildMealPlanBinding.getRoot();
//        mBtnMealCart = v.findViewById(R.id.btnMealCart);
//        mTvMeal = mBtnMealCart.findViewById(R.id.tvMeal);
//        mTvMealNo = mBtnMealCart.findViewById(R.id.tvMealsNo);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ABC", "Builder OnActivityCreated");

//        mMeals1 = new ArrayList<>();
//        mMeals2 = new ArrayList<>();
//        mMeals3 = new ArrayList<>();
        mBuildMealPlanBinding.pullToRefresh.setOnRefreshListener(() -> {
            updateList();
            mBuildMealPlanBinding.pullToRefresh.setRefreshing(false);
        });

        mContext = getActivity();
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
        updateList();

        mMealViewModel.getInsertedMeal().observe(getActivity(), aLong -> {
            Log.d("ABC", aLong + "");
            updateList();
        });

        mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
            Log.d("ABC", integer + "");
            updateList();
        });

        mMealViewModel.getMealsStartWithH().observe(getActivity(), meals -> {
            mMostPopularMealAdapter.submitList(meals);
        });

        mMealViewModel.getMealsStartWithB().observe(getActivity(), meals -> {
            mRecentlyCreatedMealAdapter.submitList(meals);
        });

        mMealViewModel.getMealsStartWithC().observe(getActivity(), meals -> {
            mBreakfastMealAdapter.submitList(meals);
        });

//        mMealViewModel.fetchMostPopularMeal("h");
//
//        mMealViewModel.getMostPopularMeal().observe(getActivity(), new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
////                insertMealsFromWebService(meals);
//                for (Meal meal : mealList) {
////                    mMealViewModel.fetchMealWithID(meal.getIdMeal());
////                    mMealViewModel.getMealWithID().observe(getActivity(),meals -> {
////                        if(meals.size()==0){
//                    mMealViewModel.insertMeal(meal);
////                    mMealViewModel.getInsertedMeal().observe(getActivity(), aLong -> {
////                        Log.d("ABC", aLong + "");
////                    });
////                        }
////                    });
//                }
//
//            }
//        });
//
//
////        mMealViewModel.fetchMealsStartWithAChar("H%");
//        mMealViewModel.getMealsStartWithH().observe(getActivity(), meals -> {
//            mMostPopularMealAdapter.submitList(meals);
////            mMealViewModel.fetchMealsStartWithAChar("H%");
//        });
//
////        mMealViewModel.fetchMealStartWithH();
////        mMealViewModel.getMealsStartWithH().observe(getActivity(), meals -> {
////            mMostPopularMealAdapter.submitList(meals);
////        });
//
//        mMealViewModel.fetchRecentlyCreatedMeal("b");
//        mMealViewModel.getRecentlyCreatedMeal().observe(getActivity(), new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
////                mRecentlyCreatedMealAdapter.submitList(apiResponse.getMeals());
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
////                insertMealsFromWebService(mealList);
//                for (Meal meal : mealList) {
////                    mMealViewModel.fetchMealWithID(meal.getIdMeal());
////                    mMealViewModel.getMealWithID().observe(getActivity(),meals -> {
////                        if(meals.size()==0){
//                    mMealViewModel.insertMeal(meal);
////                    mMealViewModel.getInsertedMeal().observe(getActivity(), aLong -> {
////                        Log.d("ABC", aLong + "");
////                    });
//                }
////                        mMealViewModel.fetchMealsStartWithAChar("B%");
////                    });
////                }
//            }
//        });
//
////        mMealViewModel.fetchMealsStartWithAChar("B%");
//        mMealViewModel.getMealsStartWithB().observe(getActivity(), meals -> {
//            mRecentlyCreatedMealAdapter.submitList(meals);
////            mMealViewModel.fetchMealsStartWithAChar("B%");
//        });
//
//
//        mMealViewModel.fetchBreakfastMeal("c");
//        mMealViewModel.getBreakfastMeal().observe(getActivity(), new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
////                mBreakfastMealAdapter.submitList(apiResponse.getMeals());
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
////                insertMealsFromWebService(mealList);
//                for (Meal meal : mealList) {
////                    mMealViewModel.fetchMealWithID(meal.getIdMeal());
////                    mMealViewModel.getMealWithID().observe(getActivity(),meals -> {
////                        if(meals.size()==0){
//                    mMealViewModel.insertMeal(meal);
////                    mMealViewModel.getInsertedMeal().observe(getActivity(), aLong -> {
////                        Log.d("ABC", aLong + "");
////                    });
//                }
////                        mMealViewModel.fetchMealsStartWithAChar("C%");
////                    });
////                }
//            }
//        });
//
////        mMealViewModel.fetchMealsStartWithAChar("C%");
//        mMealViewModel.getMealsStartWithC().observe(getActivity(), meals -> {
//            mBreakfastMealAdapter.submitList(meals);
////            mMealViewModel.fetchMealsStartWithAChar("C%");
//        });
        mMostPopularMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
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
                addMealCount();
                meal.setIsAdded(true);
                mMealViewModel.updateMeal(meal);
//                mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
//                    Toast.makeText(getActivity(), integer + "", Toast.LENGTH_SHORT).show();
//                });

//                mMealViewModel.insertMeal(meal);
//                mMealViewModel.getInsertedMeal().observe(getActivity(), new Observer<Long>() {
//                    @Override
//                    public void onChanged(Long aLong) {
//                        Toast.makeText(getActivity(), aLong + "", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                minusMealCount();
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
//                mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
//                    Toast.makeText(getActivity(), integer + "", Toast.LENGTH_SHORT).show();
//                });

//                mMealViewModel.deleteMeal(meal);
//                mMealViewModel.getDeletedMeal().observe(getActivity(),aBoolean ->
//                        Toast.makeText(getActivity(), aBoolean.toString(), Toast.LENGTH_SHORT).show());
            }
        });

        mRecentlyCreatedMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
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
                addMealCount();
                meal.setIsAdded(true);
                mMealViewModel.updateMeal(meal);
//                mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
//                    Toast.makeText(getActivity(), integer + "", Toast.LENGTH_SHORT).show();
//                });

//                mMealViewModel.insertMeal(meal);
//                mMealViewModel.getInsertedMeal().observe(getActivity(), new Observer<Long>() {
//                    @Override
//                    public void onChanged(Long aLong) {
//                        Toast.makeText(getActivity(), aLong + "", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                minusMealCount();
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
//                mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
//                    Toast.makeText(getActivity(), integer + "", Toast.LENGTH_SHORT).show();
//                });

//                mMealViewModel.deleteMeal(meal);
//                mMealViewModel.getDeletedMeal().observe(getActivity(),aBoolean ->
//                        Toast.makeText(getActivity(), aBoolean.toString(), Toast.LENGTH_SHORT).show());
            }
        });

        mBreakfastMealAdapter.setOnItemClickedListener(new MealAdapter.OnItemClickedListener() {
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
                addMealCount();
                meal.setIsAdded(true);
                mMealViewModel.updateMeal(meal);
//                mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
//                    Toast.makeText(getActivity(), integer + "", Toast.LENGTH_SHORT).show();
//                });

//                mMealViewModel.insertMeal(meal);
//                mMealViewModel.getInsertedMeal().observe(getActivity(), new Observer<Long>() {
//                    @Override
//                    public void onChanged(Long aLong) {
//                        Toast.makeText(getActivity(), aLong + "", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onDeleteButtonClicked(Meal meal) {
                minusMealCount();
                meal.setIsAdded(false);
                mMealViewModel.updateMeal(meal);
//                mMealViewModel.getUpdatedMeal().observe(getActivity(), integer -> {
//                    Toast.makeText(getActivity(), integer + "", Toast.LENGTH_SHORT).show();
//                });

//                mMealViewModel.deleteMeal(meal);
//                mMealViewModel.getDeletedMeal().observe(getActivity(),aBoolean ->
//                        Toast.makeText(getActivity(), aBoolean.toString(), Toast.LENGTH_SHORT).show());
            }
        });

        //open cart fragment
        mBuildMealPlanBinding.btnMealCart.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(()->{
                    ReviewPlanFragment fragment = new ReviewPlanFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.linearLayoutMain, fragment)
                            .addToBackStack(null)
                            .commit();
                },100);

            }
        });

//        if (mCountMeal > 0) {
//                mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.VISIBLE);
//                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.VISIBLE);
//                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(mCountMeal);
//            }

//        if (mBuildMealPlanBinding.btnMealCart.tvMeals.getVisibility() == View.VISIBLE &&
//                mBuildMealPlanBinding.btnMealCart.tvMealsNo.getVisibility() == View.VISIBLE) {
//            mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(mCountMeal);
//        }

        mMealViewModel.fetchAddedMeals();
        mMealViewModel.getAddedMeals().observe(getActivity(), meals -> {
            List<Meal> mealList = meals;
            mCountMeal = mealList.size();
            Log.d("ABC","SIZE"+mCountMeal);
//            Toast.makeText(mContext, "mealCount: "+ mealCount, Toast.LENGTH_SHORT).show();

//            mTvMealNo.setVisibility(View.VISIBLE);
//            mTvMeal.setVisibility(View.VISIBLE);
            if (mCountMeal > 0) {
                mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.VISIBLE);
                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.VISIBLE);
                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(mCountMeal+"");
//                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(mealList.size());
            } else {
                mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.GONE);
                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.GONE);
            }
        });

//        mCountMeal.observe(getActivity(), integer -> {
//            if (integer == 0) {
//                mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.GONE);
//                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.GONE);
//            } else {
//                mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.VISIBLE);
//                mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.VISIBLE);
//            }
//            mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(integer);
//        });
    }

//    private synchronized void insertMealsFromWebService(List<Meal> mealList) {
//        for(Meal meal:mealList){
//            mMealViewModel.fetchMealWithID(meal.getIdMeal());
//            mMealViewModel.getMealWithID().observe(getActivity(),meals -> {
//                if(meals.size()==0){
//                    mExecutorService.execute(() -> mMealViewModel.insertMeal(meal));
//                    mMealViewModel.getInsertedMeal().observe(getActivity(),aLong -> {
//                        Log.d("ABC",aLong+"");
//                    });
//                }
//            });
//        }
//    }

    private void minusMealCount() {
//        mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(--mCountMeal + "");
//        if (mCountMeal == 0) {
//            mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.GONE);
//            mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.GONE);
//        }
//        mMealViewModel.fetchAddedMeals();
    }

    private void addMealCount() {
//        mBuildMealPlanBinding.btnMealCart.tvMealsNo.setText(++mCountMeal + "");
//        if (mCountMeal > 0) {
//            mBuildMealPlanBinding.btnMealCart.tvMeals.setVisibility(View.VISIBLE);
//            mBuildMealPlanBinding.btnMealCart.tvMealsNo.setVisibility(View.VISIBLE);
//        }
//        mMealViewModel.fetchAddedMeals();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBuildMealPlanBinding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ABC", "Builder OnCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ABC", "Builder OnStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ABC", "Builder OnPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ABC", "Builder OnStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ABC", "Builder OnDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ABC", "Builder OnDetach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ABC", "Build Meal Resume");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("ABC", "Build Meal Attach");
    }
}