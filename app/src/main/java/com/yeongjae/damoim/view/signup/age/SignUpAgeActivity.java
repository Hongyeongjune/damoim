package com.yeongjae.damoim.view.signup.age;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.signup.age.presenter.SignUpAgeContract;
import com.yeongjae.damoim.view.signup.age.presenter.SignUpAgePresenter;
import com.yeongjae.damoim.view.signup.sex.SignUpSexActivity;

public class SignUpAgeActivity extends AppCompatActivity implements SignUpAgeContract.View {

    private Context context;
    private EditText etAge;
    private Button btnAge;
    private SignUp signUp;
    private SignUpAgeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_age);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etAge = (EditText) findViewById(R.id.et_signup_age);
        btnAge = (Button) findViewById(R.id.btn_signup_age);
        presenter = new SignUpAgePresenter(this);

        Intent intent = getIntent();
        signUp = (SignUp) intent.getSerializableExtra("SignUp");


        btnAge.setOnClickListener(v -> presenter.startNextSignUp(etAge.getText().toString()));

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startNextSignUpActivity(String birth) {
        Intent intent = new Intent(context, SignUpSexActivity.class);
        signUp.setBirth(birth);
        intent.putExtra("SignUp" , signUp);
        startActivity(intent);
    }
}
