package com.example.cinemates.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 25/08/2022 at 0:00
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideQueryMap(): MutableMap<String, String> {
        val result: MutableMap<String, String> = HashMap()

        // Add default language parameter
        result["language"] = Locale.getDefault().language

        // Add default page parameter
        addQueryParam(result, "page", "1")

        // Add default append_to_response parameter
        addQueryParam(result, "append_to_response", "images")

        // Add default include_image_language parameter
        addQueryParam(result, "include_image_language", Locale.getDefault().language)

        return result
    }

    private fun addQueryParam(queryMap: MutableMap<String, String>, key: String, value: String) {
        if (!queryMap.containsKey(key)) {
            queryMap[key] = value
        }
    }

}