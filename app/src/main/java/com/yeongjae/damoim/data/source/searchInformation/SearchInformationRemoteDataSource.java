package com.yeongjae.damoim.data.source.searchInformation;

import com.yeongjae.damoim.data.SearchEmail;
import com.yeongjae.damoim.data.SearchEmailResponse;
import com.yeongjae.damoim.data.SearchPassword;
import com.yeongjae.damoim.data.SearchPasswordResponse;
import com.yeongjae.damoim.network.api.RetrofitApiClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchInformationRemoteDataSource implements SearchInformationSource {

    private static SearchInformationRemoteDataSource INSTANCE;

    public static SearchInformationRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SearchInformationRemoteDataSource();
        }

        return INSTANCE;
    }

    @Override
    public void callSearchEmail(SearchEmail searchEmail, SearchEmailApiListener listener) {
        Call<SearchEmailResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSearchEmail(searchEmail);

        result.enqueue(new Callback<SearchEmailResponse>() {
            @Override
            public void onResponse(Call<SearchEmailResponse> call, Response<SearchEmailResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("아이디 찾기에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<SearchEmailResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });

    }

    @Override
    public void callSearchPassword(SearchPassword searchPassword, SearchPasswordApiListener listener) {
        Call<SearchPasswordResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callSearchPassword(searchPassword);

        result.enqueue(new Callback<SearchPasswordResponse>() {
            @Override
            public void onResponse(Call<SearchPasswordResponse> call, Response<SearchPasswordResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                    return;
                }
                listener.onFail("비밀번호 찾기에 실패하였습니다.");
            }

            @Override
            public void onFailure(Call<SearchPasswordResponse> call, Throwable t) {
                listener.onFail("통신에 실패하였습니다.");
            }
        });
    }
}
