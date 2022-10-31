package com.example.mangaapp.mainscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.function.GetTruyen;
import com.example.mangaapp.function.SignIn;
import com.example.mangaapp.model.TaiKhoan;
import com.example.mangaapp.model.Truyen;
import com.example.mangaapp.model.TruyenTranh;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    //để test
    RecyclerView rcvDSTruyen;
    TruyenTranhAdapter truyenTranhAdapter;
    List<Truyen> mListTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.drawer_layout);
        init();
        GetTatCaTruyen();
        initNavigationDrawer();

    }

    //Full màn hình
    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Khởi tạo
    public void init() {
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        rcvDSTruyen = findViewById(R.id.rcv_DSTruyen);
        mListTruyen = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 2);
        rcvDSTruyen.setLayoutManager(gridLayoutManager);
        rcvDSTruyen.setNestedScrollingEnabled(false);
        rcvDSTruyen.setFocusable(false);
//        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        rcvDSTruyen.addItemDecoration(dividerItemDecoration1);

    }

    private void GetTatCaTruyen() {
        ApiService.apiService.GetTatCaTruyen().enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {

                mListTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(MainScreen.this,mListTruyen);
                rcvDSTruyen.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {

                Toast.makeText(MainScreen.this, "Get tat ca truyen that bai", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(this);
        //tạo menu mở drawer trên toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.goldenrod));
        navigationView.setItemIconTintList(null);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                startActivity(new Intent(this, SignIn.class));
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (truyenTranhAdapter != null)
            truyenTranhAdapter.release();
    }
}