package com.yeongjae.damoim.view.verificationcode.presenter;

import android.telephony.SmsManager;

import com.yeongjae.damoim.data.SearchEmail;
import com.yeongjae.damoim.data.SearchEmailResponse;
import com.yeongjae.damoim.data.SearchPassword;
import com.yeongjae.damoim.data.SearchPasswordResponse;
import com.yeongjae.damoim.data.VerificationCode;
import com.yeongjae.damoim.data.source.searchInformation.SearchInformationRepository;
import com.yeongjae.damoim.data.source.searchInformation.SearchInformationSource;

public class VerificationCodePresenter implements VerificationCodeContract.Presenter{

    private VerificationCodeContract.View verificationCodeView;
    private final SearchInformationRepository searchInformationRepository;

    public VerificationCodePresenter(VerificationCodeContract.View verificationCodeView,
                                     SearchInformationRepository searchInformationRepository) {
        this.verificationCodeView = verificationCodeView;
        this.searchInformationRepository = searchInformationRepository;
    }


    @Override
    public void callSearchEmail(String phone, VerificationCode verificationCode) {

        if(phone.equals(verificationCode.getPhone())) {

            SearchEmail searchEmail = SearchEmail.builder()
                    .phone(verificationCode.getPhone())
                    .build();

            if(searchEmail.isAllNonNull()) {
                searchInformationRepository.callSearchEmail(searchEmail, new SearchInformationSource.SearchEmailApiListener() {
                    @Override
                    public void onSuccess(SearchEmailResponse searchEmailResponse) {
                        verificationCodeView.showSuccessMessage("메일로 이메일을 전송하였습니다.");

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(searchEmail.getPhone(), null,
                                "이메일 : " + getEmail(searchEmailResponse.getEmail()), null, null);
                        verificationCodeView.startIntroActivity();
                    }
                    @Override
                    public void onFail(String message) {
                        verificationCodeView.showErrorMessage(message);
                    }
                });
            }
        }
    }

    @Override
    public void callSearchPassword(String phone, VerificationCode verificationCode) {
        if(phone.equals(verificationCode.getPhone())) {

            SearchPassword searchPassword = SearchPassword.builder()
                    .phone(verificationCode.getPhone())
                    .email(verificationCode.getEmail())
                    .build();

            if(searchPassword.isAllNonNull()) {
                searchInformationRepository.callSearchPassword(searchPassword, new SearchInformationSource.SearchPasswordApiListener() {
                    @Override
                    public void onSuccess(SearchPasswordResponse searchPasswordResponse) {
                        verificationCodeView.showSuccessMessage("메일로 비밀번호를 전송하였습니다.");

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(searchPassword.getPhone(), null,
                                "이메일 : " + getEmail(searchPasswordResponse.getPassword()), null, null);
                        verificationCodeView.startIntroActivity();
                    }

                    @Override
                    public void onFail(String message) {
                        verificationCodeView.showErrorMessage(message);
                    }
                });
            }
        }
    }

    @Override
    public void startVerificationCode(String phone) {

    }


    private String getEmail(String email) {

        int count = email.indexOf("@");
        String result = email.substring(0,1);
        for(int i=2; i<count; i++) {
            result += "*";
        }

        result += email.substring(count+1);

        return result;
    }
}
