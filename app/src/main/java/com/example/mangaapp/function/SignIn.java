package com.example.mangaapp.function;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.ThongTinLienHe;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.display.ThongTinTaiKhoan;
import com.example.mangaapp.mainscreen.MainScreen;
import com.example.mangaapp.model.TaiKhoan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    private static final String MY_PREFERENCE_PASS = "USER_PASS";
    private Button btnDangNhap;
    private TextInputEditText matkhau, tentaikhoan;
    private TextView dangky;
    private ImageView phone, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_sign_in);
        init();
        btnDangNhap.setOnClickListener(v -> clickDangNhap());
        dangky.setOnClickListener(v -> clickDangKy());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, MainScreen.class));
                finish();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ThongTinLienHe.class));
                finish();
            }
        });
    }

    private void clickDangKy() {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    private void clickDangNhap() {
        String name = Objects.requireNonNull(tentaikhoan.getText()).toString().trim();
        String pass = Objects.requireNonNull(matkhau.getText()).toString().trim();
        TaiKhoan taiKhoan = new TaiKhoan(name, pass);
        ApiService.apiService.Login(taiKhoan).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                TaiKhoan taiKhoan1 = response.body();
                if (taiKhoan1 != null) {
                    if (taiKhoan1.isTrangThai()) {
                        SharedPreferences sharedPref = getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value", taiKhoan1.get_id());
                        editor.apply();
                        //share password
                        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE_PASS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("value", pass);
                        edit.apply();
                        Dialog();
                    } else {
                        Dialog3();
                    }
                } else {
                    Dialog2();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                Log.e("Sign in:L???i", t.toString());
            }
        });
    }

    //T???o dialog th??ng b??o
    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("????ng nh???p th??ng c??ng")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Th??ng b??o");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = new Intent(((Dialog) dialog).getContext(), ThongTinTaiKhoan.class);
            startActivity(intent);
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Dialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("T??i kho???n hay m???t kh???u kh??ng ????ng!!!")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Th??ng b??o");
        builder.setPositiveButton("OK", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Dialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("T??i kho???n ???? b??? ????ng b??ng! Xin li??n h??? qu???n tr??? vi??n ")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Th??ng b??o");
        builder.setPositiveButton("OK", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void init() {
        home = findViewById(R.id.img_home);
        tentaikhoan = findViewById(R.id.edt_TenTaiKhoan);
        matkhau = findViewById(R.id.edt_MatKhau);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        dangky = findViewById(R.id.et_DangKy);
        phone = findViewById(R.id.img_phone);
    }
}