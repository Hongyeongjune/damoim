package com.yeongjae.damoim.view.searchinformation.password.presenter;

public interface SearchPasswordContract {

    interface View {
        void showErrorMessage(String message);
        void startVerificationCodeActivity(String phone, String code, String email);
    }

    interface Presenter {
        void startVerificationCode(String phone, String email);
    }
}
