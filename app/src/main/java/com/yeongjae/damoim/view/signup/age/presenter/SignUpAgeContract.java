package com.yeongjae.damoim.view.signup.age.presenter;

public interface SignUpAgeContract {

    interface View {
        void showErrorMessage(String message);
        void startNextSignUpActivity(String age);
    }

    interface Presenter {
        void startNextSignUp(String age);
    }
}
