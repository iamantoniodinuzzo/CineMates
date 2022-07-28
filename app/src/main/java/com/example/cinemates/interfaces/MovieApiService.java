package com.example.cinemates.interfaces;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Images;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.Response;
import com.example.cinemates.model.Review;
import com.example.cinemates.model.Video;
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
    Observable<Response<Movie>> getCurrentlyShowing(@QueryMap HashMap<String, String> queries);

    @GET("movie/popular")
    Observable<Response<Movie>> getPopular(@QueryMap HashMap<String, String> queries);

    @GET("movie/upcoming")
    Observable<Response<Movie>> getUpcoming(@QueryMap HashMap<String, String> queries);

    @GET("movie/top_rated")
    Observable<Response<Movie>> getTopRated(@QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetails(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/credits")
    Observable<JsonObject> getCast(@Path("movie_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/similar")
    Observable<Response<Movie>> getSimilar(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/videos")
    Observable<Response<Video>> getVideos(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/reviews")
    Observable<Response<Review>> getReviews(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("movie/{movie_id}/images")
    Observable<Images> getImages(@Path("movie_id") int movie_id, @QueryMap HashMap<String, String> queries);

    @GET("person/{person_id}")
    Observable<Actor> getActorDetails(@Path("person_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("person/{person_id}/images")
    Observable<JsonObject> getActorImages(@Path("person_id") int id, @Query("api_key") String api);

    @GET("collection/{collection_id}")
    Observable<Collection> getCollection(@Path("collection_id") int id, @QueryMap HashMap<String, String> queries);

    @GET("search/movie")
    Observable<Response<Movie>> getMoviesBySearch(@QueryMap HashMap<String, String> queries);

    @GET("discover/movie")
    Observable<Response<Movie>> getMoviesByDiscover(@QueryMap HashMap<String, String> queries);

    @GET("genre/movie/list")
    Observable<Response<Genre>> getGenreList(@QueryMap HashMap<String, String> queries);

    @GET("search/person")
    Observable<Response<Cast>> getPeoplesBySearch(@QueryMap HashMap<String, String> queries);

    @GET("trending/{media_type}/{time_window}")
    Observable<Response<Movie>> getTrendingMovies(@Path("media_type") String media_type, @Path("time_window") String time_window, @QueryMap HashMap<String, String> queries);

    @GET("trending/{media_type}/{time_window}")
    Observable<Response<Cast>> getTrendingPerson(@Path("media_type") String media_type, @Path("time_window") String time_window, @QueryMap HashMap<String, String> queries);
}
