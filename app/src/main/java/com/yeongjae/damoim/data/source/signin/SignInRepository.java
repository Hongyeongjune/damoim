package com.yeongjae.damoim.data.source.signin;

import com.yeongjae.damoim.data.SignIn;

public class SignInRepository implements SignInSource {

    private static SignInRepository signInRepository;
    private SignInRemoteDataSource signInRemoteDataSource;

    public static SignInRepository getInstance() {

        if(signInRepository == null) {
            signInRepository = new SignInRepository();
        }

        return signInRepository;
    }

    private SignInRepository() {
        signInRemoteDataSource = SignInRemoteDataSource.getInstance();
    }

    @Override
    public void callSignIn(SignIn signIn, SignInApiListener listener) {
        signInRemoteDataSource.callSignIn(signIn, listener);
    }
}
