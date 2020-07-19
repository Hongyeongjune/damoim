package com.yeongjae.damoim.view.searchinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.view.searchinformation.email.SearchEmailActivity;
import com.yeongjae.damoim.view.searchinformation.password.SearchPasswordActivity;
import com.yeongjae.damoim.view.searchinformation.presenter.SearchInformationContract;

public class SearchInformationActivity extends AppCompatActivity implements SearchInformationContract.View {

    private Context context;
    private Button btnStartEmail;
    private Button btnStartPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_information);

        init();
    }

    private void init() {

        context = getApplicationContext();
        btnStartEmail = (Button) findViewById(R.id.btn_start_search_email);
        btnStartPassword = (Button) findViewById(R.id.btn_start_search_password);

        btnStartEmail.setOnClickListener(v -> startSearchEmailActivity());
        btnStartPassword.setOnClickListener(v -> startSearchPasswordActivity());
    }

    /**
     * 아이디 찾기
     */
    @Override
    public void startSearchEmailActivity() {
        Intent intent = new Intent(context, SearchEmailActivity.class);
        startActivity(intent);
    }

    /**
     * 비밀번호 찾기
     */
    @Override
    public void startSearchPasswordActivity() {
        Intent intent = new Intent(context, SearchPasswordActivity.class);
        startActivity(intent);
    }
}
