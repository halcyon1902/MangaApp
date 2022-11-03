package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.BinhLuanAdapter;
import com.example.mangaapp.adapter.LinkAnhAdapter;
import com.example.mangaapp.model.BinhLuan;
import com.example.mangaapp.model.Chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetChapter extends AppCompatActivity {

    private TextView tvChapter, tvTruyen;
    private RecyclerView rcvLinkAnh;
    private RecyclerView rcvBinhLuan;
    private List<String> mListLinkAnh;
    private List<BinhLuan> mListBinhLuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_get_chapter);
        //lấy thông tin từ intent
        Intent intent = getIntent();
        Chapter chapter = (Chapter) intent.getSerializableExtra("clickchapter");
        init();
        mListLinkAnh = new ArrayList<>();
        mListBinhLuan = new ArrayList<>();
        initLinearLayout();
        itemDecoration();
        if (chapter != null && chapter.isTrangThai()) {
            tvChapter.setText(chapter.getTenChapter());
            mListLinkAnh = new ArrayList<>(Arrays.asList(chapter.getLinkAnhs()));
            LinkAnhAdapter linkAnhAdapter = new LinkAnhAdapter(mListLinkAnh);
            rcvLinkAnh.setAdapter(linkAnhAdapter);
            mListBinhLuan = new ArrayList<>(Arrays.asList(chapter.getBinhLuans()));
            BinhLuanAdapter binhLuanAdapter = new BinhLuanAdapter(mListBinhLuan);
            rcvBinhLuan.setAdapter(binhLuanAdapter);
        }
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
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}