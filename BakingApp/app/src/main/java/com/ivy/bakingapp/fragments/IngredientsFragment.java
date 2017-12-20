package com.ivy.bakingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivy.bakingapp.R;
import com.ivy.bakingapp.RecipeListActivity;
import com.ivy.bakingapp.adapters.IngredientAdapter;
import com.ivy.bakingapp.data.model.IngredientModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.RecyclerViewMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class IngredientsFragment extends Fragment {

    private IngredientAdapter IngredientAdapter;
    private RecipeModel recipeModel;

    @BindView(R.id.ingred_recycler)
    RecyclerView IngredientRecyclerView;

    @Nullable
    @BindView(R.id.view_directions)
    TextView ViewDirections;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment IngredientsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IngredientsFragment newInstance(RecipeModel recipe) {
        ArrayList<IngredientModel> ingredients = recipe.getIngredients();
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        args.putParcelableArrayList("ingredients", ingredients);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView();


        if (view.findViewById(R.id.view_directions) != null) {
            ViewDirections.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("VIEW DIRECTIONS HAS BEEN CLICKED");
                    recipeModel = getArguments().getParcelable("recipe");
                    //StepsFragment.newInstance(recipeModel);
                    getActivity().setTitle("Directions");
                    StepsFragment fragment = StepsFragment.newInstance(recipeModel);
                    //fragment.setArguments(arguments);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.recipe_detail_container, fragment)
                            .commit();
                }
            });
        }

        return view;
    }


    private void setupRecyclerView() {
        int column = 1;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        IngredientRecyclerView.setLayoutManager(layoutManager);
        IngredientRecyclerView.addItemDecoration(decoration);

        ArrayList<IngredientModel> ingredients = getArguments().getParcelableArrayList("ingredients");

        IngredientAdapter = new IngredientAdapter(getContext(),ingredients);

        IngredientRecyclerView.setAdapter(IngredientAdapter);
    }


}
