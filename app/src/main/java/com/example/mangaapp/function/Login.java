package com.example.mangaapp.function;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.ApiService;
import com.example.mangaapp.R;
import com.example.mangaapp.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private List<TaiKhoan> list;
    private Button btnDangNhap;
    private EditText matkhau, tendangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_login);
        list = new ArrayList<>();
        getTaiKhoan();
        init();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDangNhap();
            }
        });
    }

    private void clickDangNhap() {
        String name = tendangnhap.getText().toString().trim();
        String pass = matkhau.getText().toString().trim();
        if (list==null||list.isEmpty()){
            return;
        }
        //kiểm tra có tài khoản
        boolean coTaiKhoan=false;

        for (TaiKhoan taiKhoan : list){
            if (name.equals(taiKhoan.getTaiKhoan())&&pass.equals(taiKhoan.getMatKhau())){
                coTaiKhoan=true;
                break;
            }
        }
        if (coTaiKhoan){
            Toast.makeText(Login.this, "DangNhap", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(Login.this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void getTaiKhoan() {
        ApiService.apiService.GetTaiKHoan().enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                list = response.body();
                Log.e("List có bao nhiêu phần tử", list.size() + "");
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {
                Toast.makeText(Login.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init() {
        tendangnhap = findViewById(R.id.et_TenDangNhap);
        matkhau = findViewById(R.id.et_MatKhau);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
    }
}