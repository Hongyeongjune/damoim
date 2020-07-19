package com.yeongjae.damoim.view.signup.nickname;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.signup.nickname.presenter.SignUpNickNameContract;
import com.yeongjae.damoim.view.signup.nickname.presenter.SignUpNickNamePresenter;
import com.yeongjae.damoim.view.signup.phone.SignUpPhoneActivity;

public class SignUpNickNameActivity extends AppCompatActivity implements SignUpNickNameContract.View {

    private Context context;
    private EditText etNickName;
    private Button btnNickName;
    private SignUp signUp;
    private SignUpNickNameContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_nick_name);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etNickName = (EditText) findViewById(R.id.et_signup_nickname);
        btnNickName = (Button) findViewById(R.id.btn_signup_nickname);
        presenter = new SignUpNickNamePresenter(this);

        Intent intent = getIntent();
        signUp = (SignUp) intent.getSerializableExtra("SignUp");

        btnNickName.setOnClickListener(v -> presenter.startNextSignUp(etNickName.getText().toString()));

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startNextSignUpActivity(String nickName) {

        Intent intent = new Intent(context, SignUpPhoneActivity.class);
        signUp.setNickName(nickName);
        intent.putExtra("SignUp" , signUp);
        startActivity(intent);

    }
}
