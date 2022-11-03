package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.model.TaiKhoan;

public class PutTaiKhoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_put_tai_khoan);
        //lấy thông tin từ intent
        Intent intent = getIntent();
        TaiKhoan taiKhoan = (TaiKhoan) intent.getSerializableExtra("clickTaiKhoan");
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}