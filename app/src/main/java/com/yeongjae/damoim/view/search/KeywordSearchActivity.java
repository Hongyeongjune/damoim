package com.yeongjae.damoim.view.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeongjae.damoim.R;

public class KeywordSearchActivity extends AppCompatActivity {

    private Context context;
    private EditText etKeyword;
    private Button btnKeyword;
    private TextView tvCategory;
    private LinearLayout llCategory;

    private String searchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_search);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etKeyword = (EditText) findViewById(R.id.et_keyword_search);
        btnKeyword = (Button) findViewById(R.id.btn_keyword_search);
        tvCategory = (TextView) findViewById(R.id.tv_keyword_search);
        llCategory = (LinearLayout) findViewById(R.id.ll_keyword_search);

        Intent intent = getIntent();
        searchType = intent.getStringExtra("SearchKeywordCategory");

        if(searchType.equals("Board")) {
            tvCategory.setVisibility(View.INVISIBLE);
            llCategory.setVisibility(View.INVISIBLE);
        }
        else if(searchType.equals("Trade")) {
            tvCategory.setVisibility(View.VISIBLE);
            llCategory.setVisibility(View.VISIBLE);
        }
    }
}
