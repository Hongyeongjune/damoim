package com.yeongjae.damoim.view.signup.qauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;


import com.yeongjae.damoim.R;

public class SignUpQauthActivity extends AppCompatActivity {

    private Context context;
//    private OAuthLogin mOAuthLoginModule;
    private static final String OAUTH_CLIENT_ID = "lboUuF39PiiWw0iGrUJY";
    private static final String OAUTH_CLIENT_SECRET = "6Xq0eBODPx";
    private static final String OAUTH_CLIENT_NAME = "DAMOIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_qauth);

        init();
    }

    private void init() {

        context = getApplicationContext();
        setNaver();

    }

    private void setNaver() {

//        mOAuthLoginModule = OAuthLogin.getInstance();
//        mOAuthLoginModule.init(context, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

    }
}
