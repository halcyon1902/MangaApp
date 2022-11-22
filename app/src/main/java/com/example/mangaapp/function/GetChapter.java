package com.example.mangaapp.function;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    private TextView tvChapter;
    private RecyclerView rcvLinkAnh;
    private RecyclerView rcvBinhLuan;
    private EditText edtNoiDung;
    private Button btnThemBL;
    private Chapter chapter;
    private String id;
    private BinhLuanAdapter binhLuanAdapter;
    private boolean isLoading;
    private boolean isLastPage;
    private int totalPage;
    private int currentPage = 1;

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
        //lấy thông tin từ sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE_NAME, MODE_PRIVATE);
        id = sharedPreferences.getString("value", "");
        //
        List<String> listLinkAnh;
        List<BinhLuan> listBinhLuan;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvLinkAnh.setLayoutManager(linearLayoutManager);
        rcvLinkAnh.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcvBinhLuan.setLayoutManager(linearLayoutManager1);
        rcvBinhLuan.setHasFixedSize(true);
        itemDecoration();
        if (chapter != null && chapter.isTrangThai()) {
            tvChapter.setText(chapter.getTenChapter());
            listLinkAnh = new ArrayList<>(Arrays.asList(chapter.getLinkAnhs()));
            LinkAnhAdapter linkAnhAdapter = new LinkAnhAdapter(listLinkAnh);
            rcvLinkAnh.setAdapter(linkAnhAdapter);
            listBinhLuan = new ArrayList<>(Arrays.asList(chapter.getBinhLuans()));
            binhLuanAdapter = new BinhLuanAdapter(listBinhLuan);
            rcvBinhLuan.setAdapter(binhLuanAdapter);
            binhLuanAdapter.notifyDataSetChanged();
        }

        rcvBinhLuan.addOnScrollListener(new PaginationScrollListener(linearLayoutManager1) {
            @Override
            public void loadItem() {
                isLoading=true;
                currentPage+=1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        btnThemBL.setOnClickListener(v -> clickThemBinhLuan());
    }

    private void loadNextPage() {
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clickThemBinhLuan() {
        String noiDung = edtNoiDung.getText().toString();
        PostBinhLuan postBinhLuan = new PostBinhLuan(noiDung, true, chapter.get_id(), id);
        ApiService.apiService.ThemBinhLuan(postBinhLuan).enqueue(new Callback<PostBinhLuan>() {
            @Override
            public void onResponse(@NonNull Call<PostBinhLuan> call, @NonNull Response<PostBinhLuan> response) {

            }

            @Override
            public void onFailure(@NonNull Call<PostBinhLuan> call, @NonNull Throwable t) {

            }
        });
        binhLuanAdapter.notifyDataSetChanged();
        Dialog();
    }

    private void itemDecoration() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration dividerItemDecoration1 = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvLinkAnh.addItemDecoration(dividerItemDecoration);
        rcvBinhLuan.addItemDecoration(dividerItemDecoration1);
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

    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bình luận thành công")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Thông báo");
        builder.setPositiveButton("OK", (dialog, which) -> {
            startActivity(getIntent());
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}