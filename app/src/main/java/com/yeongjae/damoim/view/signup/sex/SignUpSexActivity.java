package com.yeongjae.damoim.view.signup.sex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.data.source.signup.SignUpRepository;
import com.yeongjae.damoim.lib.fcm.FcmTokenService;
import com.yeongjae.damoim.util.LogUtils;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.intro.IntroActivity;
import com.yeongjae.damoim.view.signup.sex.presenter.SignUpSexContract;
import com.yeongjae.damoim.view.signup.sex.presenter.SignUpSexPresenter;

public class SignUpSexActivity extends AppCompatActivity implements SignUpSexContract.View {

    private Context context;
    private Button btnSex;
    private RadioGroup rgSex;
    private RadioButton rbtnMan;
    private RadioButton rbtnWoman;
    private SignUp signUp;
    private SignUpSexContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sex);

        init();
    }

    private void init() {

        context = getApplicationContext();
        rgSex = (RadioGroup) findViewById(R.id.rg_signup_sex);
        rbtnMan = (RadioButton) findViewById(R.id.rbtn_signup_man);
        rbtnWoman = (RadioButton) findViewById(R.id.rbtn_signup_woman);
        btnSex = (Button) findViewById(R.id.btn_signup_sex);
        presenter = new SignUpSexPresenter(this, SignUpRepository.getInstance());

        Intent intent = getIntent();
        signUp = (SignUp) intent.getSerializableExtra("SignUp");

        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbtn_signup_man) {
                    signUp.setSex(rbtnMan.getText().toString());
                }
                else if(checkedId == R.id.rbtn_signup_woman) {
                    signUp.setSex(rbtnWoman.getText().toString());
                }
            }
        });

        LogUtils.logInfo(FcmTokenService.getFcmToken());
        LogUtils.logInfo(signUp.getEmail() + "/" + signUp.getPassword() + "/" + signUp.getNickName() +
                "/" + signUp.getBirth() + "/");

        btnSex.setOnClickListener(v -> presenter.callSignUp(SignUp.builder()
                .email(signUp.getEmail())
                .password(signUp.getPassword())
                .nickName(signUp.getNickName())
                .birth(signUp.getBirth())
                .phone(signUp.getPhone())
                .sex(signUp.getSex())
                .location(null)
                .isVerified(false)
                .role("Role")
//                .fcmToken(FcmTokenService.getFcmToken())
                .build()
        ));
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startSignInActivity() {
        ToastUtils.showToast(context, "회원가입 성공");
        Intent intent = new Intent(context, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
