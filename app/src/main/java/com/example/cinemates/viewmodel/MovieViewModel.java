package com.example.cinemates.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.MovieResponse;
import com.example.cinemates.model.Review;
import com.example.cinemates.model.Video;
import com.example.cinemates.repository.Repository;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:56
 */
public class MovieViewModel extends ViewModel {
    private static final String TAG = MovieViewModel.class.getSimpleName();

    private Repository repository;
    private MutableLiveData<ArrayList<Movie>> currentMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> trendingMovieList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> movieSimilar = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Review>> movieReviews = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Video>> movieVideos = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> popularMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> topRatedMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> upcomingMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> queriesMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Cast>> movieCastList = new MutableLiveData<>();
    private MutableLiveData<Movie> movieDetails = new MutableLiveData<>();
    private MutableLiveData<Collection> collection = new MutableLiveData<>();
    private MutableLiveData<Actor> actorDetails = new MutableLiveData<>();

    private final CompositeDisposable disposables = new CompositeDisposable();

    @ViewModelInject
    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Movie>> getCurrentlyShowingList() {
        return currentMoviesList;
    }

    public MutableLiveData<ArrayList<Movie>> getPopularMoviesList() {
        return popularMoviesList;
    }

    public MutableLiveData<ArrayList<Movie>> getTopRatedMoviesList() {
        return topRatedMoviesList;
    }

    public MutableLiveData<ArrayList<Movie>> getUpcomingMoviesList() {
        return upcomingMoviesList;
    }

    public MutableLiveData<Movie> getMovie() {
        return movieDetails;
    }

    public MutableLiveData<ArrayList<Video>> getMovieVideos() {
        return movieVideos;
    }

    public MutableLiveData<Actor> getActor() {
        return actorDetails;
    }

    public MutableLiveData<ArrayList<Movie>> getMovieSimilar() {
        return movieSimilar;
    }

    public MutableLiveData<ArrayList<Cast>> getMovieCastList() {
        return movieCastList;
    }

    public MutableLiveData<ArrayList<Movie>> getQueriesMovies() {
        return queriesMovies;
    }

    public MutableLiveData<ArrayList<Review>> getMovieReviews() {
        return movieReviews;
    }

    public MutableLiveData<Collection> getCollection() {
        return collection;
    }

    public MutableLiveData<ArrayList<Movie>> getTrendingMovieList() {
        return trendingMovieList;
    }

    public void getCurrentlyShowingMovies() {
        disposables.add(repository.getCurrentlyShowing()
                .subscribeOn(Schedulers.io())
                .map(new Function<MovieResponse, ArrayList<Movie>>() {
                    @Override
                    public ArrayList<Movie> apply(MovieResponse movieResponse) throws Throwable {
                        return movieResponse.getResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<Movie>>() {
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull ArrayList<Movie> movies) {
                        currentMoviesList.setValue(movies);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    public void getTrendingMovies(@NonNull MediaType mediaType, @NonNull TimeWindow timeWindow) {
        switch (mediaType) {
            case ALL:
                break;
            case MOVIE:
                disposables.add(repository.getTrendingMovies(mediaType.toString(), timeWindow.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> trendingMovieList.setValue(result.getResults()),
                                error -> Log.e(TAG, "getTrendingMovies: " + error.getMessage()))
                );
                break;
            case TV:
                break;
            case PERSON:
                //TODO catch trending persons
                break;
        }

    }

    public void getPopularMovies() {
        disposables.add(repository.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> popularMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getPopularMovies: " + error.getMessage()))
        );
    }

    public void getTopRatedMovies() {
        disposables.add(repository.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> topRatedMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getTopRated: " + error.getMessage()))
        );
    }

    public void getUpcomingMovies() {
        disposables.add(repository.getUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> upcomingMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getUpcoming: " + error.getMessage()))
        );
    }

    public void getVideos(@NonNull int movie_id) {
        disposables.add(repository.getVideos(movie_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieVideos.setValue(result.getResults()),
                        error -> Log.e(TAG, "getVideos: " + error.getMessage()))
        );
    }

    public void getMovieDetails(int movieId) {
        disposables.add(repository.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieDetails.setValue(result),
                        error -> Log.e(TAG, "getMovieDetails: " + error.getMessage()))
        );

    }

    public void getSimilar(int movieId) {
        disposables.add(repository.getSimilar(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieSimilar.setValue(result.getResults()),
                        error -> Log.e(TAG, "getSimilarMovies: " + error.getMessage()))
        );

    }

    public void getReviews(int movieId) {
        disposables.add(repository.getReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieReviews.setValue(result.getResults()),
                        error -> Log.e(TAG, "getMovieReviews: " + error.getMessage()))
        );

    }

    public void getCast(int movieId) {
        disposables.add(repository.getCast(movieId)
                .subscribeOn(Schedulers.io())
                .map(new Function<JsonObject, ArrayList<Cast>>() {
                    @Override
                    public ArrayList<Cast> apply(JsonObject jsonObject) throws Throwable {
                        JsonArray jsonArray = jsonObject.getAsJsonArray("cast");
                        return new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<Cast>>() {
                        }.getType());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieCastList.setValue(result),
                        error -> Log.e(TAG, "getCastList: " + error.getMessage()))
        );
    }

    public void getCollection(int collectionId) {
        disposables.add(repository.getCollection(collectionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> collection.setValue(result),
                        error -> Log.e(TAG, "getCastList: " + error.getMessage()))
        );
    }

    public void getActorDetails(int personId) {
        disposables.add(repository.getActorDetails(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> actorDetails.setValue(result),
                        error -> Log.e(TAG, "getActorDetails: " + error.getMessage()))
        );
    }

    public void getQueriedMovies() {
        disposables.add(repository.getMoviesBySearch()
                .subscribeOn(Schedulers.io())
                .map(new Function<JsonObject, ArrayList<Movie>>() {
                         @Override
                         public ArrayList<Movie> apply(JsonObject jsonObject) throws Throwable {
                             JsonArray jsonArray = jsonObject.getAsJsonArray("results");
                             ArrayList<Movie> movieList = new Gson().fromJson(jsonArray.toString(),
                                     new TypeToken<ArrayList<Movie>>() {
                                     }.getType());
                             return movieList;
                         }
                     }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> queriesMovies.setValue(result),
                        error -> Log.e(TAG, "getPopularMovies: " + error.getMessage()))
        );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
