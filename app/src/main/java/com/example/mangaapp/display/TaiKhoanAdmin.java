package com.example.mangaapp.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mangaapp.R;
import com.example.mangaapp.function.PostTruyen;
import com.example.mangaapp.function.QL_TacGia;
import com.example.mangaapp.function.QL_TaiKhoan;
import com.example.mangaapp.function.QL_TheLoai;

public class TaiKhoanAdmin extends AppCompatActivity {
    CardView cv_truyen, cv_taciga, cv_theloai, cv_taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_tai_khoan_admin);
        init();
        cv_truyen.setOnClickListener(v -> startActivity(new Intent(TaiKhoanAdmin.this, PostTruyen.class)));
        cv_taikhoan.setOnClickListener(v -> startActivity(new Intent(TaiKhoanAdmin.this, QL_TaiKhoan.class)));
        cv_taciga.setOnClickListener(v -> startActivity(new Intent(TaiKhoanAdmin.this, QL_TacGia.class)));
        cv_theloai.setOnClickListener(v -> startActivity(new Intent(TaiKhoanAdmin.this, QL_TheLoai.class)));
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