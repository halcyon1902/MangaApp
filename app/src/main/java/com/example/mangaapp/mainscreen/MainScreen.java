package com.example.mangaapp.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.function.SignIn;
import com.example.mangaapp.model.Truyen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreen extends AppCompatActivity {
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    RecyclerView rcvDSTruyen;
    TruyenTranhAdapter truyenTranhAdapter;
    List<Truyen> mListTruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.main_screen);
        init();
        GetTatCaTruyen();
        initGridView();
        initBottomNavigation();
    }

    //Full màn hình
    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //khởi tạo bottom navigation
    private void initBottomNavigation() {
        bottomNavigationView.setSelected(false);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.account:
                    startActivity(new Intent(MainScreen.this, SignIn.class));
                    finish();
                case R.id.favorite:
                    break;
            }
            return false;
        });
    }

    //Khởi tạo
    public void init() {
        rcvDSTruyen = findViewById(R.id.rcv_DSTruyen);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
    }

    private void initGridView() {
        mListTruyen = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvDSTruyen.setLayoutManager(gridLayoutManager);
        rcvDSTruyen.setNestedScrollingEnabled(false);
        rcvDSTruyen.setFocusable(false);
    }

    private void GetTatCaTruyen() {
        ApiService.apiService.GetTatCaTruyen().enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                mListTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(MainScreen.this, mListTruyen);
                rcvDSTruyen.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {

            }
        });
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