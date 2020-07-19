package com.yeongjae.damoim.view.verificationcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.SignUp;
import com.yeongjae.damoim.data.VerificationCode;
import com.yeongjae.damoim.data.source.searchInformation.SearchInformationRepository;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.intro.IntroActivity;
import com.yeongjae.damoim.view.signup.age.SignUpAgeActivity;
import com.yeongjae.damoim.view.signup.sex.SignUpSexActivity;
import com.yeongjae.damoim.view.verificationcode.presenter.VerificationCodeContract;
import com.yeongjae.damoim.view.verificationcode.presenter.VerificationCodePresenter;

public class VerificationCodeActivity extends AppCompatActivity implements VerificationCodeContract.View{

    private Context context;
    private EditText etVerificationCode;
    private Button btnVerificationCode;
    private Button btnRequestRepeat;
    private TextView tvVerificationCode;
    private VerificationCode verificationCode;
    private VerificationCodeContract.Presenter presenter;
    private SignUp signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etVerificationCode = (EditText) findViewById(R.id.et_verification_code);
        btnVerificationCode = (Button) findViewById(R.id.btn_verification_code);
        btnRequestRepeat = (Button) findViewById(R.id.btn_repeat_verification_code);
        tvVerificationCode = (TextView) findViewById(R.id.tv_verification_code);
        presenter = new VerificationCodePresenter(this, SearchInformationRepository.getInstance());

        Intent intent = getIntent();
        verificationCode = (VerificationCode) intent.getSerializableExtra("VerificationCode");
        signUp = (SignUp) intent.getSerializableExtra("SignUp");

        tvVerificationCode.setText(verificationCode.getPhone() + " 에 전송된 6자리 인증번호를 입력해주세요.");

        btnVerificationCode.setOnClickListener(v -> {
                    if (verificationCode.getType().equals("Email")) {
                        presenter.callSearchEmail(
                                etVerificationCode.getText().toString(), verificationCode
                        );
                    }
                    else if(verificationCode.getType().equals("Password")) {
                        presenter.callSearchPassword(
                                etVerificationCode.getText().toString(), verificationCode
                        );
                    }
                    else if(verificationCode.getType().equals("SignUp")) {
                        startNextSignUpActivity();
                    }
                }
        );
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void showSuccessMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startNextSignUpActivity() {
        Intent intent = new Intent(context, SignUpAgeActivity.class);
        intent.putExtra("SignUp" , signUp);

        startActivity(intent);
    }

    @Override
    public void startIntroActivity() {
        Intent intent = new Intent(context, IntroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
