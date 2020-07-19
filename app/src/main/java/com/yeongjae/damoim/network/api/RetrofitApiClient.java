package com.yeongjae.damoim.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrofitApiClient {

    private final String URL = "http://54.180.106.47:8080/";
    private static final RetrofitApiClient INSTANCE = new RetrofitApiClient();
    private RetrofitApiService retrofitApiService;

    // 통신을 할 시 json 사용과 해당 객체로의 파싱을 위해 생성
    private Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static RetrofitApiClient getInstance() {
        return INSTANCE;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)   // 서버 주소
            .addConverterFactory(GsonConverterFactory.create(gson)) // Json 사용을 위해 ConvertFactory 추가
            .build();

    // api 사용을 위한 서비스 생성 => 싱글톤
    public RetrofitApiService getRetrofitApiService() {
        if (retrofitApiService == null) {
            retrofitApiService = retrofit.create(RetrofitApiService.class);
        }
        return retrofitApiService;
    }
}
