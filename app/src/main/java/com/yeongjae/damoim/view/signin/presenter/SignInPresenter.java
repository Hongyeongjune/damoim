package com.yeongjae.damoim.view.signin.presenter;

import com.yeongjae.damoim.data.SignIn;
import com.yeongjae.damoim.data.source.signin.SignInRepository;
import com.yeongjae.damoim.data.source.signin.SignInSource;
import com.yeongjae.damoim.lib.fcm.FcmTokenService;
import com.yeongjae.damoim.util.LogUtils;

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View signInView;
    private final SignInRepository signInRepository;

    public SignInPresenter(SignInContract.View signInView,
                           SignInRepository signInRepository) {

        this.signInView = signInView;
        this.signInRepository = signInRepository;
    }

    @Override
    public void callSignIn(String email, String password) {

        LogUtils.logDebug(FcmTokenService.getFcmToken());

        SignIn signIn = SignIn.builder()
                .email(email)
                .password(password)
                .fcmToken(FcmTokenService.getFcmToken())
                .build();

        if(signIn.isAllNonNull()) {
            signInRepository.callSignIn(signIn, new SignInSource.SignInApiListener() {
                @Override
                public void onSuccess(SignIn signIn) {
                    signInView.startDamoimActivity(signIn);
                }

                @Override
                public void onFail(String message) {
                    signInView.showErrorMessage(message);
                }
            });
            return ;
        }
        signInView.showErrorMessage("내용을 입력해주세요");
    }
}
