package com.yeongjae.damoim.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yeongjae.damoim.MainActivity;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.util.LogUtils;
import com.yeongjae.damoim.util.PrefUtils;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.intro.IntroActivity;
import com.yeongjae.damoim.view.splash.presenter.SplashContract;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {

        context = getApplicationContext();

        loading();

        confirmAutoSignIn();
    }

    private void loading() {
        try {
            Thread.sleep(1500);
            LogUtils.logInfo("Splash");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 자동 로그인
     */
    private void confirmAutoSignIn() {
        if(PrefUtils.readAutoSignInFrom(context)) {
            ToastUtils.showToast(context, "자동 로그인 성공");
            startMainActivity();
        }
        else {
            startIntroActivity();
        }
    }

    @Override
    public void startIntroActivity() {
        Intent intent = new Intent(context, IntroActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
