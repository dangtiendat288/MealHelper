package com.example.mealhelper.api;

import com.example.mealhelper.model.ApiResponse;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    //    @GET("search.php")
    @GET("search.php")
    Maybe<ApiResponse> fetchMostPopularMeal(
            @Query("f") String s);

    @GET("search.php")
    Maybe<ApiResponse> fetchRecentlyCreatedMeal(
            @Query("f") String s);

    @GET("search.php")
    Maybe<ApiResponse> fetchBreakfastMeal(
            @Query("f") String s);


}
