package com.example.mealhelper.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealhelper.model.ApiResponse;
import com.example.mealhelper.model.Ingredient;
import com.example.mealhelper.model.Meal;
import com.example.mealhelper.repository.MealRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
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
    private MutableLiveData<Long> mInsertedIngredient;
    private MutableLiveData<Integer> mUpdatedIngredient;
    private MutableLiveData<Boolean> mDeletedIngredient;
    private MutableLiveData<List<Ingredient>> mAllIngredient;
    private MutableLiveData<List<Meal>> mAllMeal;
    private MutableLiveData<List<Meal>> mMealWithID;
    private MutableLiveData<List<Meal>> mMealsStartWithH;
    private MutableLiveData<List<Meal>> mMealsStartWithB;
    private MutableLiveData<List<Meal>> mMealsStartWithC;
    private MutableLiveData<List<Meal>> mAddedMeals;
    private MutableLiveData<List<Meal>> mFavMeals;
    //    private MutableLiveData<List<Meal>> mCountAddedMeals;
    private MutableLiveData<List<Meal>> mBuiltMeals;
//    private MutableLiveData<List<Meal>> mMealsStartWithH;

    public MealViewModel(@NonNull Application application) {
        super(application);
        mMealRepository = MealRepository.getInstance(application);
        mostPopularMeal = new MutableLiveData<>();
        recentlyCreatedMeal = new MutableLiveData<>();
        breakfastMeal = new MutableLiveData<>();

        mInsertedMeal = new MutableLiveData<>();
        mUpdatedMeal = new MutableLiveData<>();
        mDeletedMeal = new MutableLiveData<>();
        mInsertedIngredient = new MutableLiveData<>();
        mUpdatedIngredient = new MutableLiveData<>();
        mDeletedIngredient = new MutableLiveData<>();

        mAllIngredient = new MutableLiveData<>();

        mAllMeal = new MutableLiveData<>();
        mMealWithID = new MutableLiveData<>();
        mMealsStartWithH = new MutableLiveData<>();
        mMealsStartWithB = new MutableLiveData<>();
        mMealsStartWithC = new MutableLiveData<>();
        mAddedMeals = new MutableLiveData<>();
        mFavMeals = new MutableLiveData<>();
//        mCountAddedMeals = new MutableLiveData<>();
        mBuiltMeals = new MutableLiveData<>();
    }

    public void fetchAllIngredient() {
        mMealRepository.fetchAllIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Ingredient>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Ingredient> ingredients) {
                        mAllIngredient.setValue(ingredients);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Ingredient>> getAllIngredient() {
        return mAllIngredient;
    }

    public void fetchBuiltMeals() {
        mMealRepository.fetchBuiltMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
                        mBuiltMeals.setValue(meals);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Meal>> getBuiltMeals() {
        return mBuiltMeals;
    }

//    public void fetchCountAddedMeals() {
//        mMealRepository.fetchAddedMeals()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MaybeObserver<List<Meal>>() {
//                    @Override
//                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
//                        mCountAddedMeals.setValue(meals);
//                    }
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
//    }
//
//    public LiveData<List<Meal>> getCountAddedMeals() {
//        return mCountAddedMeals;
//    }

    public void fetchFavMeals() {
        mMealRepository.fetchFavMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
                        mFavMeals.setValue(meals);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Meal>> getFavMeals() {
        return mFavMeals;
    }

    public void fetchAddedMeals() {
        mMealRepository.fetchAddedMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
                        mAddedMeals.setValue(meals);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Meal>> getAddedMeals() {
        return mAddedMeals;
    }

    public void fetchMealsStartWithAChar(String initChar) {
        mMealRepository.fetchMealStartWithAChar(initChar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
                        switch (initChar) {
                            case "H%":
                                mMealsStartWithH.setValue(meals);
                                break;
                            case "B%":
                                mMealsStartWithB.setValue(meals);
                                break;
                            case "C%":
                                mMealsStartWithC.setValue(meals);
                                break;
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Meal>> getMealsStartWithH() {
        return mMealsStartWithH;
    }

    public LiveData<List<Meal>> getMealsStartWithB() {
        return mMealsStartWithB;
    }

    public LiveData<List<Meal>> getMealsStartWithC() {
        return mMealsStartWithC;
    }

    public void fetchMealWithID(int idMeal) {
        mMealRepository.fetchMealWithID(idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Meal>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Meal> meals) {
                        mMealWithID.setValue(meals);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<Meal>> getMealWithID() {
        return mMealWithID;
    }

    public void insertIngredient(Ingredient ingredient) {
        mMealRepository.insert(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Long>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                        mInsertedIngredient.setValue(aLong);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Long> getInsertedIngredient() {
        return mInsertedIngredient;
    }

    public void updateIngredient(Ingredient ingredient) {
        mMealRepository.update(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Integer integer) {
                        mUpdatedIngredient.setValue(integer);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Integer> getUpdatedIngredient() {
        return mUpdatedIngredient;
    }

    public void deleteIngredient(Ingredient ingredient) {
        mMealRepository.delete(ingredient)
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
                        mDeletedIngredient.setValue(true);
                    }
                });
    }

    public LiveData<Boolean> getDeletedIngredient() {
        return mDeletedIngredient;
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
