package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mealhelper.R;
import com.example.mealhelper.model.Meal;


public class VideoFragment extends Fragment {
    TextView mTextview;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Meal meal = this.getArguments().getParcelable("meal");
        mTextview.setText(meal.getStrYoutube());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        mTextview = v.findViewById(R.id.tvVideo);
        return v;
    }
}