package com.yeongjae.damoim.view.searchinformation.email.presenter;

import android.telephony.SmsManager;

import com.yeongjae.damoim.data.SearchEmail;

public class SearchEmailPresenter implements SearchEmailContract.Presenter {

    private SearchEmailContract.View searchEmailView;
    private String verificationCode;

    public SearchEmailPresenter(SearchEmailContract.View searchEmailView) {
        this.searchEmailView = searchEmailView;
    }


    @Override
    public void startVerificationCode(String phone) {

        if(SearchEmail.isSearchEmailDataNull(phone)) {
            getVerificationCode();

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, "인증번호 : " + verificationCode, null, null);

            searchEmailView.startVerificationCodeActivity(phone, verificationCode);

            return;
        }

        searchEmailView.showErrorMessage("내용을 입력해주세요.");
    }

    private void getVerificationCode() {

        verificationCode = "";
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
            verificationCode += String.valueOf(random);
        }

    }
}
