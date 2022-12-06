package com.example.mangaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mangaapp.mainscreen.MainScreen;

public class ThongTinLienHe extends AppCompatActivity {
    private ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_lien_he);
        init();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongTinLienHe.this, MainScreen.class));
                finish();
            }
        });
    }
    public void init() {
        home =findViewById(R.id.img_ttlh_home);
    }
}