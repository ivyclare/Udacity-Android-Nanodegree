package com.ivy.bakingapp.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.TextView;

import com.ivy.bakingapp.R;
import com.ivy.bakingapp.RecipeListActivity;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.utils.TextUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.MyViewHolder> {
    Context context;
    private ArrayList<StepModel> steps = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_title)
        TextView step_title;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public StepAdapter(Context context, ArrayList<StepModel> steps) {
        this.context = context;
        this.steps = steps;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.steps_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        StepModel item = steps.get(position);
        holder.step_title.setText(TextUtils.capitalizeEachWords(item.getShortDescription()));

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

}