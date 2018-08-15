package com.movinfo.movinfo.data.network;

import com.movinfo.movinfo.BuildConfig;
import com.movinfo.movinfo.utils.Constants;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class MovieDbApi {
    private static MovieDbService sMovieDbApi = null;

    private MovieDbApi() {
    }

    public static MovieDbService getInstance() {
        if (sMovieDbApi == null) {
            // Build a client with an interceptor to add the API key
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request initialRequest = chain.request();
                        HttpUrl initialHttpUrl = initialRequest.url();

                        HttpUrl modifiedHttpUrl = initialHttpUrl.newBuilder()
                                .addQueryParameter(Constants.API_KEY_REQUEST_KEY,
                                        BuildConfig.TheMovieDbApiToken)
                                .build();
                        Request modifiedRequest = initialRequest.newBuilder()
                                .url(modifiedHttpUrl)
                                .build();

                        return chain.proceed(modifiedRequest);
                    })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.MOVIE_DB_API_BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sMovieDbApi = retrofit.create(MovieDbService.class);
        }
        return sMovieDbApi;
    }
}
