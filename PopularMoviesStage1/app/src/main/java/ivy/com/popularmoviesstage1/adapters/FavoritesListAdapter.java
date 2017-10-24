package ivy.com.popularmoviesstage1.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ivy.com.popularmoviesstage1.R;
import ivy.com.popularmoviesstage1.model.Favorite;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.ViewHolder> {
    private OnMovieClickListener onMovieClickListener;
    private Context context;
    private ArrayList<Favorite> favorites;

    private final String BASE_URL = "http://image.tmdb.org/t/p/w185";

    public FavoritesListAdapter(Context mContext, ArrayList<Favorite> favorites) {
        //this.onMovieClickListener = (OnMovieClickListener) activity;
        this.context = mContext;
        this.favorites = favorites;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_item_movie, parent, false);
        final ViewHolder viewHolder = new ViewHolder(cardView);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMovieClickListener != null) {
                    onMovieClickListener.onMovieClick(favorites.get(viewHolder.getAdapterPosition()));
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        System.out.println("THE MOVIE TITLE IS "+favorites.get(position).getTitle());

        Glide.with(context)
                .load(BASE_URL + favorites.get(position).getPosterPath())
                .placeholder(R.drawable.image)
                .into(holder.poster);
        holder.title.setText(favorites.get(position).getTitle());
        String rating = Double.toString(favorites.get(position).getVoteAverage());
        holder.rating.setText(rating);
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ImageView poster;
        public TextView title;
        public TextView rating;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
            this.poster = (ImageView) cardView.findViewById(R.id.image_view_poster);
            this.title = (TextView) cardView.findViewById(R.id.text_view_title);
            this.rating = (TextView) cardView.findViewById(R.id.text_view_rating);
        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(Favorite favorite);
    }
}
