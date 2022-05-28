package com.example.cinemates.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.Actor;
import com.example.cinemates.model.Cast;
import com.example.cinemates.model.Collection;
import com.example.cinemates.model.Genre;
import com.example.cinemates.model.Movie;
import com.example.cinemates.model.MovieResponse;
import com.example.cinemates.repository.Repository;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

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
    private MutableLiveData<ArrayList<Movie>> movieRecommendedList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> popularMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> topRatedMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> upcomingMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> queriesMovies = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Cast>> movieCastList = new MutableLiveData<>();
    private MutableLiveData<Movie> movieDetails = new MutableLiveData<>();
    private MutableLiveData<Collection> collection = new MutableLiveData<>();
    private MutableLiveData<Actor> actorDetails = new MutableLiveData<>();

    private final io.reactivex.rxjava3.disposables.CompositeDisposable disposables = new CompositeDisposable();

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

    public MutableLiveData<Actor> getActor() {
        return actorDetails;
    }

    public MutableLiveData<ArrayList<Movie>> getMovieRecommendedList() {
        return movieRecommendedList;
    }

    public MutableLiveData<ArrayList<Cast>> getMovieCastList() {
        return movieCastList;
    }

    public MutableLiveData<ArrayList<Movie>> getQueriesMovies() {
        return queriesMovies;
    }

    public MutableLiveData<Collection> getCollection() {
        return collection;
    }

    public MutableLiveData<ArrayList<Movie>> getTrendingMovieList() {
        return trendingMovieList;
    }

    public void getCurrentlyShowingMovies(HashMap<String, String> map) {
        disposables.add(repository.getCurrentlyShowing(map)
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

    public void getTrendingMovies(@NonNull MediaType mediaType, @NonNull TimeWindow timeWindow, HashMap<String, String> map) {
        switch (mediaType) {
            case ALL:
                break;
            case MOVIE:
                disposables.add(repository.getTrendingMovies(mediaType.toString(), timeWindow.toString(), map)
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

    public void getPopularMovies(HashMap<String, String> map) {
        disposables.add(repository.getPopular(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> popularMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getPopularMovies: " + error.getMessage()))
        );
    }

    public void getTopRatedMovies(HashMap<String, String> map) {
        disposables.add(repository.getTopRated(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> topRatedMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getTopRated: " + error.getMessage()))
        );
    }

    public void getUpcomingMovies(HashMap<String, String> map) {
        disposables.add(repository.getUpcoming(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> upcomingMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getUpcoming: " + error.getMessage()))
        );
    }

    public void getMovieDetails(int movieId, HashMap<String, String> map) {
        disposables.add(repository.getMovieDetails(movieId, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieDetails.setValue(result),
                        error -> Log.e(TAG, "getMovieDetails: " + error.getMessage()))
        );

    }

    public void getRecommendations(int movieId, HashMap<String, String> map) {
        disposables.add(repository.getRecommendations(movieId, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieRecommendedList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getRecommendedMovies: " + error.getMessage()))
        );

    }

    public void getCast(int movieId, HashMap<String, String> map) {
        disposables.add(repository.getCast(movieId, map)
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

    public void getCollection(int collectionId, HashMap<String, String> map) {
        disposables.add(repository.getCollection(collectionId, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> collection.setValue(result),
                        error -> Log.e(TAG, "getCastList: " + error.getMessage()))
        );
    }

    public void getActorDetails(int personId, HashMap<String, String> map) {
        disposables.add(repository.getActorDetails(personId, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> actorDetails.setValue(result),
                        error -> Log.e(TAG, "getActorDetails: " + error.getMessage()))
        );
    }

    public void getQueriedMovies(HashMap<String, String> map) {
        disposables.add(repository.getMoviesBySearch(map)
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
