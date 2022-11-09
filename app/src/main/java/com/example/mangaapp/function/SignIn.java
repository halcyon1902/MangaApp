package com.example.mangaapp.function;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.md5.MD5;
import com.example.mangaapp.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    private final String messageAdmin = "Đăng nhập với quyền Admin thành công";
    private final String messageUser = "Đăng nhập thàng công";
    private List<TaiKhoan> list;
    private Button btnDangNhap;
    private TextInputEditText matkhau, tentaikhoan;
    private TextView dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_sign_in);
        list = new ArrayList<>();
        getTaiKhoan();
        init();
        btnDangNhap.setOnClickListener(v -> clickDangNhap());
        dangky.setOnClickListener(v -> clickDangKy());
    }

    private void clickDangKy() {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    private void clickDangNhap() {
        String name = Objects.requireNonNull(tentaikhoan.getText()).toString().trim();
        String pass = Objects.requireNonNull(matkhau.getText()).toString().trim();
        //mã hóa md5 cho mật khẩu
        byte[] md5Input = pass.getBytes();
        BigInteger md5Data = null;
        try {
            md5Data = new BigInteger(1, MD5.encryptMD5(md5Input));
        } catch (Exception e) {
            Toast.makeText(SignIn.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        assert md5Data != null;
        String passMD5 = md5Data.toString(16);
        if (passMD5.length() < 32) {
            passMD5 = 0 + passMD5;
        }
        //kiểm tra lấy được tài khoản
        if (list == null || list.isEmpty()) {
            return;
        }
        for (TaiKhoan taiKhoan : list) {
            if (name.equals(taiKhoan.getTaiKhoan()) && passMD5.equals(taiKhoan.getMatKhau()) && taiKhoan.isTrangThai()) {
                if (taiKhoan.isPhanQuyen()) {
                    Dialog(messageAdmin);
                } else {
                    Dialog(messageUser);
                }
                break;
            }
        }
    }

    //Tạo dialog thông báo
    private void Dialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        builder.setPositiveButton("OK", (dialog, which) -> {
            if (message.equals(messageAdmin)) {
                startActivity(new Intent(((Dialog) dialog).getContext(), com.example.mangaapp.display.TaiKhoanAdmin.class));
                finish();
            }
            if (message.equals(messageUser)) {
                startActivity(new Intent(((Dialog) dialog).getContext(), com.example.mangaapp.display.TaiKhoan.class));
                finish();
            }

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void getTaiKhoan() {
        ApiService.apiService.GetTaiKhoan().enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(@NonNull Call<List<TaiKhoan>> call, @NonNull Response<List<TaiKhoan>> response) {
                list = response.body();
                Log.e("Có bao nhiêu tài khoản",list.toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<TaiKhoan>> call, @NonNull Throwable t) {
                Toast.makeText(SignIn.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init() {
        tentaikhoan = findViewById(R.id.edt_TenTaiKhoan);
        matkhau = findViewById(R.id.edt_MatKhau);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        dangky = findViewById(R.id.et_DangKy);
    }
}