package com.example.cinemates.repository;

import androidx.annotation.NonNull;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Images;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Response;
import com.example.cinemates.model.Review;
import com.example.cinemates.model.Video;
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
        sMap.put("api_key", Constants.TMDB_API_KEY);
        sMap.put("language", Constants.DEFAULT_SYSTEM_LANGUAGE);
        sMap.put("append_to_response", "images");
        sMap.put("include_image_language", Constants.DEFAULT_SYSTEM_LANGUAGE);
        sMap.put("page", "1");
    }

    public Observable<Response<Movie>> getCurrentlyShowing() {
        return apiService.getCurrentlyShowing(sMap);
    }

    public Observable<Response<Movie>> getTrendingMovies(@NonNull String mediaType, @NonNull String timeWindow) {
        return apiService.getTrendingMovies(mediaType, timeWindow, sMap);
    }

    public Observable<Response<Movie>> getPopular() {
        return apiService.getPopular(sMap);
    }

    public Observable<Response<Movie>> getTopRated() {
        return apiService.getTopRated(sMap);
    }

    public Observable<Response<Movie>> getUpcoming() {
        return apiService.getUpcoming(sMap);
    }

    public Observable<Response<Video>> getVideos(int movie_id) {
        return apiService.getVideos(movie_id, sMap);
    }

    public Observable<Movie> getMovieDetails(int movieId) {
        return apiService.getMovieDetails(movieId, sMap);
    }

    public Observable<Response<Movie>> getSimilar(int movieId) {
        return apiService.getSimilar(movieId, sMap);
    }

    public Observable<Response<Review>> getReviews(int movieId) {
        return apiService.getReviews(movieId, sMap);
    }

    public Observable<Images> getImages(int movieId) {
        return apiService.getImages(movieId, sMap);
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

    public Observable<Response<Movie>> getMoviesBySearch(String query) {
        sMap.put("query", query);
        return apiService.getMoviesBySearch(sMap);

    }

    public Observable<Response<Movie>> getMoviesByActor(String with_cast) {
        sMap.put("with_cast", with_cast);
        return apiService.getMoviesByDiscover(sMap);

    }

    public Observable<Response<Cast>> getPeoplesBySearch(String query) {
        sMap.put("query", query);
        return apiService.getPeoplesBySearch(sMap);

    }


}
