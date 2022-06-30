package com.taghive.networkInterfaces;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static RetrofitApi retrofitApi = null;
    static String MAIN_URL = "https://api.wazirx.com/sapi/v1/tickers/";

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor())
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        Log.e(RetrofitClient.class.getSimpleName(), request.url().toString());
                        return chain.proceed(request);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitApi getAPIService() {
        if (retrofitApi == null)
            retrofitApi = getClient(MAIN_URL).create(RetrofitApi.class);
        return retrofitApi;
    }

    public static RetrofitApi getAPIService(Context context) {
        return getAPIService();
    }
}
