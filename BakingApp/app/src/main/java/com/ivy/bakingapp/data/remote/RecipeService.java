package com.ivy.bakingapp.data.remote;


import com.ivy.bakingapp.data.model.IngredientModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.data.model.StepModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ivoline-Clarisse on 10/28/2017.
 */
public interface RecipeService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeModel>> loadRecipes();

//    @GET("topher/2017/May/59121517_baking/baking.json")
//    Call<Recipes> loadRecipes();

//    @GET("/3/movie/{sort}")
//    Call<Recipes> loadRecipes(@Path("sort") String sort, @Query("api_key") String api_key);
//
//    @GET("/3/movie/{id}/videos")
//    Call<Ingredients> loadIngredients(@Path("id") String id, @Query("api_key") String api_key);
//
//    @GET("/3/movie/{id}/reviews")
//    Call<Steps> loadSteps(@Path("id") String id, @Query("api_key") String api_key);

    class Recipes {
        public List<RecipeModel> results;
    }

//    class Ingredients {
//        public List<IngredientModel> results;
//    }
//
//    class Steps {
//        public List<StepModel> results;
//    }

}
