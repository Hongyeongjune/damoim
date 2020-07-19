package com.yeongjae.damoim.view.searchinformation.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.VerificationCode;
import com.yeongjae.damoim.data.source.searchInformation.SearchInformationRepository;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.searchinformation.email.presenter.SearchEmailContract;
import com.yeongjae.damoim.view.searchinformation.email.presenter.SearchEmailPresenter;
import com.yeongjae.damoim.view.verificationcode.VerificationCodeActivity;

public class SearchEmailActivity extends AppCompatActivity implements SearchEmailContract.View{

    private Context context;
    private EditText etSearchEmail;
    private Button btnSearchEmail;
    private VerificationCode verificationCode;
    private SearchEmailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_email);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etSearchEmail = (EditText) findViewById(R.id.et_search_email);
        btnSearchEmail = (Button) findViewById(R.id.btn_search_email);

        verificationCode = SearchInformationRepository.getInstanceVerificationCode();
        presenter = new SearchEmailPresenter(this);

        btnSearchEmail.setOnClickListener(v -> presenter.startVerificationCode(etSearchEmail.getText().toString()));

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startVerificationCodeActivity(String phone, String code) {
        Intent intent = new Intent(context, VerificationCodeActivity.class);
        verificationCode.setPhone(phone);
        verificationCode.setVerificationCode(code);
        verificationCode.setType("Email");
        intent.putExtra("VerificationCode", verificationCode);
        startActivity(intent);
    }

}
