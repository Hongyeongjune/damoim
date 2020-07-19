package com.yeongjae.damoim.view.signup.phone.presenter;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;

import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.util.LogUtils;
import com.yeongjae.damoim.util.ToastUtils;

public class SignUpPhonePresenter implements SignUpPhoneContract.Presenter{

    private SignUpPhoneContract.View signUpPhoneView;
    private String verificationCode;

    public SignUpPhonePresenter(SignUpPhoneContract.View signUpPhoneView) {
        this.signUpPhoneView = signUpPhoneView;
    }

    @Override
    public void startVerificationCode(String phone) {

//        if(SignUp.isSignUpDataNonNull(phone) && !SignUp.isSignUpDataNonNull(verification)) {

        if(SignUp.isSignUpDataNonNull(phone)) {
            signUpPhoneView.showVerificationMessage("문자메세지를 확인하여 인증번호를 입력하세요.");

//            signUpPhoneView.showInputVerificationCode();

            getVerificationCode();

            LogUtils.logInfo(verificationCode + " Code");
            signUpPhoneView.startNextSignUpActivity(phone, verificationCode);
//            signUpPhoneView.sendSMS(phone, verificationCode);

            return;

        }

        signUpPhoneView.showErrorMessage("내용을 입력해주세요.");
//        else if(SignUp.isSignUpDataNonNull(phone) && SignUp.isSignUpDataNonNull(verification)) {
//
//            if (this.verificationCode.equals(verificationCode)) {
//                signUpPhoneView.startNextSignUpActivity(phone);
//            } else {
//                signUpPhoneView.showErrorMessage("인증번호가 일치하지 않습니다.");
//            }
//            return;
//        }
    }

//    @Override
//    public void requestRepeat(String phone) {
//
//        if(SignUp.isSignUpDataNonNull(phone)) {
//            getVerificationCode();
//
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phone, null, "인증번호 : " + verificationCode, null, null);
//
//            return;
//        }
//
//        signUpPhoneView.showErrorMessage("내용을 입력해주세요.");
//
//    }

    private void getVerificationCode() {

        verificationCode = "";
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
//            verificationCode += String.valueOf(random);
            verificationCode += String.valueOf(i);
        }

    }


}
