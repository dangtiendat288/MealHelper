package com.example.mealhelper.model;

public class Ingredient {
    private String ingredient;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Ingredient(String ingredient, String measure) {
        this.ingredient = ingredient;
        this.measure = measure;
    }

    private String measure;
}
