package com.yeongjae.damoim.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.main.presenter.DamoimContract;
import com.yeongjae.damoim.view.menu.MenuActivity;
import com.yeongjae.damoim.view.search.KeywordSearchActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DamoimActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DamoimContract.View {

    private Context context;
    private Button btnLocationVerification;
    private Button btnTownImage;
    private Button btnTownText;
    private Button btnBoardImage;
    private Button btnBoardText;
    private Button btnTradeImage;
    private Button btnTradeText;
    private Button btnPeopleImage;
    private Button btnPeopleText;
    private MenuItem searchItem;
    private MenuItem categoryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damoim);

        init();

    }

    private void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.app_bar_damoim_layout);
        layoutInflater.inflate(R.layout.content_damoim, coordinatorLayout, true);

        context = getApplicationContext();
        btnLocationVerification = (Button) findViewById(R.id.btn_location_verification);
        btnTownImage = (Button) findViewById(R.id.btn_town_image);
        btnTownText = (Button) findViewById(R.id.btn_town_text);
        btnBoardImage = (Button) findViewById(R.id.btn_board_image);
        btnBoardText = (Button) findViewById(R.id.btn_board_text);
        btnTradeImage = (Button) findViewById(R.id.btn_trade_image);
        btnTradeText = (Button) findViewById(R.id.btn_trade_text);
        btnPeopleImage = (Button) findViewById(R.id.btn_people_image);
        btnPeopleText = (Button) findViewById(R.id.btn_people_text);

        btnBoardImage.setOnClickListener(v -> startBoardActivity());
        btnBoardText.setOnClickListener(v -> startBoardActivity());
        btnPeopleImage.setOnClickListener(v -> startPeopleActivity());
        btnPeopleText.setOnClickListener(v -> startPeopleActivity());
        btnTradeImage.setOnClickListener(v -> startTradeActivity());
        btnTradeText.setOnClickListener(v -> startTradeActivity());
        btnTownImage.setOnClickListener(v -> startTownActivity());
        btnTownText.setOnClickListener(v -> startTownActivity());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.damoim, menu);
        searchItem = menu.findItem(R.id.action_search);
        categoryItem = menu.findItem(R.id.action_category);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                ToastUtils.showToast(context, "알림창으로 가는 액티비티 구현중...");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_information :
                ToastUtils.showToast(context,"내 정보 구현중...");
                break;
            case R.id.nav_town:
                Intent intentTown = new Intent(context, MenuActivity.class);
                intentTown.putExtra("Menu", "Town");
                intentTown.putExtra("SearchKeywordType", "Town");
                startActivity(intentTown);
                finish();
                break;
            case R.id.nav_board:
                Intent intentBoard = new Intent(context, MenuActivity.class);
                intentBoard.putExtra("Menu", "Board");
                intentBoard.putExtra("SearchKeywordType", "Board");
                startActivity(intentBoard);
                finish();
                break;
            case R.id.nav_trade:
                Intent intentTrade = new Intent(context, MenuActivity.class);
                intentTrade.putExtra("Menu", "Trade");
                intentTrade.putExtra("SearchKeywordType", "Trade");
                startActivity(intentTrade);
                finish();
                break;
            case R.id.nav_people:
                Intent intentPeople = new Intent(context, MenuActivity.class);
                intentPeople.putExtra("Menu", "People");
                intentPeople.putExtra("SearchKeywordType", "People");
                startActivity(intentPeople);
                finish();
                break;
            case R.id.nav_logout:
                ToastUtils.showToast(context,"로그아웃 구현중...");
                break;
        }
        return false;
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startLocationVerification() {
        ToastUtils.showToast(context, "동네인증구현중...");
    }

    @Override
    public void startTownActivity() {
        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("Menu", "Town");
        intent.putExtra("SearchKeywordType", "Town");
        startActivity(intent);
    }

    @Override
    public void startBoardActivity() {
        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("Menu", "Board");
        intent.putExtra("SearchKeywordType", "Board");
        startActivity(intent);
    }

    @Override
    public void startTradeActivity() {
        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("Menu", "Trade");
        intent.putExtra("SearchKeywordType", "Trade");
        startActivity(intent);
    }

    @Override
    public void startPeopleActivity() {
        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("Menu", "People");
        intent.putExtra("SearchKeywordType", "People");
        startActivity(intent);
    }
}
