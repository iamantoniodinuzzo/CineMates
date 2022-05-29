package com.example.cinemates.network;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.MovieResponse;
import com.example.cinemates.model.ReviewResponse;
import com.example.cinemates.model.VideoResponse;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.google.gson.JsonObject;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:44
 */
public interface MovieApiService {
    @GET("movie/now_playing")
    Observable<MovieResponse> getCurrentlyShowing(@QueryMap HashMap<String, String> queries);

    @GET("movie/popular")
    Observable<MovieResponse> getPopular(@QueryMap HashMap<String, String> queries);

    @GET("movie/upcoming")
    Observable<MovieResponse> getUpcoming(@QueryMap HashMap<String, String> queries);

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRated(@QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetails(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/credits")
    Observable<JsonObject> getCast(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/similar")
    Observable<MovieResponse> getSimilar(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/videos")
    Observable<VideoResponse> getVideos(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewResponse> getReviews(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("person/{person_id}")
    Observable<Actor> getActorDetails(@Path("person_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("person/{person_id}/images")
    Observable<JsonObject> getActorImages(@Path("person_id") int id, @Query("api_key") String api);

    @GET("collection/{collection_id}")
    Observable<Collection> getCollection(@Path("collection_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("search/movie")
    Observable<JsonObject> getMoviesBySearch(@QueryMap HashMap<String, String> queries);

    @GET("trending/{media_type}/{time_window}")
    Observable<MovieResponse> getTrendingMovies(@Path("media_type") String media_type, @Path("time_window") String time_window, @QueryMap HashMap<String, String> queries);
}
