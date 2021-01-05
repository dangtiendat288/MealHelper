package com.example.mealhelper.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        if (meal.getStrIngredient1() == null) {

        } else if (!meal.getStrIngredient1().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient1(), meal.getStrMeasure1()));
        if (meal.getStrIngredient2() == null) {

        } else if (!meal.getStrIngredient2().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient2(), meal.getStrMeasure2()));
        if (meal.getStrIngredient3() == null) {

        } else if (!meal.getStrIngredient3().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient3(), meal.getStrMeasure3()));
        if (meal.getStrIngredient4() == null) {

        } else if (!meal.getStrIngredient4().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient4(), meal.getStrMeasure4()));
        if (meal.getStrIngredient5() == null) {

        } else if (!meal.getStrIngredient5().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient5(), meal.getStrMeasure5()));
        if (meal.getStrIngredient6() == null) {

        } else if (!meal.getStrIngredient6().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient6(), meal.getStrMeasure6()));
        if (meal.getStrIngredient7() == null) {

        } else if (!meal.getStrIngredient7().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient7(), meal.getStrMeasure7()));
        if (meal.getStrIngredient8() == null) {

        } else if (!meal.getStrIngredient8().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient8(), meal.getStrMeasure8()));
        if (meal.getStrIngredient9() == null) {

        } else if (!meal.getStrIngredient9().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient9(), meal.getStrMeasure9()));
        if (meal.getStrIngredient10() == null) {

        } else if (!meal.getStrIngredient10().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient10(), meal.getStrMeasure10()));
        if (meal.getStrIngredient11() == null) {

        } else if (!meal.getStrIngredient11().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient11(), meal.getStrMeasure11()));
        if (meal.getStrIngredient12() == null) {

        } else if (!meal.getStrIngredient12().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient12(), meal.getStrMeasure12()));
        if (meal.getStrIngredient13() == null) {

        } else if (!meal.getStrIngredient13().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient13(), meal.getStrMeasure13()));
        if (meal.getStrIngredient14() == null) {

        } else if (!meal.getStrIngredient14().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient14(), meal.getStrMeasure14()));
        if (meal.getStrIngredient15() == null) {

        } else if (!meal.getStrIngredient15().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient15(), meal.getStrMeasure15()));
        if (meal.getStrIngredient16() == null) {

        } else if (!meal.getStrIngredient16().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient16(), meal.getStrMeasure16()));
        if (meal.getStrIngredient17() == null) {

        } else if (!meal.getStrIngredient17().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient17(), meal.getStrMeasure17()));
        if (meal.getStrIngredient18() == null) {

        } else if (!meal.getStrIngredient18().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient18(), meal.getStrMeasure18()));
        if (meal.getStrIngredient19() == null) {

        } else if (!meal.getStrIngredient19().isEmpty())
            mIngredients.add(new Ingredient(meal.getStrIngredient19(), meal.getStrMeasure19()));

//        if(meal.getStrIngredient1()==null||(!meal.getStrIngredient1().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient1(),meal.getStrMeasure1()));
//        if(meal.getStrIngredient2()==null||(!meal.getStrIngredient2().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient2(),meal.getStrMeasure2()));
//        if(meal.getStrIngredient3()==null||(!meal.getStrIngredient3().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient3(),meal.getStrMeasure3()));
//        if(meal.getStrIngredient4()==null||(!meal.getStrIngredient4().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient4(),meal.getStrMeasure4()));
//        if(meal.getStrIngredient5()==null||(!meal.getStrIngredient5().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient5(),meal.getStrMeasure5()));
//        if(meal.getStrIngredient6()==null||(!meal.getStrIngredient6().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient6(),meal.getStrMeasure6()));
//        if(meal.getStrIngredient7()==null||(!meal.getStrIngredient7().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient7(),meal.getStrMeasure7()));
//        if(meal.getStrIngredient8()==null||(!meal.getStrIngredient8().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient8(),meal.getStrMeasure8()));
//        if(meal.getStrIngredient9()==null||(!meal.getStrIngredient9().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient9(),meal.getStrMeasure9()));
//        if(meal.getStrIngredient10()==null||(!meal.getStrIngredient10().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient10(),meal.getStrMeasure10()));
//        if(meal.getStrIngredient11()==null||(!meal.getStrIngredient11().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient11(),meal.getStrMeasure11()));
//        if(meal.getStrIngredient12()==null||(!meal.getStrIngredient12().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient12(),meal.getStrMeasure12()));
//        if(meal.getStrIngredient13()==null||(!meal.getStrIngredient13().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient13(),meal.getStrMeasure13()));
//        if(meal.getStrIngredient14()==null||(!meal.getStrIngredient14().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient14(),meal.getStrMeasure14()));
//        if(meal.getStrIngredient15()==null||(!meal.getStrIngredient15().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient15(),meal.getStrMeasure15()));
//        if(meal.getStrIngredient16()==null||(!meal.getStrIngredient16().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient16(),meal.getStrMeasure16()));
//        if(meal.getStrIngredient17()==null||(!meal.getStrIngredient17().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient17(),meal.getStrMeasure17()));
//        if(meal.getStrIngredient18()==null||(!meal.getStrIngredient18().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient18(),meal.getStrMeasure18()));
//        if(meal.getStrIngredient19()==null||(!meal.getStrIngredient19().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient19(),meal.getStrMeasure19()));
//        if(meal.getStrIngredient20()==null||(!meal.getStrIngredient20().isEmpty()))mIngredients.add(new Ingredient(meal.getStrIngredient20(),meal.getIsBuilt()));

        IngredientAdapter ingredientAdapter = new IngredientAdapter(getActivity());
        mRecyclerView.hasFixedSize();
        mRecyclerView.setAdapter(ingredientAdapter);
        ingredientAdapter.submitList(mIngredients);


//        Toast.makeText(getActivity(), mIngredients.toString(), Toast.LENGTH_SHORT).show();
    }
}