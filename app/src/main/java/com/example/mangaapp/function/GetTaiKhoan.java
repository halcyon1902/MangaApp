package com.example.mangaapp.function;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TaiKhoanAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTaiKhoan extends AppCompatActivity {
    private final Context context = this;
    private RecyclerView recyclerView;
    private List<TaiKhoan> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_get_tai_khoan);
        recyclerView = findViewById(R.id.rcv_taikhoan);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        getTaiKhoan();
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
                assert list != null;
                Log.e("Lấy được tài khoản", list.toString());
                TaiKhoanAdapter taiKhoanAdapter = new TaiKhoanAdapter(list, context);
                recyclerView.setAdapter(taiKhoanAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<TaiKhoan>> call, @NonNull Throwable t) {
                Log.e("Bị lỗi gì ", t.toString());
            }
        });
    }
}