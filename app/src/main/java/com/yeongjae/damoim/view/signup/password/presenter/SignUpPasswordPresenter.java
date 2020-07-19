package com.yeongjae.damoim.view.signup.password.presenter;

import com.yeongjae.damoim.data.SignUp;

import java.util.regex.Pattern;

public class SignUpPasswordPresenter implements SignUpPasswordContract.Presenter {

    private SignUpPasswordContract.View signUpPasswordView;

    public SignUpPasswordPresenter(SignUpPasswordContract.View signUpPasswordView) {
        this.signUpPasswordView = signUpPasswordView;
    }

    @Override
    public void passwordPatternCheck(String password) {

        if (SignUp.isSignUpDataNonNull(password)) {
            String passwordPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,}$";

            Boolean pattern = Pattern.matches(passwordPattern, password);

            if (pattern) {
                signUpPasswordView.startNextSignUpActivity(password);
            } else {
                signUpPasswordView.showErrorMessage("대소문자 구분 숫자 특수문자 조합 9 ~ 12 자리를 입력하세요.");
            }
            return;
        }
        signUpPasswordView.showErrorMessage("내용을 입력해주세요.");
    }
}
