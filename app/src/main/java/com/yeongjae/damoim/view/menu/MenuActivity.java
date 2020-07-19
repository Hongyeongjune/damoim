package com.yeongjae.damoim.view.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.util.ToastUtils;
import com.yeongjae.damoim.view.board.BoardFragment;
import com.yeongjae.damoim.view.main.DamoimActivity;
import com.yeongjae.damoim.view.people.PeopleFragment;
import com.yeongjae.damoim.view.search.KeywordSearchActivity;
import com.yeongjae.damoim.view.town.TownFragment;
import com.yeongjae.damoim.view.trade.TradeFragment;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private String menu_string;
    private String item_string;
    private String searchType;

    private LinearLayout llMenuHome;
    private LinearLayout llMenuChatting;
    private LinearLayout llMenuTown;
    private LinearLayout llMenuBoard;
    private LinearLayout llMenuTrade;
    private LinearLayout llMenuPeople;
    private DrawerLayout drawer;

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
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.app_bar_damoim_layout);
        layoutInflater.inflate(R.layout.content_menu, coordinatorLayout, true);

        context = getApplicationContext();
        llMenuHome = (LinearLayout) findViewById(R.id.ll_menu_home);
        llMenuChatting = (LinearLayout) findViewById(R.id.ll_menu_chatting);
        llMenuTown = (LinearLayout) findViewById(R.id.ll_menu_town);
        llMenuBoard = (LinearLayout) findViewById(R.id.ll_menu_board);
        llMenuTrade = (LinearLayout) findViewById(R.id.ll_menu_trade);
        llMenuPeople = (LinearLayout) findViewById(R.id.ll_menu_people);

        llMenuHome.setOnClickListener(view -> {
            Intent intent = new Intent(context, DamoimActivity.class);
            startActivity(intent);
            finish();
        });
        llMenuChatting.setOnClickListener(view -> ToastUtils.showToast(context, "채팅 구현중,,,"));
        llMenuTown.setOnClickListener(view -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ll_damoim_menu, TownFragment.createFragment())
                            .commit();
                    item_string = "Town";
                    invalidateOptionsMenu();
                }
        );
        llMenuBoard.setOnClickListener(view -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ll_damoim_menu, BoardFragment.createFragment())
                            .commit();

                    item_string = "Board";
                    searchType = "Board";
                    invalidateOptionsMenu();
                }
        );
        llMenuTrade.setOnClickListener(view -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ll_damoim_menu, TradeFragment.createFragment())
                            .commit();
                    item_string = "Trade";
                    searchType = "Trade";
                    invalidateOptionsMenu();
                }
        );
        llMenuPeople.setOnClickListener(view -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ll_damoim_menu, PeopleFragment.createFragment())
                            .commit();
                    item_string = "PeoPle";
                    invalidateOptionsMenu();
                }
        );


        Intent intent = getIntent();
        menu_string = intent.getStringExtra("Menu");
        item_string = intent.getStringExtra("SearchKeywordType");

        if(menu_string.equals("Board")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_damoim_menu, BoardFragment.createFragment())
                    .commit();
        }
        else if(menu_string.equals("People")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_damoim_menu, PeopleFragment.createFragment())
                    .commit();
        }
        else if(menu_string.equals("Trade")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_damoim_menu, TradeFragment.createFragment())
                    .commit();
        }
        else if(menu_string.equals("Town")) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ll_damoim_menu, TownFragment.createFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.damoim, menu);
        searchItem = menu.findItem(R.id.action_search);
        categoryItem = menu.findItem(R.id.action_category);

        if(item_string.equals("Board")) {
            searchItem.setVisible(true);
            categoryItem.setVisible(false);
        }
        else if(item_string.equals("People")) {
            searchItem.setVisible(false);
            categoryItem.setVisible(false);
        }
        else if(item_string.equals("Trade")) {
            searchItem.setVisible(true);
            categoryItem.setVisible(true);
        }
        else if(item_string.equals("Town")) {
            searchItem.setVisible(false);
            categoryItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                ToastUtils.showToast(context, "알림창으로 가는 액티비티 구현중...");
            }
            case R.id.action_search: {
                Intent intent = new Intent(context, KeywordSearchActivity.class);
                intent.putExtra("SearchKeywordCategory", searchType);
                startActivity(intent);
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
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_damoim_menu, TownFragment.createFragment())
                        .commit();
                item_string = "Town";
                invalidateOptionsMenu();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_board:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_damoim_menu, BoardFragment.createFragment())
                        .commit();
                item_string = "Board";
                searchType = "Board";
                invalidateOptionsMenu();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_trade:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_damoim_menu, TradeFragment.createFragment())
                        .commit();
                item_string = "Trade";
                searchType = "Trade";
                invalidateOptionsMenu();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_people:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_damoim_menu, PeopleFragment.createFragment())
                        .commit();
                item_string = "PeoPle";
                invalidateOptionsMenu();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_logout:
                ToastUtils.showToast(context,"로그아웃 구현중...");
                break;
        }
        return false;
    }
}
