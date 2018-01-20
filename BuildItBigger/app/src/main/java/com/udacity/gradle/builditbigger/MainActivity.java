package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ivy.androidjokelib.MyJokesActivity;
import com.ivy.javajokelib.*;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;


public class MainActivity extends AppCompatActivity {
    MyJokes MyJokes;

    ProgressBar mSpinner;
    private MyApi mApiService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar) findViewById(R.id.progressBar);

        mSpinner.setVisibility(View.GONE);

        MyJokes = new MyJokes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //Joke displayed from Java Library
        //Toast.makeText(this, MyJokes.getJoke(), Toast.LENGTH_SHORT).show();

        //Joke displayed from Java Library through Android Library
        //launchJokeActivity(view);

        new EndPointsAsyncTask(getApplicationContext(), mSpinner, false).execute("give me the joke");
    }

    // Displaying Joke Using Android Library
    public void launchJokeActivity(View view) {
        Intent intent = new Intent(this, MyJokesActivity.class);
        MyJokes jokeSource = new MyJokes();
        String joke = jokeSource.getJoke();
        intent.putExtra(MyJokesActivity.JOKE_KEY, joke);
        startActivity(intent);
    }


//    @Override
//    public void startActivity(String result) {
//        if (result != null && !result.equalsIgnoreCase("")) {
//            Intent intent = new Intent(getApplicationContext(), MyJokesActivity.class);
//            System.out.println("MAINACTIVITY:  THE JOKE IS "+result);
//            intent.putExtra(MyJokesActivity.JOKE_KEY, result);
//            startActivity(intent);
//        }else{
//            Toast.makeText(getApplicationContext(),getString(R.string.fail), Toast.LENGTH_LONG).show();
//        }
//    }
}
