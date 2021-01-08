package com.example.mealhelper.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.mealhelper.MainActivity;
import com.example.mealhelper.R;
import com.example.mealhelper.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    public static FragmentSettingsBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() ==
                AppCompatDelegate.MODE_NIGHT_YES) {
//            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.dm_background));
            mBinding.switchDarkMode.setChecked(true);
        } else {
            mBinding.switchDarkMode.setChecked(false);
        }

        mBinding.switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                new Handler(Looper.myLooper()).postDelayed(()->{
                    if (mBinding.switchDarkMode.isChecked()) {

                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate
                                .MODE_NIGHT_YES);
//                        getActivity().setTheme(R.style.DarkMode);
                    } else {

                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate
                                .MODE_NIGHT_NO);
//                        getActivity().setTheme(R.style.LightMode);
                    }
                },1000);

//                activate();

            }
        });
    }

    private void activate() {
        Intent intent = new Intent(getActivity(),
                MainActivity.class);
        new Handler(Looper.myLooper()).postDelayed(()->{
//        intent.putExtra("name", name);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
//        finish();
        },1000);
    }
}