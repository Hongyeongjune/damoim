package com.yeongjae.damoim.view.signup.sex.presenter;

import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.data.source.signup.SignUpRepository;
import com.yeongjae.damoim.data.source.signup.SignUpSource;

public class SignUpSexPresenter implements SignUpSexContract.Presenter {

    private SignUpSexContract.View signUpSexView;
    private final SignUpRepository signUpRepository;

    public SignUpSexPresenter(SignUpSexContract.View signUpSexView,
                              SignUpRepository signUpRepository) {
        this.signUpSexView = signUpSexView;
        this.signUpRepository = signUpRepository;
    }


    @Override
    public void callSignUp(SignUp signUp) {
        if(signUp.isAllNonNull()) {
            signUpRepository.callSignUp(signUp, new SignUpSource.ApiListener() {
                @Override
                public void onSuccess() {
                    signUpSexView.startSignInActivity();
                }

                @Override
                public void onFail(String message) {
                    signUpSexView.showErrorMessage(message);
                }
            });

            return;
        }
        signUpSexView.showErrorMessage("내용이 올바르지 않습니다.");
    }
}
