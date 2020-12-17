package com.example.mealhelper.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.repository.MealRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealViewModel extends AndroidViewModel {

    private MealRepository mMealRepository;
    private MutableLiveData<ApiResponse> mostPopularMeal;
    private MutableLiveData<ApiResponse> recentlyCreatedMeal;
    private MutableLiveData<ApiResponse> breakfastMeal;

    private MutableLiveData<Long> mInsertedMeal;
    private MutableLiveData<Integer> mUpdatedMeal;
    private MutableLiveData<Boolean> mDeletedMeal;
    private MutableLiveData<List<Meal>> mAllMeal;

    public MealViewModel(@NonNull Application application) {
        super(application);
        mMealRepository = MealRepository.getInstance(application);
        mostPopularMeal = new MutableLiveData<>();
        recentlyCreatedMeal = new MutableLiveData<>();
        breakfastMeal = new MutableLiveData<>();

        mInsertedMeal = new MutableLiveData<>();
        mUpdatedMeal = new MutableLiveData<>();
        mDeletedMeal = new MutableLiveData<>();
        mAllMeal = new MutableLiveData<>();
    }

    public void insertMeal(Meal meal) {
        mMealRepository.insert(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                        mInsertedMeal.setValue(aLong);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Long> getInsertedMeal() {
        return mInsertedMeal;
    }

    public void updateMeal(Meal meal) {
        mMealRepository.update(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Integer integer) {
                        mUpdatedMeal.setValue(integer);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Integer> getUpdatedMeal() {
        return mUpdatedMeal;
    }

    public void deleteMeal(Meal meal) {
        mMealRepository.delete(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Void>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mDeletedMeal.setValue(true);
                    }
                });
    }

    public LiveData<Boolean> getDeletedMeal() {
        return mDeletedMeal;
    }

    public void fetchAllMeal() {
        mMealRepository.fetchAllMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
                        mAllMeal.setValue(meals);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Meal>> getAllMeal() {
        return mAllMeal;
    }

    public void fetchMostPopularMeal(String s) {
        mMealRepository.fetchMostPopularMeal(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<ApiResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ApiResponse apiResponse) {
                        mostPopularMeal.setValue(apiResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public LiveData<ApiResponse> getMostPopularMeal() {
        return mostPopularMeal;
    }

    public void fetchRecentlyCreatedMeal(String s) {
        mMealRepository.fetchMostPopularMeal(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<ApiResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ApiResponse apiResponse) {
                        recentlyCreatedMeal.setValue(apiResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public LiveData<ApiResponse> getRecentlyCreatedMeal() {
        return recentlyCreatedMeal;
    }

    public void fetchBreakfastMeal(String s) {
        mMealRepository.fetchMostPopularMeal(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<ApiResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ApiResponse apiResponse) {
                        breakfastMeal.setValue(apiResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public LiveData<ApiResponse> getBreakfastMeal() {
        return breakfastMeal;
    }

}
