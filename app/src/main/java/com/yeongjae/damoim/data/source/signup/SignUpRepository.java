package com.yeongjae.damoim.data.source.signup;

import com.yeongjae.damoim.data.SignUp;

public class SignUpRepository implements SignUpSource {

    private static SignUpRepository signUpRepository;
    private SignUpRemoteDataSource signUpRemoteDataSource;
    private static SignUp signUp;

    public static SignUpRepository getInstance() {
        if(signUpRepository == null) {
            signUpRepository = new SignUpRepository();
        }

        return signUpRepository;
    }

    public static SignUp getInstanceSignUp() {

        if(signUp == null) {
            signUp = new SignUp();
        }

        return signUp;
    }

    private SignUpRepository() {
        signUpRemoteDataSource = SignUpRemoteDataSource.getInstance();
    }


    @Override
    public void callSignUp(SignUp signUp, ApiListener listener) {
        signUpRemoteDataSource.callSignUp(signUp, listener);
    }
}
