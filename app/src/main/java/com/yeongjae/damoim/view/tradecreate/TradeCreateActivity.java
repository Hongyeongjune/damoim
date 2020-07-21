package com.yeongjae.damoim.view.tradecreate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.listener.OnLoadMoreListener;
import com.yeongjae.damoim.view.tradecreate.adapter.TradeCreateAdapter;

public class TradeCreateActivity extends Activity implements OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private TradeCreateAdapter tradeCreateAdapter;
    private Spinner spinner;
    private EditText etTitle;
    private EditText etContent;
    private Button btnTradeCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_trade_create);
        init();
    }

    private void init() {

        context = getApplicationContext();
        spinner = (Spinner) findViewById(R.id.spinner_trade_create);
        etTitle = (EditText) findViewById(R.id.et_trade_create_title);
        etContent = (EditText) findViewById(R.id.et_trade_create_content);
        btnTradeCreate = (Button) findViewById(R.id.btn_trade_create);
        recyclerView = (RecyclerView) findViewById(R.id.rv_trade_create_image);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        tradeCreateAdapter = new TradeCreateAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
                ));
        tradeCreateAdapter.setLinearLayoutManager(linearLayoutManager);
        tradeCreateAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(tradeCreateAdapter);
    }

    @Override
    public void onLoadMore() {

    }
}
