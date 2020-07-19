package com.yeongjae.damoim.view.signup.phone.presenter;

public interface SignUpPhoneContract {

    interface View {
        void showErrorMessage(String message);
        void startNextSignUpActivity(String phone, String verificationCode);
        void showVerificationMessage(String message);
        void sendSMS(String phone, String verificationCode);
//        void showInputVerificationCode();
    }

    interface Presenter {
        void startVerificationCode(String phone);
//        void requestRepeat(String phone);
    }

}
