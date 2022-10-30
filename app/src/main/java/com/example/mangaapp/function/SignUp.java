package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.md5.MD5;
import com.example.mangaapp.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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
        String name = Objects.requireNonNull(tentaikhoan.getText()).toString().trim();
        String pass = Objects.requireNonNull(matkhau.getText()).toString().trim();
        String mail = Objects.requireNonNull(email.getText()).toString().trim();
        boolean isTrangThai = true;
        boolean isPhanQuyen = false;
        String[] binhluan = new String[0];
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        //mã hóa md5 cho mật khẩu
        byte[] md5Input = pass.getBytes();
        BigInteger md5Data = null;
        try {
            md5Data = new BigInteger(1, MD5.encryptMD5(md5Input));
        } catch (Exception e) {
            Log.e("Lỗi md5:", e.toString());
        }
        assert md5Data != null;
        String passMD5 = md5Data.toString();
        Log.e("MD5 mã hóa", passMD5);
        if (Validation()) {
            TaiKhoan taiKhoan = new TaiKhoan(name, passMD5, mail, isPhanQuyen, isTrangThai, binhluan, currentDate);
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
    }

    private boolean Validation() {
        String name = Objects.requireNonNull(tentaikhoan.getText()).toString().trim();
        String pass = Objects.requireNonNull(matkhau.getText()).toString().trim();
        String repass = Objects.requireNonNull(nhaplaimatkhau.getText()).toString().trim();
        String mail = Objects.requireNonNull(email.getText()).toString().trim();
        if (TextUtils.isEmpty(mail)) {
            email.setError("Email không được để trống");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Email không đúng định dạng");
            email.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            tentaikhoan.setError("Tên tài khoản không được để trống");
            tentaikhoan.requestFocus();
            return false;
        }
        if (pass.length() < 6) {
            matkhau.setError("Mật khẩu không được ít hơn 6 kí tự !!!");
            matkhau.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pass)) {
            matkhau.setError("Mật khẩu không được để trống");
            matkhau.requestFocus();
            return false;
        }
        if (!repass.equals(pass)) {
            nhaplaimatkhau.setError("Mật khẩu không trùng khớp");
            nhaplaimatkhau.requestFocus();
            return false;
        }
        return true;
    }

    private void clickQuayLai() {
        startActivity(new Intent(this, SignIn.class));
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