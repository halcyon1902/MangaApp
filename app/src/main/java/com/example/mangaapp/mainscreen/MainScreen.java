package com.example.mangaapp.mainscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.function.SignIn;
import com.example.mangaapp.model.TruyenTranh;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;
    //để test
    GridView gvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.drawer_layout);
        init();
        initNavigationDrawer();
        truyen();
        setUp();
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
        gvDSTruyen = findViewById(R.id.gvDSTruyen);
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

    //để test
    private void truyen() {
        truyenTranhArrayList = new ArrayList<>();
        truyenTranhArrayList.add(new TruyenTranh("Danh Môn Chí Ái", "Chapter 359.2", "https://i.truyenvua.xyz/ebook/190x247/danh-mon-chi-ai_1567691484.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Attack On Titan", "Chapter 141", "https://i.truyenvua.xyz/ebook/190x247/attack-on-titan_1552228880.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Hakaijuu", "Chapter 83", "https://3.bp.blogspot.com/-0HvXfl4yywU/V5ZMEkAuHZI/AAAAAAAAA0w/RZ-6yrtx8-M/hakaijuu.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch Thiên Tà Thần", "Chapter 556", "https://i.truyenvua.xyz/ebook/190x247/nghich-thien-ta-than_1537240417.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Vua Sinh Tồn", "Chapter 76", "https://3.bp.blogspot.com/-SZp0rjc6udI/W27dOU44XoI/AAAAAAAAYoM/T9MzAUxGfsw9O4KpPELkd9DBFMrytnHjQCHMYCw/vua-sinh-ton"));
        truyenTranhArrayList.add(new TruyenTranh("One Piece", "Chapter 1060", "https://i.truyenvua.xyz/ebook/190x247/dao-hai-tac_1552224567.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Onepunch Man", "Chapter 217", "https://i.truyenvua.xyz/ebook/190x247/onepunch-man_1552232163.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Danh Môn Chí Ái", "Chapter 359.2", "https://i.truyenvua.xyz/ebook/190x247/danh-mon-chi-ai_1567691484.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Attack On Titan", "Chapter 141", "https://i.truyenvua.xyz/ebook/190x247/attack-on-titan_1552228880.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Hakaijuu", "Chapter 83", "https://3.bp.blogspot.com/-0HvXfl4yywU/V5ZMEkAuHZI/AAAAAAAAA0w/RZ-6yrtx8-M/hakaijuu.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch Thiên Tà Thần", "Chapter 556", "https://i.truyenvua.xyz/ebook/190x247/nghich-thien-ta-than_1537240417.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Vua Sinh Tồn", "Chapter 76", "https://3.bp.blogspot.com/-SZp0rjc6udI/W27dOU44XoI/AAAAAAAAYoM/T9MzAUxGfsw9O4KpPELkd9DBFMrytnHjQCHMYCw/vua-sinh-ton"));
        truyenTranhArrayList.add(new TruyenTranh("One Piece", "Chapter 1060", "https://i.truyenvua.xyz/ebook/190x247/dao-hai-tac_1552224567.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Onepunch Man", "Chapter 217", "https://i.truyenvua.xyz/ebook/190x247/onepunch-man_1552232163.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Danh Môn Chí Ái", "Chapter 359.2", "https://i.truyenvua.xyz/ebook/190x247/danh-mon-chi-ai_1567691484.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Attack On Titan", "Chapter 141", "https://i.truyenvua.xyz/ebook/190x247/attack-on-titan_1552228880.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Hakaijuu", "Chapter 83", "https://3.bp.blogspot.com/-0HvXfl4yywU/V5ZMEkAuHZI/AAAAAAAAA0w/RZ-6yrtx8-M/hakaijuu.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch Thiên Tà Thần", "Chapter 556", "https://i.truyenvua.xyz/ebook/190x247/nghich-thien-ta-than_1537240417.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Vua Sinh Tồn", "Chapter 76", "https://3.bp.blogspot.com/-SZp0rjc6udI/W27dOU44XoI/AAAAAAAAYoM/T9MzAUxGfsw9O4KpPELkd9DBFMrytnHjQCHMYCw/vua-sinh-ton"));
        truyenTranhArrayList.add(new TruyenTranh("One Piece", "Chapter 1060", "https://i.truyenvua.xyz/ebook/190x247/dao-hai-tac_1552224567.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Onepunch Man", "Chapter 217", "https://i.truyenvua.xyz/ebook/190x247/onepunch-man_1552232163.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Danh Môn Chí Ái", "Chapter 359.2", "https://i.truyenvua.xyz/ebook/190x247/danh-mon-chi-ai_1567691484.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Attack On Titan", "Chapter 141", "https://i.truyenvua.xyz/ebook/190x247/attack-on-titan_1552228880.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Hakaijuu", "Chapter 83", "https://3.bp.blogspot.com/-0HvXfl4yywU/V5ZMEkAuHZI/AAAAAAAAA0w/RZ-6yrtx8-M/hakaijuu.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch Thiên Tà Thần", "Chapter 556", "https://i.truyenvua.xyz/ebook/190x247/nghich-thien-ta-than_1537240417.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Vua Sinh Tồn", "Chapter 76", "https://3.bp.blogspot.com/-SZp0rjc6udI/W27dOU44XoI/AAAAAAAAYoM/T9MzAUxGfsw9O4KpPELkd9DBFMrytnHjQCHMYCw/vua-sinh-ton"));
        truyenTranhArrayList.add(new TruyenTranh("One Piece", "Chapter 1060", "https://i.truyenvua.xyz/ebook/190x247/dao-hai-tac_1552224567.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Onepunch Man", "Chapter 217", "https://i.truyenvua.xyz/ebook/190x247/onepunch-man_1552232163.jpg?gf=hdfgdfg&mobile=2"));
        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }

    private void setUp() {
        gvDSTruyen.setAdapter(adapter);
    }//để test
}