package com.ivy.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ivy.popularmovies.R;
import com.ivy.popularmovies.models.TrailerModel;

import java.util.ArrayList;


public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {

    Context context;
    ArrayList<TrailerModel> movie_data = new ArrayList<>();

    public TrailerAdapter(Context context, ArrayList<TrailerModel> movie_data) {
        this.context = context;
        this.movie_data = movie_data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_list_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String id = movie_data.get(position).getKey();
        String thumbnailURL = "http://img.youtube.com/vi/".concat(id).concat("/hqdefault.jpg");
        Picasso.with(context).load(thumbnailURL).placeholder(R.drawable.image).into((holder).imageView);
    }

    @Override
    public int getItemCount() {
        return movie_data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) itemView.findViewById(R.id.trailerImage);
        }
    }

}