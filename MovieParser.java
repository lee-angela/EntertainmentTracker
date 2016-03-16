package edu.upenn.cis350.project;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Elsie on 3/16/16.
 */
public class MovieParser extends AsyncTask<String, Void, ArrayList<Movie>> {

    IMovies activity;
    public MovieParser()
    {

    }

    public MovieParser(IMovies activity) {
        this.activity = activity;
    }


    protected ArrayList<Movie> doInBackground(String... params)
    {
        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int statusCode = 0;
        try {
            statusCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (statusCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringBuilder sb = new StringBuilder();
            String line = null;

            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (line != null) {
                sb.append(line);
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                return parseMovies(sb.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected ArrayList<Movie> parseMovies(String str) throws JSONException {
        {
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            JSONObject root = new JSONObject(str);
            JSONArray movieJSONArray = root.getJSONArray("Search");

            for (int i = 0; i < movieJSONArray.length(); i++) {
                JSONObject movieJSON = movieJSONArray.getJSONObject(i);
                Movie movie = new Movie(movieJSON);
                movieList.add(movie);
            }
            Collections.sort(movieList);
            return movieList;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        activity.getMovies(movies);

    }


    static public interface IMovies
    {
        public void getMovies(ArrayList<Movie> NList);
    }


}