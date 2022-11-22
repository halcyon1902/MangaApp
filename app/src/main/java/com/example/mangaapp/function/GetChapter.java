package com.example.mangaapp.function;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.BinhLuanAdapter;
import com.example.mangaapp.adapter.LinkAnhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.BinhLuan;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.PostBinhLuan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetChapter extends AppCompatActivity {

    private static final String MY_PREFERENCE_NAME = "USER_ID";
    private TextView tvChapter, tvTruyen;
    private RecyclerView rcvLinkAnh;
    private RecyclerView rcvBinhLuan;
    private List<String> mListLinkAnh;
    private List<BinhLuan> mListBinhLuan;
    private EditText edtNoiDung;
    private Button btnThemBL;
    private Chapter chapter;
    private String id;
    private BinhLuanAdapter binhLuanAdapter;
    private LinkAnhAdapter linkAnhAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_get_chapter);
        //lấy thông tin từ intent
        Intent intent = getIntent();
        chapter = (Chapter) intent.getSerializableExtra("clickchapter");
        Log.e("chapter la: ", chapter.toString());
        init();
        //
        SharedPreferences sh = getSharedPreferences(MY_PREFERENCE_NAME, MODE_PRIVATE);
        String s1 = sh.getString("value", "");
        id = s1;
        Log.e("test lay id", s1);
        //
        mListLinkAnh = new ArrayList<>();
        mListBinhLuan = new ArrayList<>();
        initLinearLayout();
        itemDecoration();
        if (chapter != null && chapter.isTrangThai()) {
            tvChapter.setText(chapter.getTenChapter());
            mListLinkAnh = new ArrayList<>(Arrays.asList(chapter.getLinkAnhs()));
            linkAnhAdapter = new LinkAnhAdapter(mListLinkAnh);
            rcvLinkAnh.setAdapter(linkAnhAdapter);
            mListBinhLuan = new ArrayList<>(Arrays.asList(chapter.getBinhLuans()));
            binhLuanAdapter = new BinhLuanAdapter(mListBinhLuan);
            rcvBinhLuan.setAdapter(binhLuanAdapter);
            binhLuanAdapter.notifyDataSetChanged();
        }

        btnThemBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickThemBinhLuan();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clickThemBinhLuan() {
        String noiDung = edtNoiDung.getText().toString();
        PostBinhLuan postBinhLuan = new PostBinhLuan(noiDung, true, chapter.get_id(), id);
        ApiService.apiService.ThemBinhLuan(postBinhLuan).enqueue(new Callback<PostBinhLuan>() {
            @Override
            public void onResponse(Call<PostBinhLuan> call, Response<PostBinhLuan> response) {
                Toast.makeText(getApplicationContext(), "Them Binh Luan Thanh cong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostBinhLuan> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Them Binh Luan That bai", Toast.LENGTH_LONG).show();
            }
        });
        binhLuanAdapter.notifyDataSetChanged();
        finish();
        startActivity(getIntent());
    }

    private void itemDecoration() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvLinkAnh.addItemDecoration(dividerItemDecoration);
        rcvBinhLuan.addItemDecoration(dividerItemDecoration1);
    }

    private void initLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvLinkAnh.setLayoutManager(linearLayoutManager);
        rcvLinkAnh.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcvBinhLuan.setLayoutManager(linearLayoutManager1);
        rcvBinhLuan.setHasFixedSize(true);
    }

    private void init() {

        rcvLinkAnh = findViewById(R.id.rcv_linkanh);
        rcvBinhLuan = findViewById(R.id.rcv_binhluan);
        tvChapter = findViewById(R.id.tv_chapter);
        btnThemBL = findViewById(R.id.btn_ThemBinhLuan);
        edtNoiDung = findViewById(R.id.edt_ThemBinhLuan);
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}