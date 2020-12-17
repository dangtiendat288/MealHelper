package com.example.mealhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mealhelper.databinding.ActivityMainBinding;
import com.example.mealhelper.fragment.BuildMealPlanFragment;
import com.example.mealhelper.fragment.GroceriesFragment;
import com.example.mealhelper.fragment.MealPlanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mMainBinding;
    //    private String[] titles;
//    ViewPagerFragmentAdapter mViewPagerAdapter;
    final Fragment fragment1 = new MealPlanFragment();
    final Fragment fragment2 = new GroceriesFragment();
    final Fragment fragment3 = new BuildMealPlanFragment();
//    final Fragment[] fragments = {fragment1,fragment2,fragment3};
    FragmentManager mFragmentManager;
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());
//        titles = new String[]{"Meal Plan", "Groceries", "Builder"};
//        mViewPagerAdapter = new ViewPagerFragmentAdapter(this, titles.length);
//        mMainBinding.viewPager.setAdapter(mViewPagerAdapter);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
        .add(R.id.linearLayout, fragment3, "3").hide(fragment3)
        .add(R.id.linearLayout, fragment2, "2").hide(fragment2)
        .add(R.id.linearLayout, fragment1, "1").commit();

        mMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.page1:
                        fragmentTransaction.hide(active).show(fragment1).commit();
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


    }

}