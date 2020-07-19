package com.yeongjae.damoim.data.source.signup.overlap;

public interface EmailCheckSource {

    interface ApiListener {
        void onSuccess(String email);
        void onFail(String message);
    }

    void callEmailCheck(String email, ApiListener listener);
}
