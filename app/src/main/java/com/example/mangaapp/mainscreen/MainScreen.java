package com.example.mangaapp.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.menu.MenuActivity;

public class MainScreen extends AppCompatActivity {
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        init();
        //Điều hướng thanh menu
        menu.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }

    //Khởi tạo
    public void init() {
        menu = findViewById(R.id.iv_menu);
    }
}