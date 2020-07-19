package com.yeongjae.damoim.view.signup.sex.presenter;

import com.yeongjae.damoim.data.SignUp;

public interface SignUpSexContract {

    interface View {
        void showErrorMessage(String message);
        void startSignInActivity();
    }

    interface Presenter {
        void callSignUp(SignUp signUp);
    }
}
