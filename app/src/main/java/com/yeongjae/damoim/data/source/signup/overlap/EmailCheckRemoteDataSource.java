package com.yeongjae.damoim.data.source.signup.overlap;

import com.yeongjae.damoim.network.api.RetrofitApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailCheckRemoteDataSource implements EmailCheckSource{

    private static EmailCheckRemoteDataSource INSTANCE;

    public static EmailCheckRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EmailCheckRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callEmailCheck(String email, ApiListener listener) {

        Call<String> result = RetrofitApiClient.getInstance().getRetrofitApiService().callEmailCheck(email);

        result.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(email);
                    return;
                }
                listener.onFail("아이디를 사용할 수 없거나 올바르지 않습니다.");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
