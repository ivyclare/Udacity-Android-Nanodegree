package com.ivy.bakingapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ivy.bakingapp.R;
import com.ivy.bakingapp.adapters.IngredientAdapter;
import com.ivy.bakingapp.data.model.IngredientModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.RecyclerViewMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsFragment extends Fragment {

    private IngredientAdapter IngredientAdapter;

    @BindView(R.id.ingred_recycler)
    RecyclerView IngredientRecyclerView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment IngredientsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IngredientsFragment newInstance(ArrayList<IngredientModel> ingredients) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
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

        //Sending the ingredient details to the widget

//        ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();
//
//
//        ingredients.forEach((a) ->
//        {
//            textView.append("\u2022 "+ a.getIngredient()+"\n");
//            textView.append("\t\t\t Quantity: "+a.getQuantity().toString()+"\n");
//            textView.append("\t\t\t Measure: "+a.getMeasure()+"\n\n");
//
//            recipeIngredientsForWidgets.add(a.getIngredient()+"\n"+
//                    "Quantity: "+a.getQuantity().toString()+"\n"+
//                    "Measure: "+a.getMeasure()+"\n");
//        });
    }


}
