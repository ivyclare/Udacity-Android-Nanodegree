package com.ivy.bakingapp.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivy.bakingapp.R;
import com.ivy.bakingapp.RecipeListActivity;
import com.ivy.bakingapp.data.model.IngredientModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.utils.TextUtils;
import com.ivy.bakingapp.widget.UpdateBakingService;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {
    Context context;
    private ArrayList<IngredientModel> ingredients = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //        public TextView title;
//        public ImageView thumbnail;
        @BindView(R.id.ingredient_name)
        TextView ingredient_name;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.ll_ingredient)
        LinearLayout ll_ingredient;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public IngredientAdapter(Context context, ArrayList<IngredientModel> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.ingredient_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        // Toggling colors of rows in recycler view
        if (position % 2 == 1) {
            holder.ll_ingredient.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                    R.color.colorPrimaryLight));
        } else {
            holder.ll_ingredient.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                    R.color.pure_white));
        }
        IngredientModel item = ingredients.get(position);
        holder.ingredient_name.setText(TextUtils.capitalizeEachWords(item.getIngredient()));
        String quantity = TextUtils.removeTrailingZero(String.valueOf(item.getQuantity())) + " " +
                item.getMeasure();
        holder.quantity.setText(quantity);

        //Sending the ingredient details to the widget
        ArrayList<String> recipeIngredientsForWidgets= new ArrayList<>();
        for(int i=0; i<ingredients.size(); i++) {
            IngredientModel items = ingredients.get(i);
            recipeIngredientsForWidgets.add(items.getIngredient() + "\t\t" + items.getQuantity().toString() + " "+ items.getMeasure());
        }
        //Updating the Widget
        UpdateBakingService.startBakingService(context,recipeIngredientsForWidgets);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

}