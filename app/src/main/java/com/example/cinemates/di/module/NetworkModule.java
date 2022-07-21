package com.example.cinemates.di.module;


import com.example.cinemates.interfaces.MovieApiService;
import com.example.cinemates.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:51
 */
@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Singleton
    @Provides
    public static MovieApiService provideMovieApiService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(MovieApiService.class);
    }
}
