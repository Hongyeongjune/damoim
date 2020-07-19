package com.yeongjae.damoim.view.signup.email.presenter;

public interface SignUpEmailContract {

    interface View {

        void showErrorMessage(String message);
        void startNextSignUpActivity(String email);

    }

    interface Presenter {
        void callEmailCheck(String email);
        void emailPatternCheck(String email);
    }
}
