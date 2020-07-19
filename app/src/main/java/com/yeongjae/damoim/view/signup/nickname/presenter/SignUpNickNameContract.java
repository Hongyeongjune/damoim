package com.yeongjae.damoim.view.signup.nickname.presenter;

public interface SignUpNickNameContract {

    interface View {
        void showErrorMessage(String message);
        void startNextSignUpActivity(String password);
    }

    interface Presenter {
        void startNextSignUp(String nickName);
    }

}
