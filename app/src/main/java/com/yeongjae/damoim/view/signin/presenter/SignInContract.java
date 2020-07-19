package com.yeongjae.damoim.view.signin.presenter;

import com.yeongjae.damoim.data.SignIn;

public interface SignInContract {

    interface View {
        void showErrorMessage(String message);
        void startDamoimActivity(SignIn signIn);
        void startSearchInformationActivity();
    }

    interface Presenter {
        void callSignIn(String email, String password);
    }
}
