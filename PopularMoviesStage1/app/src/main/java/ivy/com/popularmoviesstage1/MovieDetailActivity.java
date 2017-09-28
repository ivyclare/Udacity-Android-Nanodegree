package ivy.com.popularmoviesstage1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ivy.com.popularmoviesstage1.R;

// Displays details of anyy selected movie
public class MovieDetailActivity extends AppCompatActivity {

    private TextView titleDetail, overviewDetail, voteAvgDetail, relDateDetail;
    private ImageView posterDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String average = getIntent().getStringExtra("average");
        String date = getIntent().getStringExtra("date");
        String image = getIntent().getStringExtra("image");

        titleDetail = (TextView) findViewById(R.id.titleDetail);
        titleDetail.setText(title);

        overviewDetail = (TextView) findViewById(R.id.overviewDetail);
        overviewDetail.setText(overview);

        voteAvgDetail = (TextView) findViewById(R.id.voteAvgDetail);
        voteAvgDetail.setText(average);

        relDateDetail = (TextView) findViewById(R.id.relDateDetail);                
        relDateDetail.setText(date);

        posterDetail = (ImageView) findViewById(R.id.posterDetail);
        Picasso.with(this).load(image).into(posterDetail);
    }
}
