package edu.upenn.cis350.project;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Comparable<Movie>, Parcelable{
    String title,imdbID,poster,released,genre,director,actors,plot;
    float imdbRating;
    int year;


    protected Movie()
    {
    }

    public Movie(JSONObject movieJSON) throws JSONException {
        Movie movie=new Movie();
        movie.setTitle(movieJSON.getString("Title"));
        movie.setYear(movieJSON.getString("Year"));
        movie.setImdbID(movieJSON.getString("imdbID"));
        movie.setPoster(movieJSON.getString("Poster"));
        movie.setActors(null);
        movie.setDirector(null);
        movie.setGenre(null);
        movie.setImdbRating(0);
        movie.setReleased(null);
        movie.setPlot(null);
    }

    protected Movie(Parcel in) {
        title = in.readString();
        imdbID = in.readString();
        poster = in.readString();
        released = in.readString();
        genre = in.readString();
        director = in.readString();
        actors = in.readString();
        plot = in.readString();
        imdbRating = in.readFloat();
        year = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", poster='" + poster + '\'' +
                ", released='" + released + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }


    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Movie another) {
        int y= another.getYear();
        return y-this.getYear();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public String getPlot() {
        return plot;
    }

    public String getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleased() {
        return released;
    }

    public String getPoster() {
        return poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {

        return title;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imdbID);
        dest.writeString(poster);
        dest.writeString(released);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeFloat(imdbRating);

        dest.writeInt(year);
    }

    public static Movie getDetails(JSONObject jsonMovie) throws JSONException {
        Movie movie=new Movie();
        movie.setImdbID(jsonMovie.getString("imdbID"));

        movie.setReleased(jsonMovie.getString("Released"));


        movie.setGenre(jsonMovie.getString("Genre"));
        movie.setDirector(jsonMovie.getString("Director"));
        movie.setActors(jsonMovie.getString("Actors"));
        movie.setPlot(jsonMovie.getString("Plot"));
        movie.setImdbRating(Float.parseFloat(jsonMovie.getString("imdbRating")));
        return movie;
    }
}