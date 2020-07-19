package com.yeongjae.damoim.view.signup.password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.signup.nickname.SignUpNickNameActivity;
import com.yeongjae.damoim.view.signup.password.presenter.SignUpPasswordContract;
import com.yeongjae.damoim.view.signup.password.presenter.SignUpPasswordPresenter;

public class SignUpPassWordActivity extends AppCompatActivity implements SignUpPasswordContract.View {

    private Context context;
    private EditText etPassword;
    private Button btnPassword;
    private SignUp signUp;
    private SignUpPasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pass_word);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etPassword = (EditText) findViewById(R.id.et_signup_password);
        btnPassword = (Button) findViewById(R.id.btn_signup_password);
        presenter = new SignUpPasswordPresenter(this);

        Intent intent = getIntent();
        signUp = (SignUp) intent.getSerializableExtra("SignUp");

        btnPassword.setOnClickListener(v -> presenter.passwordPatternCheck(etPassword.getText().toString()));

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startNextSignUpActivity(String password) {

        Intent intent = new Intent(context, SignUpNickNameActivity.class);
        signUp.setPassword(password);
        intent.putExtra("SignUp" , signUp);
        startActivity(intent);

    }
}
