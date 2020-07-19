package com.yeongjae.damoim.view.verificationcode.presenter;

import com.yeongjae.damoim.data.SearchEmail;
import com.yeongjae.damoim.data.VerificationCode;

public interface VerificationCodeContract {

    interface View {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
        void startNextSignUpActivity();
        void startIntroActivity();
    }

    interface Presenter {
        void callSearchEmail(String phone, VerificationCode verificationCode);
        void callSearchPassword(String phone, VerificationCode verificationCode);
        void startVerificationCode(String phone);
    }
}
