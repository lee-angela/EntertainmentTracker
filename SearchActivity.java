package edu.upenn.cis350.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Elsie on 3/16/16.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener,MovieParser.IMovies {

    LinearLayout layout;
    LinearLayout.LayoutParams params;
    ArrayList<Movie> moviesList;
    String title;
    static ProgressDialog pd;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            title = intent.getStringExtra("searchTerm");
            new MovieParser(this).execute("http://www.omdbapi.com/?type=movie&s=" + title);
        }
    }


    @Override
    public void onClick(View v) {

        int n = (int) v.getTag();
        Intent intent = new Intent(this, MovieInfo.class);
        intent.putParcelableArrayListExtra("movie_list", moviesList);
        intent.putExtra("movie_key", n);
        intent.putExtra("movie_name", title);
        finish();
        startActivity(intent);

    }

    @Override
    public void getMovies(ArrayList<Movie> NList) {
        moviesList = NList;

        if (NList == null) {
            Intent i = new Intent(this, MainActivity.class);
            pd.dismiss();
            finish();
            Toast.makeText(SearchActivity.this, "Please Enter Correct Movie Name", Toast.LENGTH_SHORT).show();
            startActivity(i);

        } else {
            for (Movie m : moviesList) {

                TextView text = new TextView(this);
                text.setText(m.getTitle() + " (" + m.getYear() + ")");
                text.setTag(i);
                i++;
                text.setTextSize(15);
                text.setPadding(5, 5, 5, 5);
                text.setLayoutParams(params);

                text.setOnClickListener(this);
                layout.addView(text);

                ImageView divider = new ImageView(this);
                divider.setLayoutParams(params);


                layout.addView(divider);
            }
            pd.dismiss();
        }
    }
}