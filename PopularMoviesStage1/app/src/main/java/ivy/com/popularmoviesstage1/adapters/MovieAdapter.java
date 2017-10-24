package ivy.com.popularmoviesstage1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ivy.com.popularmoviesstage1.R;
import ivy.com.popularmoviesstage1.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Movie> movieData = new ArrayList<Movie>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public MovieAdapter(Context mContext, ArrayList<Movie> movieData) {
        this.mContext = mContext;
        this.movieData = movieData;
    }

    //Update list when called
    public void setMovieData(ArrayList<Movie> movieData) {
        this.movieData = movieData;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Movie movie = movieData.get(position);
        holder.title.setText(movie.getTitle());
        Picasso.with(mContext).load(movie.getImage()).fit().into(holder.thumbnail);
    }

     @Override
    public int getItemCount() {
        return movieData.size();
    }

}