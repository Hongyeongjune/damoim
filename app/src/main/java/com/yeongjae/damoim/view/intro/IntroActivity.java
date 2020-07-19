package com.yeongjae.damoim.view.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.lib.fcm.FcmTokenService;
import com.yeongjae.damoim.view.intro.presenter.IntroContract;
import com.yeongjae.damoim.view.main.DamoimActivity;
import com.yeongjae.damoim.view.signin.SignInActivity;
import com.yeongjae.damoim.view.signup.email.SignUpEmailActivity;

public class IntroActivity extends AppCompatActivity implements IntroContract.View {

    private Context context;
    private Button btnStartSignIn;
    private Button btnStartSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();
    }

    private void init() {
        FcmTokenService.getFcmToken();
        context = getApplicationContext();
        btnStartSignIn = (Button) findViewById(R.id.btn_start_signin);
        btnStartSignUp = (Button) findViewById(R.id.btn_start_signup);

        btnStartSignIn.setOnClickListener(v -> startSignInActivity());

        btnStartSignUp.setOnClickListener(v -> startSignUpActivity());
    }

    /**
     * 로그인
     */
    @Override
    public void startSignInActivity() {
        Intent intent = new Intent(context, DamoimActivity.class);
        startActivity(intent);
    }

    /**
     * 회원가입
     */
    @Override
    public void startSignUpActivity() {
        Intent intent = new Intent(context, SignUpEmailActivity.class);
        startActivity(intent);
    }
}
