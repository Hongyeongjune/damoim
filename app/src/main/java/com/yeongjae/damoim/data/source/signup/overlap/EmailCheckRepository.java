package com.yeongjae.damoim.data.source.signup.overlap;

public class EmailCheckRepository implements EmailCheckSource {

    private static EmailCheckRepository emailCheckRepository;
    private EmailCheckRemoteDataSource emailCheckRemoteDataSource;

    public static EmailCheckRepository getInstance() {

        if(emailCheckRepository == null) {
            emailCheckRepository = new EmailCheckRepository();
        }

        return emailCheckRepository;
    }

    private EmailCheckRepository() {
        emailCheckRemoteDataSource = emailCheckRemoteDataSource.getInstance();
    }

    @Override
    public void callEmailCheck(String email, ApiListener listener) {
        emailCheckRemoteDataSource.callEmailCheck(email, listener);
    }

}
