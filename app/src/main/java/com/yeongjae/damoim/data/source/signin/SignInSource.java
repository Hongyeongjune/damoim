package com.yeongjae.damoim.data.source.signin;

import com.yeongjae.damoim.data.SignIn;

public interface SignInSource {

    interface SignInApiListener {
        void onSuccess(SignIn signIn);
        void onFail(String message);
    }

    void callSignIn(SignIn signIn, SignInApiListener listener);
}
