package com.example.mangaapp.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mangaapp.R;
import com.example.mangaapp.function.GetTaiKhoan;
import com.example.mangaapp.function.PostTruyen;
import com.example.mangaapp.function.PutTaiKhoan;

public class TaiKhoanAdmin extends AppCompatActivity {
    CardView cv_truyen, cv_taciga, cv_theloai, cv_taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_tai_khoan_admin);
        init();
        cv_truyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanAdmin.this, PostTruyen.class));
                finish();
            }
        });
        cv_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaiKhoanAdmin.this, GetTaiKhoan.class));
                finish();
            }
        });
    }

    //Full màn hình
    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        cv_truyen = findViewById(R.id.cv_Truyen);
        cv_taikhoan = findViewById(R.id.cv_TaiKhoan);
        cv_taciga = findViewById(R.id.cv_TacGia);
        cv_theloai = findViewById(R.id.cv_TheLoai);
    }
}