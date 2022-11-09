package com.example.mangaapp.function;

import android.annotation.SuppressLint;
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
import com.example.mangaapp.adapter.TheLoaiAdminAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QL_TheLoai extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TheLoai> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_ql_the_loai);
        list = new ArrayList<>();
        init();
        initLinearLayout();
        getTheLoai();
    }

    private void initLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void init() {
        recyclerView = findViewById(R.id.rcv_admin_theloai);
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void getTheLoai() {
        ApiService.apiService.GetTatCaTheLoai().enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(@NonNull Call<List<TheLoai>> call, @NonNull Response<List<TheLoai>> response) {
                list = response.body();
                assert list != null;
                Log.e("Lấy được thể loại", list.toString());
                TheLoaiAdminAdapter adapter = new TheLoaiAdminAdapter(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(@NonNull Call<List<TheLoai>> call, @NonNull Throwable t) {

                Log.e("Lỗi", t.toString());
            }
        });
    }
}