package com.yeongjae.damoim.view.boardcreate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.yeongjae.damoim.R;

public class BoardCreateActivity extends Activity {

    private Context context;
    private EditText etTitle;
    private EditText etContent;
    private Button btnBoardCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_board_create);
        init();
    }

    private void init() {
        context = getApplicationContext();
        etTitle = (EditText) findViewById(R.id.et_board_create_title);
        etContent = (EditText) findViewById(R.id.et_board_create_content);
        btnBoardCreate = (Button) findViewById(R.id.btn_board_create);

    }
}
