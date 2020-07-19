package com.yeongjae.damoim.view.signup.age.presenter;

import com.yeongjae.damoim.data.SignUp;

public class SignUpAgePresenter implements SignUpAgeContract.Presenter {

    private SignUpAgeContract.View signUpAgeView;

    public SignUpAgePresenter(SignUpAgeContract.View signUpAgeView) {
        this.signUpAgeView = signUpAgeView;
    }

    @Override
    public void startNextSignUp(String age) {

        if(SignUp.isSignUpDataNonNull(age)) {

            signUpAgeView.startNextSignUpActivity(age);

            return;
        }

        signUpAgeView.showErrorMessage("내용을 입력해주세요.");

    }
}
