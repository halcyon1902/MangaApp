package com.example.mangaapp.display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.mainscreen.MainScreen;
import com.example.mangaapp.model.TaiKhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinTaiKhoan extends AppCompatActivity {
    private TextView tvHoVaTen;
    private TextView tvHoVaTen1;
    private TextView tvEmail;
    private TextView tvEmail1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        tvHoVaTen = findViewById(R.id.ttcn_tv_HoVaTen);
        tvHoVaTen1 = findViewById(R.id.ttcn_tv_HoVaTen1);
        tvEmail = findViewById(R.id.ttcn_tv_Email);
        tvEmail1 = findViewById(R.id.ttcn_tv_Email1);

        getThongTinTaiKhoan("637ba16b9f6bb108c64f7860");
    }

    private void getThongTinTaiKhoan(String s) {
        ApiService.apiService.thongtintaikhoan(s).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                Toast.makeText(ThongTinTaiKhoan.this, "Call Api Success", Toast.LENGTH_SHORT).show();

                TaiKhoan taiKhoan = response.body();
                if  (taiKhoan != null && taiKhoan.isTrangThai()){
                    tvHoVaTen.setText(taiKhoan.getHoTen());
                    tvHoVaTen1.setText(taiKhoan.getHoTen());
                    tvEmail.setText(taiKhoan.getEmail());
                    tvEmail1.setText(taiKhoan.getEmail());
                    Log.e("Họ tên: ", ""+ taiKhoan.getHoTen());
                }
            }

            @Override
            public void onFailure(Call<TaiKhoan> call, Throwable t) {
                Toast.makeText(ThongTinTaiKhoan.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}