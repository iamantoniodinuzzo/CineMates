package com.example.cinemates.model;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.ProductionCountry;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 17:06
 */
public class Movie {

    private final int movieID;

    private final String title;
    private final String years;
    private final Integer duration;
    private final String plot;
    private final List<ProductionCountry> productionCountry;
    private final List<PersonCrew> crew;
    private final List<Genre> genres;
    private final List<PersonCast> cast;
    private final Uri posterUri;

    public Movie(int movieID, String title, List<PersonCrew> crew, String years, Integer duration, String plot, List<ProductionCountry> productionCountry, List<Genre> genres, List<PersonCast> cast, Uri posterUri) {
        this.movieID = movieID;
        this.title = title;
        this.crew = crew;
        this.years = years;
        this.duration = duration;
        this.plot = plot;
        this.productionCountry = productionCountry;
        this.cast = cast;
        this.genres = genres;
        this.posterUri = posterUri;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public String getYears() {
        return years;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getPlot() {
        return plot;
    }

    public List<ProductionCountry> getProductionCountry() {
        return productionCountry;
    }

    public List<PersonCrew> getCrew() {
        return crew;
    }

    public List<PersonCast> getCast() {
        return cast;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Uri getPosterUri() {
        return posterUri;
    }


    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imageUri) {
        Glide.with(imageView)
                .load(imageUri)
                .into(imageView);

    }

    @BindingAdapter({"android:loadGenres"})
    public static void loadGenres(TextView textView, List<Genre> genres_list) {
        String genres = "";
        for (Genre genre : genres_list) {
            genres.concat(genre.getName() + " ");
        }
        textView.setText(genres);
    }
}
