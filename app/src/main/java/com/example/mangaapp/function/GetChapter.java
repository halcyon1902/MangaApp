package com.example.mangaapp.function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaapp.ApiService;
import com.example.mangaapp.R;
import com.example.mangaapp.adapter.BinhLuanAdapter;
import com.example.mangaapp.adapter.LinkAnhAdapter;
import com.example.mangaapp.model.BinhLuan;
import com.example.mangaapp.model.Chapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetChapter extends AppCompatActivity {

    private TextView tvChapter, tvTruyen;
    private RecyclerView rcvLinkAnh;
    private RecyclerView rcvBinhLuan;
    private List<String> mListLinkAnh;
    private List<BinhLuan> mListBinhLuan;

    private LinkAnhAdapter linkAnhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_chapter);

        Intent intent = getIntent();
        Chapter chapter = (Chapter) intent.getSerializableExtra("clickchapter");

        rcvLinkAnh = findViewById(R.id.rcv_linkanh);
        rcvBinhLuan = findViewById(R.id.rcv_binhluan);
        tvChapter = findViewById(R.id.tv_chapter);
        mListLinkAnh = new ArrayList<>();
        mListBinhLuan = new ArrayList<>();

//        linkAnhAdapter = new LinkAnhAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvLinkAnh.setLayoutManager(linearLayoutManager);
//        rcvLinkAnh.setFocusable(false);
//        rcvLinkAnh.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcvBinhLuan.setLayoutManager(linearLayoutManager1);
//        rcvBinhLuan.setFocusable(false);
//        rcvBinhLuan.setNestedScrollingEnabled(false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvLinkAnh.addItemDecoration(dividerItemDecoration);
        rcvBinhLuan.addItemDecoration(dividerItemDecoration1);

        if (chapter!=null&&chapter.isTrangThai()){
            tvChapter.setText(chapter.getTenChapter());
            mListLinkAnh = new ArrayList<String>(Arrays.asList(chapter.getLinkAnh()));
            LinkAnhAdapter linkAnhAdapter = new LinkAnhAdapter(mListLinkAnh);
            rcvLinkAnh.setAdapter(linkAnhAdapter);

            mListBinhLuan = new ArrayList<BinhLuan>(Arrays.asList(chapter.getBinhLuans()));
            BinhLuanAdapter binhLuanAdapter = new BinhLuanAdapter(mListBinhLuan);
            rcvBinhLuan.setAdapter(binhLuanAdapter);

        }
   //     callApi();

    }

//    private void callApi(){
//        ApiService.apiService.GetChapter("6358d3ed7bfd0c7df215ff3e").enqueue(new Callback<Chapter>() {
//            @Override
//            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
//                Chapter chapter = response.body();
//                if (chapter!=null&&chapter.isTrangThai()){
//                    tvChapter.setText(chapter.getTenChapter());
//                    mListLinkAnh = new ArrayList<String>(Arrays.asList(chapter.getLinkAnh()));
//                    LinkAnhAdapter linkAnhAdapter = new LinkAnhAdapter(mListLinkAnh);
//                    rcvLinkAnh.setAdapter(linkAnhAdapter);
//
//                    mListBinhLuan = new ArrayList<BinhLuan>(Arrays.asList(chapter.getBinhLuans()));
//                    BinhLuanAdapter binhLuanAdapter = new BinhLuanAdapter(mListBinhLuan);
//                    rcvBinhLuan.setAdapter(binhLuanAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Chapter> call, Throwable t) {
//                Toast.makeText(GetChapter.this, "Get thất bại",Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}