package com.yeongjae.damoim.data.source.searchInformation;

import com.yeongjae.damoim.data.SearchEmail;
import com.yeongjae.damoim.data.SearchEmailResponse;
import com.yeongjae.damoim.data.SearchPassword;
import com.yeongjae.damoim.data.SearchPasswordResponse;

public interface SearchInformationSource {

    interface SearchEmailApiListener {
        void onSuccess(SearchEmailResponse searchEmailResponse);
        void onFail(String message);
    }

    interface SearchPasswordApiListener {
        void onSuccess(SearchPasswordResponse searchPasswordResponse);
        void onFail(String message);
    }

    void callSearchEmail(SearchEmail searchEmail, SearchEmailApiListener listener);
    void callSearchPassword(SearchPassword searchPassword, SearchPasswordApiListener listener);
}
