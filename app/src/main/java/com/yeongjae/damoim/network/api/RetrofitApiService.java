package com.yeongjae.damoim.network.api;

import com.yeongjae.damoim.data.SearchEmail;
import com.yeongjae.damoim.data.SearchEmailResponse;
import com.yeongjae.damoim.data.SearchPassword;
import com.yeongjae.damoim.data.SearchPasswordResponse;
import com.yeongjae.damoim.data.SignIn;
import com.yeongjae.damoim.data.SignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApiService {

    /**
     * 회원 정보 관련
     * 로그인 / 아이디 중복확인 / 회원가입
     */
    @POST("damoim/sign/signIn")
    Call<SignIn> callSignIn(@Body SignIn signIn);

    @GET("damoim/sign/check/{email}")
    Call<String> callEmailCheck(@Path("email") String email);

    @POST("damoim/sign")
    Call<Void> callSignUp(@Body SignUp signUp);

    @POST("")
    Call<SearchEmailResponse> callSearchEmail(@Body SearchEmail searchEmail);

    @POST("")
    Call<SearchPasswordResponse> callSearchPassword(@Body SearchPassword searchPassword);

}
