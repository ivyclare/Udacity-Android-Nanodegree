package com.ivy.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.ivy.popularmovies.activities.MovieActivity;
import com.ivy.popularmovies.R;
import com.ivy.popularmovies.models.MoviesModel;
import com.ivy.popularmovies.utils.Util;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    Context context;
    ArrayList<MoviesModel> data = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public MoviesAdapter(Context context, ArrayList<MoviesModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        MovieActivity.progressBar.setVisibility(View.VISIBLE);

        holder.title.setText(data.get(position).getTitle());

        String imageURL = Util.IMAGE_URL+"/w342" + data.get(position).getposter_path() + "?api_key?=" + Util.API_KEY;
        Picasso.with(context).load(imageURL).into(((MyViewHolder) holder).thumbnail, new Callback() {
            @Override
            public void onSuccess() {
                MovieActivity.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void addAll(ArrayList<MoviesModel> list){
        for (int i = 0; i < list.size(); i++)
        data.add(list.get(i));
    }

}