package com.example.mealhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mealhelper.databinding.ActivityMainBinding;
import com.example.mealhelper.fragment.BuildMealPlanFragment;
import com.example.mealhelper.fragment.GroceriesFragment;
import com.example.mealhelper.fragment.MealPlanFragment;
import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.repository.MealRepository;
import com.example.mealhelper.viewModel.MealViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

//    public static Observer<List<Meal>> mBuildMealObserver;
    public static MealViewModel mMealViewModel;
    ActivityMainBinding mMainBinding;
    public static final ExecutorService mExecutorService = Executors.newFixedThreadPool(4);
//    MealViewModel mMealViewModel;
//    MealRepository mMealRepository;
    //    private String[] titles;
//    ViewPagerFragmentAdapter mViewPagerAdapter;
    final Fragment fragment1 = new MealPlanFragment();
    final Fragment fragment2 = new GroceriesFragment();
    final Fragment fragment3 = new BuildMealPlanFragment();
//    final Fragment[] fragments = {fragment1,fragment2,fragment3};
    FragmentManager mFragmentManager;
    Fragment active = fragment1;

    public static void reloadBuildMealFragment(){
        BuildMealPlanFragment.updateList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        Log.d("FFF", "Android SDK: " + sdkVersion + " (" + release +")");
        mMealViewModel = new ViewModelProvider(this).get(MealViewModel.class);
//        mBuildMealObserver = meals -> {
//            MainActivity.mExecutorService.execute(()->{
//                for (Meal meal : meals) {
//                    meal.setIsBuilt(true);
//                    meal.setIsAdded(false);
//                    mMealViewModel.updateMeal(meal);
//                }
//            });
//        MealPlanFragment.updateBuiltMeals();
//        };

//        mMealViewModel = new ViewModelProvider(MainActivity.this).get(MealViewModel.class);
//        mMealViewModel.fetchMostPopularMeal("h");
//        mMealViewModel.getMostPopularMeal().observe(MainActivity.this, new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(ApiResponse apiResponse) {
//                List<Meal> mealList = apiResponse.getMeals();
//                //add meal
//
//                for(Meal meal:mealList){
//                    mMealViewModel.fetchMealWithID(meal.getIdMeal());
//                    mMealViewModel.getMealWithID().observe(MainActivity.this,meals -> {
//                        if(meals.size()==0){
//                            mMealViewModel.insertMeal(meal);
//                            mMealViewModel.getInsertedMeal().observe(MainActivity.this,aLong -> {
//                                Log.d("ABC",aLong+"");
//                            });
//                        }
//                    });
//                }
//            }
//        });

//        mMealRepository = MealRepository.getInstance(getApplication());
//        mMealRepository.fetchMostPopularMeal("h")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MaybeObserver<ApiResponse>() {
//                    @Override
//                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ApiResponse apiResponse) {
//                        List<Meal> mealList = apiResponse.getMeals();
//                        for(Meal mealItem:mealList){
//                            mMealRepository.fetchMealWithID(mealItem.getIdMeal())
//                                    .subscribeOn(Schedulers.io())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(new MaybeObserver<List<Meal>>() {
//                                        @Override
//                                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                                        }
//
//                                        @Override
//                                        public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
//                                            if(meals.size()==0){
//                                                mMealRepository.insert(mealItem);
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                                        }
//
//                                        @Override
//                                        public void onComplete() {
//
//                                        }
//                                    });
//                                }
//                            }
//
//
//                    @Override
//                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

//        titles = new String[]{"Meal Plan", "Groceries", "Builder"};
//        mViewPagerAdapter = new ViewPagerFragmentAdapter(this, titles.length);
//        mMainBinding.viewPager.setAdapter(mViewPagerAdapter);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
        .add(R.id.containerMainFragments, fragment3, "3").hide(fragment3)
        .add(R.id.containerMainFragments, fragment2, "2").hide(fragment2)
        .add(R.id.containerMainFragments, fragment1, "1").commit();

        mMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.page1:
                        fragmentTransaction.hide(active).show(fragment1).commit();
                        MealPlanFragment.mMealViewModel.fetchAllMeal();
//                        loadFragment(fragment1);
//                        fragmentTransaction.replace(R.id.linearLayout,fragment1);
//                        fragmentTransaction.commit();
                        active = fragment1;
                        return true;
                    case R.id.page2:
                        fragmentTransaction.hide(active).show(fragment2).commit();
//                        loadFragment(fragment2);
                        active = fragment2;
//                        fragmentTransaction.replace(R.id.linearLayout,fragment2);
//                        fragmentTransaction.commit();
                        return true;
                    case R.id.page3:
                        fragmentTransaction.hide(active).show(fragment3).commit();
//                        loadFragment(fragment3);
                        active = fragment3;
//                        fragmentTransaction.replace(R.id.linearLayout,fragment3);
//                        fragmentTransaction.commit();
                        return true;
                }

                return false;
            }
        });

        mMainBinding.bottomNavigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });

//        new TabLayoutMediator(mMainBinding.tabLayout, mMainBinding.viewPager,
//                new TabLayoutMediator.TabConfigurationStrategy() {
//                    @Override
//                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                        tab.setText(titles[position]);
//                    }
//                }).attach();
        Log.d("ABC","MainActivity OnCreate");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ABC","MainActivity OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ABC","MainActivity OnRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ABC","MainActivity OnStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ABC","MainActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ABC","MainActivity OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ABC","MainActivity OnDestroy");
    }
}