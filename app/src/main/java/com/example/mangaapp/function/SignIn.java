package com.example.mangaapp.function;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.md5.MD5;
import com.example.mangaapp.model.TaiKhoan;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    private List<TaiKhoan> list;
    private Button btnDangNhap;
    private EditText matkhau, tendangnhap;
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
        String name = tendangnhap.getText().toString().trim();
        String pass = matkhau.getText().toString().trim();
        //mã hóa md5 cho mật khẩu
        byte[] md5Input = pass.getBytes();
        BigInteger md5Data = null;
        try {
            md5Data = new BigInteger(1, MD5.encryptMD5(md5Input));
        } catch (Exception e) {
            Toast.makeText(SignIn.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        assert md5Data != null;
        String passMD5 = md5Data.toString();
        Log.e("MD5 mã hóa", passMD5);
        //kiểm tra lấy được tài khoản
        if (list == null || list.isEmpty()) {
            return;
        }
        String messageAdmin = "Đăng nhập với quyền Admin thành công";
        String message = "Đăng nhập thàng công";
        for (TaiKhoan taiKhoan : list) {
            if (name.equals(taiKhoan.getTaiKhoan()) && passMD5.equals(taiKhoan.getMatKhau()) && taiKhoan.isTrangThai()) {
                if (taiKhoan.isPhanQuyen()) {
                    Dialog(messageAdmin);
                    startActivity(new Intent(this, SignIn.class));
                } else {
                    Dialog(message);
                }
                break;
            }
        }
    }

    //thông báo đăng nhập thành công
    private void Dialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        AlertDialog dialog = builder.create();
        dialog.show();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
            }
        }, 1000);
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void getTaiKhoan() {
        ApiService.apiService.GetTaiKHoan().enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(@NonNull Call<List<TaiKhoan>> call, @NonNull Response<List<TaiKhoan>> response) {
                list = response.body();
                assert list != null;
                //Log.e("List có bao nhiêu phần tử", list.size() + "");
            }

            @Override
            public void onFailure(@NonNull Call<List<TaiKhoan>> call, @NonNull Throwable t) {
                Toast.makeText(SignIn.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init() {
        tendangnhap = findViewById(R.id.edt_TenTaiKhoan);
        matkhau = findViewById(R.id.edt_MatKhau);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        dangky = findViewById(R.id.et_DangKy);
    }
}