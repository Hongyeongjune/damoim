package com.yeongjae.damoim.view.signup.nickname.presenter;

import com.yeongjae.damoim.data.SignUp;

public class SignUpNickNamePresenter implements SignUpNickNameContract.Presenter {

    private SignUpNickNameContract.View signUpNickNameView;

    public SignUpNickNamePresenter(SignUpNickNameContract.View signUpNickNameView) {
        this.signUpNickNameView = signUpNickNameView;
    }
    
    @Override
    public void startNextSignUp(String nickName) {

        if(SignUp.isSignUpDataNonNull(nickName)) {

            signUpNickNameView.startNextSignUpActivity(nickName);

            return;
        }

        signUpNickNameView.showErrorMessage("내용을 입력해주세요.");
    }
}
