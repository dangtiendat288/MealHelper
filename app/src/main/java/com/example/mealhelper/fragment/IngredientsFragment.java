package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealhelper.R;
import com.example.mealhelper.adapter.IngredientAdapter;
import com.example.mealhelper.model.Ingredient;
import com.example.mealhelper.model.Meal;

import java.util.ArrayList;
import java.util.List;


public class IngredientsFragment extends Fragment {
    List<Ingredient> mIngredients;
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredients, container, false);
        mRecyclerView = v.findViewById(R.id.rvIngredient);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Meal meal = this.getArguments().getParcelable("meal");
        mIngredients = new ArrayList<>();
        mIngredients.add(new Ingredient(meal.getStrIngredient1(),meal.getStrMeasure1()));
        mIngredients.add(new Ingredient(meal.getStrIngredient2(),meal.getStrMeasure2()));
        mIngredients.add(new Ingredient(meal.getStrIngredient3(),meal.getStrMeasure3()));
        mIngredients.add(new Ingredient(meal.getStrIngredient4(),meal.getStrMeasure4()));
        mIngredients.add(new Ingredient(meal.getStrIngredient5(),meal.getStrMeasure5()));
        mIngredients.add(new Ingredient(meal.getStrIngredient6(),meal.getStrMeasure6()));
        mIngredients.add(new Ingredient(meal.getStrIngredient7(),meal.getStrMeasure7()));
        mIngredients.add(new Ingredient(meal.getStrIngredient8(),meal.getStrMeasure8()));
        mIngredients.add(new Ingredient(meal.getStrIngredient9(),meal.getStrMeasure9()));
        mIngredients.add(new Ingredient(meal.getStrIngredient10(),meal.getStrMeasure10()));
        mIngredients.add(new Ingredient(meal.getStrIngredient11(),meal.getStrMeasure11()));
        mIngredients.add(new Ingredient(meal.getStrIngredient12(),meal.getStrMeasure12()));
        mIngredients.add(new Ingredient(meal.getStrIngredient13(),meal.getStrMeasure13()));
        mIngredients.add(new Ingredient(meal.getStrIngredient14(),meal.getStrMeasure14()));
        mIngredients.add(new Ingredient(meal.getStrIngredient15(),meal.getStrMeasure15()));
        mIngredients.add(new Ingredient(meal.getStrIngredient16(),meal.getStrMeasure16()));
        mIngredients.add(new Ingredient(meal.getStrIngredient17(),meal.getStrMeasure17()));
        mIngredients.add(new Ingredient(meal.getStrIngredient18(),meal.getStrMeasure18()));
        mIngredients.add(new Ingredient(meal.getStrIngredient19(),meal.getStrMeasure19()));
        mIngredients.add(new Ingredient(meal.getStrIngredient20(),meal.getStrMeasure20()));
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity());
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(ingredientAdapter);
        ingredientAdapter.submitList(mIngredients);


//        Toast.makeText(getActivity(), mIngredients.toString(), Toast.LENGTH_SHORT).show();
    }
}