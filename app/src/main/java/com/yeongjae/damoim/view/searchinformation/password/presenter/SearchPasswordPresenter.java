package com.yeongjae.damoim.view.searchinformation.password.presenter;

import android.telephony.SmsManager;

import com.yeongjae.damoim.data.SearchPassword;
import com.yeongjae.damoim.data.source.signup.overlap.EmailCheckRepository;
import com.yeongjae.damoim.data.source.signup.overlap.EmailCheckSource;

public class SearchPasswordPresenter implements SearchPasswordContract.Presenter {

    private SearchPasswordContract.View searchPasswordView;
    private final EmailCheckRepository emailCheckRepository;
    private String verificationCode;

    public SearchPasswordPresenter(SearchPasswordContract.View searchPasswordView, EmailCheckRepository emailCheckRepository) {
        this.searchPasswordView = searchPasswordView;
        this.emailCheckRepository = emailCheckRepository;
    }

    @Override
    public void startVerificationCode(String phone, String email) {

        if(SearchPassword.isSearchPasswordDataNull(phone)) {

            emailCheckRepository.callEmailCheck(email, new EmailCheckSource.ApiListener() {
                @Override
                public void onSuccess(String email) {
                    getVerificationCode();

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, "인증번호 : " + verificationCode, null, null);

                    searchPasswordView.startVerificationCodeActivity(phone, verificationCode, email);
                }

                @Override
                public void onFail(String message) {
                    searchPasswordView.showErrorMessage(message);
                }
            });
        }

        searchPasswordView.showErrorMessage("내용을 입력해주세요.");
    }



    private void getVerificationCode() {

        verificationCode = "";
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
            verificationCode += String.valueOf(random);
        }

    }
}
