package com.yeongjae.damoim.view.searchinformation.email.presenter;

import com.yeongjae.damoim.data.VerificationCode;

public interface SearchEmailContract {

    interface View {
        void showErrorMessage(String message);
        void startVerificationCodeActivity(String phone, String code);
    }

    interface Presenter {
        void startVerificationCode(String phone);
    }

}
