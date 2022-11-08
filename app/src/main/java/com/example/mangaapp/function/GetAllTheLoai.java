package com.example.mangaapp.function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TheLoaiAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllTheLoai extends AppCompatActivity {

    private ImageView imgBack, imgSearch;

    private RecyclerView rcvTheloai;
    private TheLoaiAdapter theLoaiAdapter;
    private List<TheLoai> mlistTheLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_the_loai);
        init();
        getTatCaTheLoai();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetAllTheLoai.this, SearchTruyen.class));
            }
        });
    }


    private void init() {
        mlistTheLoai = new ArrayList<>();
        rcvTheloai = findViewById(R.id.rcv_all_theloai);
        imgSearch = findViewById(R.id.img_search);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rcvTheloai.setLayoutManager(gridLayoutManager);
        rcvTheloai.setNestedScrollingEnabled(false);
        rcvTheloai.setFocusable(false);
    }

    private void getTatCaTheLoai() {
        ApiService.apiService.GetTatCaTheLoai().enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                mlistTheLoai = response.body();
                Log.e("mlisttheloai: ", mlistTheLoai.toString());
                theLoaiAdapter = new TheLoaiAdapter(mlistTheLoai, GetAllTheLoai.this);
                rcvTheloai.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                Toast.makeText(GetAllTheLoai.this, "get tat ca the loai that bai", Toast.LENGTH_LONG).show();
            }
        });
    }
}