package com.yeongjae.damoim.view.signup.password.presenter;

public interface SignUpPasswordContract {

    interface View {
        void showErrorMessage(String message);
        void startNextSignUpActivity(String password);
    }

    interface Presenter {
        void passwordPatternCheck(String password);
    }

}
