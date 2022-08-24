package com.example.cinemates.view.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemates.model.data.Actor;
import com.example.cinemates.model.data.Cast;
import com.example.cinemates.model.data.Collection;
import com.example.cinemates.model.data.Genre;
import com.example.cinemates.model.data.Images;
import com.example.cinemates.model.data.Movie;
import com.example.cinemates.model.data.Person;
import com.example.cinemates.model.data.Response;
import com.example.cinemates.model.data.Review;
import com.example.cinemates.model.data.Video;
import com.example.cinemates.model.repository.MovieRepository;
import com.example.cinemates.util.MediaType;
import com.example.cinemates.util.TimeWindow;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
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
@HiltViewModel
public class MovieViewModel extends ViewModel {
    private static final String TAG = MovieViewModel.class.getSimpleName();

    private final MovieRepository movieRepository;
    private final MutableLiveData<List<Movie>> currentMoviesList = new MutableLiveData<>();
    private final MutableLiveData<List<Genre>> genreList = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesByActor = new MutableLiveData<>();//added
    private final MutableLiveData<List<Movie>> filteredMovies = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> trendingMovieList = new MutableLiveData<>();//added
    private final MutableLiveData<List<Person>> trendingPerson = new MutableLiveData<>();//added
    private final MutableLiveData<List<Movie>> movieSimilar = new MutableLiveData<>();//added
    private final MutableLiveData<List<Review>> movieReviews = new MutableLiveData<>();
    private final MutableLiveData<Images> images = new MutableLiveData<>();//added
    private final MutableLiveData<List<Video>> movieVideos = new MutableLiveData<>();//added
    private final MutableLiveData<List<Movie>> popularMoviesList = new MutableLiveData<>();//added
    private final MutableLiveData<List<Movie>> topRatedMoviesList = new MutableLiveData<>();//added
    private final MutableLiveData<List<Movie>> upcomingMoviesList = new MutableLiveData<>();//added
    private final MutableLiveData<List<Movie>> queriesMovies = new MutableLiveData<>();
    private final MutableLiveData<List<Cast>> queriesPeoples = new MutableLiveData<>();
    private final MutableLiveData<List<Cast>> movieCastList = new MutableLiveData<>();//added
    private final MutableLiveData<Movie> movieDetails = new MutableLiveData<>();//added
    private final MutableLiveData<Collection> collection = new MutableLiveData<>();//added
    private final MutableLiveData<Actor> actorDetails = new MutableLiveData<>();//added

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MutableLiveData<List<Movie>> getFilteredMovies() {
        return filteredMovies;
    }

    public MutableLiveData<List<Movie>> getCurrentlyShowingList() {
        return currentMoviesList;
    }

    public MutableLiveData<List<Movie>> getPopularMoviesList() {
        return popularMoviesList;
    }

    public MutableLiveData<List<Movie>> getTopRatedMoviesList() {
        return topRatedMoviesList;
    }

    public MutableLiveData<List<Movie>> getUpcomingMoviesList() {
        return upcomingMoviesList;
    }

    public MutableLiveData<Movie> getMovie() {
        return movieDetails;
    }

    public MutableLiveData<List<Genre>> getGenreList() {
        return genreList;
    }

    public MutableLiveData<List<Video>> getMovieVideos() {
        return movieVideos;
    }

    public MutableLiveData<Images> getImages() {
        return images;
    }

    public MutableLiveData<List<Person>> getTrendingPerson() {
        return trendingPerson;
    }

    public MutableLiveData<Actor> getActor() {
        return actorDetails;
    }

    public MutableLiveData<List<Cast>> getQueriesPeoples() {
        return queriesPeoples;
    }

    public MutableLiveData<List<Movie>> getMoviesByActor() {
        return moviesByActor;
    }

    public MutableLiveData<List<Movie>> getMovieSimilar() {
        return movieSimilar;
    }

    public MutableLiveData<List<Cast>> getMovieCastList() {
        return movieCastList;
    }

    public MutableLiveData<List<Movie>> getQueriedMovies() {
        return queriesMovies;
    }

    public MutableLiveData<List<Review>> getMovieReviews() {
        return movieReviews;
    }

    public MutableLiveData<Collection> getCollection() {
        return collection;
    }

    public MutableLiveData<List<Movie>> getTrendingMovieList() {
        return trendingMovieList;
    }

    public void getCurrentlyShowingMovies() {
        disposables.add(movieRepository.getCurrentlyShowing()
                .subscribeOn(Schedulers.io())
                .map(new Function<Response<Movie>, ArrayList<Movie>>() {
                    @Override
                    public ArrayList<Movie> apply(Response<Movie> movieResponse) throws Throwable {
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

    public void getTrending(@NonNull MediaType mediaType, @NonNull TimeWindow timeWindow) {
        switch (mediaType) {
            case ALL:
                break;
            case MOVIE:
                disposables.add(movieRepository.getTrendingMovies(mediaType.toString(), timeWindow.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> trendingMovieList.setValue(result.getResults()),
                                error -> Log.e(TAG, "getTrendingMovies: " + error.getMessage()))
                );
                break;
            case TV:
                break;
            case PERSON:
                disposables.add(movieRepository.getTrendingPerson(mediaType.toString(), timeWindow.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> trendingPerson.setValue(result.getResults()),
                                error -> Log.e(TAG, "getTrendingPerson: " + error.getMessage()))
                );
                break;
        }

    }

    public void getPopularMovies() {
        disposables.add(movieRepository.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> popularMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getPopularMovies: " + error.getMessage()))
        );
    }

    public void getGenres() {
        disposables.add(movieRepository.getGenreList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> genreList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getGenres: " + error.getMessage()))
        );
    }

    public void getTopRatedMovies() {
        disposables.add(movieRepository.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> topRatedMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getTopRated: " + error.getMessage()))
        );
    }

    public void getUpcomingMovies() {
        disposables.add(movieRepository.getUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> upcomingMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getUpcoming: " + error.getMessage()))
        );
    }

    public void getVideos(@NonNull int movie_id) {
        disposables.add(movieRepository.getVideos(movie_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieVideos.setValue(result.getResults()),
                        error -> Log.e(TAG, "getVideos: " + error.getMessage()))
        );
    }

    public void getMovieDetails(int movieId) {
        disposables.add(movieRepository.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieDetails.setValue(result),
                        error -> Log.e(TAG, "getMovieDetails: " + error.getMessage()))
        );

    }

    public void getSimilar(int movieId) {
        disposables.add(movieRepository.getSimilar(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieSimilar.setValue(result.getResults()),
                        error -> Log.e(TAG, "getSimilarMovies: " + error.getMessage()))
        );

    }

    public void getReviews(int movieId) {
        disposables.add(movieRepository.getReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieReviews.setValue(result.getResults()),
                        error -> Log.e(TAG, "getMovieReviews: " + error.getMessage()))
        );

    }

    public void getImages(int movieId) {
        disposables.add(movieRepository.getImages(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> images.setValue(result),
                        error -> Log.e(TAG, "getMovieImages: " + error.getMessage()))
        );

    }

    public void getCast(int movieId) {
        disposables.add(movieRepository.getCast(movieId)
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
        disposables.add(movieRepository.getCollection(collectionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> collection.setValue(result),
                        error -> Log.e(TAG, "getCollection: " + error.getMessage()))
        );
    }

    public void getMoviesByActor(String with_cast) {
        disposables.add(movieRepository.getMoviesByActor(with_cast)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> moviesByActor.setValue(result.getResults()),
                        error -> Log.e(TAG, "getMoviesByActor: " + error.getMessage()))
        );
    }

    public void getActorDetails(int personId) {
        disposables.add(movieRepository.getActorDetails(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> actorDetails.setValue(result),
                        error -> Log.e(TAG, "getActorDetails: " + error.getMessage()))
        );
    }

    public void getDiscoverMovies(String sort_value, String genre_id) {
        disposables.add(movieRepository.getDiscoverMovies(sort_value, genre_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> filteredMovies.setValue(result.getResults()),
                        error -> Log.e(TAG, "getDiscoverMovies: " + error.getMessage()))
        );
    }


    public void getQueriedMovies(String query) {
        if (!TextUtils.isEmpty(query)) {
            disposables.add(movieRepository.getMoviesBySearch(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> queriesMovies.setValue(result.getResults()),
                            error -> Log.e(TAG, "getQueriedMovies: " + error.getMessage()))
            );
        } else
            queriesMovies.setValue(Collections.emptyList());
    }

    public void getPeoplesBySearch(String query) {
        if (!TextUtils.isEmpty(query)) {
            disposables.add(movieRepository.getPeoplesBySearch(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> queriesPeoples.setValue(result.getResults()),
                            error -> Log.e(TAG, "getQueriedPeoples: " + error.getMessage()))
            );
        } else
            queriesPeoples.setValue(Collections.emptyList());
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
