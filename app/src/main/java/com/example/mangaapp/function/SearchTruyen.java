package com.example.mangaapp.function;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.Truyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTruyen extends AppCompatActivity {


    private RecyclerView rcvDSTruyenTimKiem;
    private TruyenTranhAdapter truyenTranhAdapter;
    private List<Truyen> mListTruyen;
    private List<String> mlistTenTruyenTacGia;
    private EditText edtTimKiem;
    private ImageView imgSearch;
    private TextView tvTatCaTruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_truyen);


        Intent intent = getIntent();
        String tenTacGia = (String) intent.getSerializableExtra("clickTenTacGia");


        Intent intent1 = getIntent();
        String tenTheLoai = (String) intent.getSerializableExtra("clickTenTheLoai");

        init();
        if (tenTacGia != null)
            getTaCaTruyenTheoTacGia(tenTacGia);
        else if (tenTheLoai != null)
            getTaCaTruyenTheoTheLoai(tenTheLoai);
        else
            GetTatCaTruyen();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTruyenTheoTenOrTacGia(edtTimKiem.getText().toString().trim());
            }
        });
        tvTatCaTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTimKiem.setText("");
                GetTatCaTruyen();
            }
        });
    }


    private void GetTruyenTheoTenOrTacGia(String id) {

        String key = edtTimKiem.getText().toString().trim();
        if (key.isEmpty())
            GetTatCaTruyen();
        else {
            ApiService.apiService.GetTruyenTheoTenTruyenOrTacGia(id).enqueue(new Callback<List<Truyen>>() {
                @Override
                public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                    mListTruyen = response.body();
                    truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, mListTruyen);
                    rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
                }

                @Override
                public void onFailure(Call<List<Truyen>> call, Throwable t) {
                    Toast.makeText(SearchTruyen.this, "Get truyen theoTen hoac tac gia that bai", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void getTaCaTruyenTheoTheLoai(String tenTheLoai) {
        ApiService.apiService.GetTruyenTheoTheLoai(tenTheLoai).enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                mListTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, mListTruyen);
                rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(SearchTruyen.this, "Get truyen theo tac gia that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mlistTenTruyenTacGia = new ArrayList<>();
        mlistTenTruyenTacGia.add("naruto");
        mlistTenTruyenTacGia.add("one piece");
        mlistTenTruyenTacGia.add("one puch man");
        mlistTenTruyenTacGia.add("naruto 2");
        mlistTenTruyenTacGia.add("naruto 3");

        tvTatCaTruyen = findViewById(R.id.tv_tatcatruyen);
        edtTimKiem = findViewById(R.id.et_TimKiem);
        imgSearch = findViewById(R.id.img_search);

        rcvDSTruyenTimKiem = findViewById(R.id.rcv_DSTruyen_TimKiem);
        mListTruyen = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcvDSTruyenTimKiem.setLayoutManager(gridLayoutManager);
        rcvDSTruyenTimKiem.setNestedScrollingEnabled(false);
        rcvDSTruyenTimKiem.setFocusable(false);

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String keysearch) {
        ArrayList<Truyen> filterlist = new ArrayList<>();
        for (Truyen item : mListTruyen) {
            if (item.getTenTruyen().toLowerCase().contains(keysearch.toLowerCase())) {
                filterlist.add(item);
            }
        }
        truyenTranhAdapter.filterList(filterlist);
    }

    private void getTaCaTruyenTheoTacGia(String tenTacGia) {
        ApiService.apiService.GetTruyenTheoTacGia(tenTacGia).enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                mListTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, mListTruyen);
                rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(SearchTruyen.this, "Get truyen theo tac gia that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void GetTatCaTruyen() {
        ApiService.apiService.GetTatCaTruyen().enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                mListTruyen = response.body();
                truyenTranhAdapter = new TruyenTranhAdapter(SearchTruyen.this, mListTruyen);
                rcvDSTruyenTimKiem.setAdapter(truyenTranhAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {

            }
        });
    }


}