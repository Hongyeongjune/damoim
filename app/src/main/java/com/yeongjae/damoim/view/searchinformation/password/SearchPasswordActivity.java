package com.yeongjae.damoim.view.searchinformation.password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.VerificationCode;
import com.yeongjae.damoim.data.source.searchInformation.SearchInformationRepository;
import com.yeongjae.damoim.data.source.signup.overlap.EmailCheckRepository;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.searchinformation.password.presenter.SearchPasswordContract;
import com.yeongjae.damoim.view.searchinformation.password.presenter.SearchPasswordPresenter;
import com.yeongjae.damoim.view.verificationcode.VerificationCodeActivity;

public class SearchPasswordActivity extends AppCompatActivity implements SearchPasswordContract.View {

    private Context context;
    private EditText etSearchPasswordEmail;
    private EditText etSearchPasswordPhone;
    private Button btnSearchPassword;
    private VerificationCode verificationCode;
    private SearchPasswordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_password);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etSearchPasswordEmail = (EditText) findViewById(R.id.et_search_password_email);
        etSearchPasswordPhone = (EditText) findViewById(R.id.et_search_password_phone);
        btnSearchPassword = (Button) findViewById(R.id.btn_search_password);

        verificationCode = SearchInformationRepository.getInstanceVerificationCode();
        presenter = new SearchPasswordPresenter(this, EmailCheckRepository.getInstance());

        btnSearchPassword.setOnClickListener(v -> presenter.startVerificationCode(
                etSearchPasswordPhone.getText().toString(), etSearchPasswordEmail.getText().toString())
        );
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startVerificationCodeActivity(String phone, String code, String email) {
        Intent intent = new Intent(context, VerificationCodeActivity.class);
        verificationCode.setPhone(phone);
        verificationCode.setVerificationCode(code);
        verificationCode.setType("Password");
        verificationCode.setEmail(email);
        intent.putExtra("VerificationCode", verificationCode);
        startActivity(intent);
    }
}
