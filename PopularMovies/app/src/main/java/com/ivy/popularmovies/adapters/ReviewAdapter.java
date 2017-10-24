package com.ivy.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivy.popularmovies.R;
import com.ivy.popularmovies.models.ReviewModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ReviewModel> movie_data = new ArrayList<>();

    public ReviewAdapter(Context context, ArrayList<ReviewModel> movie_data) {
        this.context = context;
        this.movie_data = movie_data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.authorText.setText(movie_data.get(position).getAuthor());
        holder.reviewInfo.setText(movie_data.get(position).getcontent());
    }

    @Override
    public int getItemCount() {
        return movie_data.size();
    }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView authorText;
            TextView reviewInfo;

            public MyViewHolder(View view) {
                super(view);
                authorText = (TextView) view.findViewById(R.id.authorText);
                reviewInfo = (TextView) view.findViewById(R.id.reviewText);
            }
        }
}