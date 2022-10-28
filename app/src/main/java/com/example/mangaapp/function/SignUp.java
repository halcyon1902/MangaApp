package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private Button dangky, quaylai;
    private TextInputEditText matkhau, tentaikhoan, nhaplaimatkhau, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_sign_up);
        init();
        quaylai.setOnClickListener(v -> clickQuayLai());
        dangky.setOnClickListener(v -> clickDangKy());
    }

    private void clickDangKy() {
        String name = tentaikhoan.getText().toString().trim();
        String pass = matkhau.getText().toString().trim();
        String mail = email.getText().toString().trim();
        boolean isTrangThai = true;
        boolean isPhanQuyen = false;
        String[] binhluan = new String[0];
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TaiKhoan taiKhoan = new TaiKhoan(name, pass, mail, isPhanQuyen, isTrangThai, binhluan, currentDate);
        ApiService.apiService.PostTaiKhoan(taiKhoan).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                Log.e("Tài khoản đã tạo bao gồm", taiKhoan.toString());
            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                Toast.makeText(SignUp.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clickQuayLai() {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void init() {
        tentaikhoan = findViewById(R.id.edt_TenTaiKhoan);
        email = findViewById(R.id.edt_Email);
        matkhau = findViewById(R.id.edt_MatKhau);
        nhaplaimatkhau = findViewById(R.id.edt_NhapLaiMatKhau);
        quaylai = findViewById(R.id.btn_QuayLai);
        dangky = findViewById(R.id.btn_DangKy);
    }

}