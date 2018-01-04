package com.ivy.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivy.bakingapp.R;
import com.ivy.bakingapp.RecipeListActivity;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    Context context;
    private boolean mTwoPane;
    ArrayList<RecipeModel> data = new ArrayList<>();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //        public TextView title;
//        public ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public RecipeAdapter(Context context, ArrayList<RecipeModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recipe_list_content, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        RecipeListActivity.progressBar.setVisibility(View.VISIBLE);

        holder.title.setText(data.get(position).getName());

        //String imageURL = ApiUtils.IMAGE_URL+"/w342" + data.get(position).getPosterPath() + "?api_key?=" + ApiUtils.API_KEY;
        String imageURL = data.get(position).getImage();

        if(imageURL.equals("")){
            Picasso.with(context).load(R.drawable.food_placeholder).error(R.drawable.food_placeholder).into(((MyViewHolder) holder).thumbnail,
                    new Callback() {
                        @Override
                        public void onSuccess() {
                            RecipeListActivity.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        } else{
            Picasso.with(context).load(imageURL).error(R.drawable.food_placeholder).into(((MyViewHolder) holder).thumbnail,
                    new Callback() {
                        @Override
                        public void onSuccess() {
                            RecipeListActivity.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void addAll(ArrayList<RecipeModel> list){
        for (int i = 0; i < list.size(); i++)
            data.add(list.get(i));
    }

}