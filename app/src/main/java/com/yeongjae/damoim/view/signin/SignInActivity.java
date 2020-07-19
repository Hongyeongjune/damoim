package com.yeongjae.damoim.view.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.yeongjae.damoim.MainActivity;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignIn;
import com.yeongjae.damoim.data.source.signin.SignInRepository;
import com.yeongjae.damoim.lib.fcm.FcmTokenService;
import com.yeongjae.damoim.util.PrefUtils;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.main.DamoimActivity;
import com.yeongjae.damoim.view.signin.presenter.SignInContract;
import com.yeongjae.damoim.view.signin.presenter.SignInPresenter;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private Context context;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;
    private TextView tvSearch;
    private SignInContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    private void init() {

        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnSignIn = (Button) findViewById(R.id.btn_signin);
        tvSearch = (TextView) findViewById(R.id.tv_search_user_information);
        presenter = new SignInPresenter(this, SignInRepository.getInstance());


        btnSignIn.setOnClickListener(v -> presenter.callSignIn(
                etEmail.getText().toString(), etPassword.getText().toString()
        ));

        tvSearch.setOnClickListener(v -> startSearchInformationActivity());


    }

    @Override
    public void startDamoimActivity(SignIn signIn) {

        /**
         * 자동 로그인 구현하기
         */
        PrefUtils.writeAutoSignInTo(context);

        /**
         * 로그인 성공시 가는 Intent 구현하기
         */
        Intent intent = new Intent(context, DamoimActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startSearchInformationActivity() {

        /**
         * 아이디 비밀번호 찾기로 가는 Intent 구현하기
         */
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}
