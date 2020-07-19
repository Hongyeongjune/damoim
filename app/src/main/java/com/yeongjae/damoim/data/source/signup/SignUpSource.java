package com.yeongjae.damoim.data.source.signup;

import com.yeongjae.damoim.data.SignUp;

public interface SignUpSource {

    interface ApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callSignUp(SignUp signUp, ApiListener listener);
}
