package com.example.cinemates.repository;

import androidx.annotation.NonNull;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.MovieResponse;
import com.example.cinemates.model.ReviewResponse;
import com.example.cinemates.model.VideoResponse;
import com.example.cinemates.network.MovieApiService;
import com.example.cinemates.util.Constants;
import com.google.gson.JsonObject;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:46
 */
public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private static HashMap<String, String> sMap;
    MovieApiService apiService;


    @Inject
    public Repository(MovieApiService apiService) {
        this.apiService = apiService;
        sMap = new HashMap<>();
        sMap.put("api_key", Constants.API_KEY);
        sMap.put("language", Constants.DEFAULT_SYSTEM_LANGUAGE);
        sMap.put("append_to_response", "images");
        sMap.put("include_image_language", Constants.DEFAULT_SYSTEM_LANGUAGE);
        sMap.put("page", "1");
    }

    public Observable<MovieResponse> getCurrentlyShowing() {
        return apiService.getCurrentlyShowing(sMap);
    }

    public Observable<MovieResponse> getTrendingMovies(@NonNull String mediaType, @NonNull String timeWindow) {
        return apiService.getTrendingMovies(mediaType, timeWindow, sMap);
    }

    public Observable<MovieResponse> getPopular() {
        return apiService.getPopular(sMap);
    }

    public Observable<MovieResponse> getTopRated() {
        return apiService.getTopRated(sMap);
    }

    public Observable<MovieResponse> getUpcoming() {
        return apiService.getUpcoming(sMap);
    }

    public Observable<VideoResponse> getVideos(int movie_id) {
        return apiService.getVideos(movie_id, sMap);
    }

    public Observable<Movie> getMovieDetails(int movieId) {
        return apiService.getMovieDetails(movieId, sMap);
    }

    public Observable<MovieResponse> getSimilar(int movieId) {
        return apiService.getSimilar(movieId, sMap);
    }

    public Observable<ReviewResponse> getReviews(int movieId) {
        return apiService.getReviews(movieId, sMap);
    }

    public Observable<JsonObject> getCast(int movieId) {
        return apiService.getCast(movieId, sMap);
    }

    public Observable<Actor> getActorDetails(int personId) {
        return apiService.getActorDetails(personId, sMap);
    }

    public Observable<Collection> getCollection(int collectionId) {
        return apiService.getCollection(collectionId, sMap);
    }

    public Observable<MovieResponse> getMoviesBySearch(String query) {
        sMap.put("query", query);
        return apiService.getMoviesBySearch(sMap);

    }


}
