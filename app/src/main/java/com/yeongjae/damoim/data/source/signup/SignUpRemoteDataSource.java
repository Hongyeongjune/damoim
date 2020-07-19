package com.yeongjae.damoim.data.source.signup;

import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.network.api.RetrofitApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRemoteDataSource implements SignUpSource{

    private static SignUpRemoteDataSource INSTANCE;

    public static  SignUpRemoteDataSource getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new SignUpRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callSignUp(SignUp signUp, ApiListener listener) {
        Call<Void> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSignUp(signUp);
        result.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    listener.onSuccess();
                    return;
                }
                listener.onFail("회원가입에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
