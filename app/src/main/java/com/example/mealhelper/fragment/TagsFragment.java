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


public class TagsFragment extends Fragment {
    TextView mTvTags, mtvArea;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Meal meal = this.getArguments().getParcelable("meal");
        String tags = meal.getStrTags();
        if (tags == null) {
            tags = "no tag";
        } else if (tags.contains(",")) {
            tags = tags.replace(",", ", ");
        }
        mTvTags.setText("Tags: " + tags);
        mtvArea.setText("Area: " + meal.getStrArea());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tags, container, false);
        mTvTags = v.findViewById(R.id.tvTags);
        mtvArea = v.findViewById(R.id.tvArea);
        return v;
    }
}