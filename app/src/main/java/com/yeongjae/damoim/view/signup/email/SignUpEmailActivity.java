package com.yeongjae.damoim.view.signup.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.data.source.signup.SignUpRepository;
import com.yeongjae.damoim.data.source.signup.overlap.EmailCheckRepository;
import com.yeongjae.damoim.util.LogUtils;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.signup.email.presenter.SignUpEmailContract;
import com.yeongjae.damoim.view.signup.email.presenter.SignUpEmailPresenter;
import com.yeongjae.damoim.view.signup.password.SignUpPassWordActivity;

public class SignUpEmailActivity extends AppCompatActivity implements SignUpEmailContract.View{

    private Context context;
    private EditText etEmail;
    private Button btnEmail;
    private SignUp signUp;
    private SignUpEmailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_signup_email);
        btnEmail = (Button) findViewById(R.id.btn_signup_email);

        signUp = SignUpRepository.getInstanceSignUp();

        presenter = new SignUpEmailPresenter(this, EmailCheckRepository.getInstance());

        btnEmail.setOnClickListener(v -> presenter.callEmailCheck(etEmail.getText().toString()));
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startNextSignUpActivity(String email) {

        Intent intent = new Intent(context, SignUpPassWordActivity.class);
        LogUtils.logInfo(email);
        signUp.setEmail(email);
        intent.putExtra("SignUp" , signUp);
        startActivity(intent);
    }
}
