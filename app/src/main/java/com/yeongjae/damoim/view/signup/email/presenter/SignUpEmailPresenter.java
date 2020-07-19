package com.yeongjae.damoim.view.signup.email.presenter;

import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.data.source.signup.overlap.EmailCheckRepository;
import com.yeongjae.damoim.data.source.signup.overlap.EmailCheckSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpEmailPresenter implements SignUpEmailContract.Presenter {

    private SignUpEmailContract.View signUpEmailView;
    private final EmailCheckRepository emailCheckRepository;

    public SignUpEmailPresenter(SignUpEmailContract.View signUpEmailView,
                                EmailCheckRepository emailCheckRepository) {
        this.signUpEmailView = signUpEmailView;
        this.emailCheckRepository = emailCheckRepository;
    }

    /**
     * 아이디 중복체크
     * @param email : 중복체크할 아이디
     */
    @Override
    public void callEmailCheck(String email) {

        if (SignUp.isSignUpDataNonNull(email)) {
            emailCheckRepository.callEmailCheck(email, new EmailCheckSource.ApiListener() {
                @Override
                public void onSuccess(String email) {
                    signUpEmailView.startNextSignUpActivity(email);
                }

                @Override
                public void onFail(String message) {
                    signUpEmailView.showErrorMessage(message);
                }
            });
            return;
        }
        signUpEmailView.showErrorMessage("내용을 입력해주세요.");
    }

    @Override
    public void emailPatternCheck(String email) {

        if (SignUp.isSignUpDataNonNull(email)) {
            String emailPattern = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

            Pattern p = Pattern.compile(emailPattern);
            Matcher matcher = p.matcher(email);

            boolean pattern = matcher.matches();

            if(pattern) {
                callEmailCheck(email);
            } else {
                signUpEmailView.showErrorMessage("이메일 형식에 맞게 입력해주세요.");
            }

            return;
        }

        signUpEmailView.showErrorMessage("내용을 입력해주세요.");
    }
}

