package com.ade.step2.data.api;

import com.ade.step2.helper.Constant;
import com.ade.step2.model.api.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public abstract class BaseApi {

    static final String BASE_URL = Constant.BUKALAPAK;
    static final int TIMEOUT = 60;

    ApiService mApiService;
    ApiResponse apiResponse;

    public BaseApi() {

        apiResponse = new ApiResponse();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(logInterceptor);

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        mApiService = retrofit.create(ApiService.class);
    }

    abstract public void onFinishRequest(boolean success, ApiResponse response);

}
