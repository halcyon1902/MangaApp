package com.example.mangaapp.display;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.mainscreen.MainScreen;
import com.example.mangaapp.model.TaiKhoan;
import com.example.mangaapp.model.Truyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorite extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "USER_ID";
    String id = null;
    private RecyclerView recyclerView;
    private TruyenTranhAdapter truyenTranhAdapter;
    private List<Truyen> listTruyen;
    private List<Truyen> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_favorite);
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE_NAME, MODE_PRIVATE);
        id = sharedPreferences.getString("value", "");
        check(id);
        init();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Favorite.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
    }

    private void check(String id) {
        ApiService.apiService.thongtintaikhoan(id).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                TaiKhoan taiKhoan = response.body();
                if (taiKhoan != null) {
                    if (!taiKhoan.isTrangThai()) {
                        Dialog3();
                    } else {
                        GetTruyen();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                Log.e("Th??ng tin t??i kho???n: ", t.toString());
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.rcv_yeuthich);
    }

    private void GetTruyen() {
        ApiService.apiService.thongtintaikhoan(id).enqueue(new Callback<TaiKhoan>() {
            @Override
            public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {
                TaiKhoan taiKhoan = response.body();
                List YeuThich = taiKhoan.getYeuThich();
                ApiService.apiService.GetTatCaTruyen().enqueue(new Callback<List<Truyen>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Truyen>> call, @NonNull Response<List<Truyen>> response) {
                        listTruyen = response.body();
                        if (listTruyen != null) {
                            for (int i = 0; i < listTruyen.size(); i++) {
                                for (int j = 0; j < YeuThich.size(); j++) {
                                    if (listTruyen.get(i).get_id().equals(YeuThich.get(j))) {
                                        list.add(listTruyen.get(i));
                                    }
                                }
                            }
                            if (list.size() == 0) {
                                Dialog();
                            }
                            truyenTranhAdapter = new TruyenTranhAdapter(Favorite.this, list);
                            recyclerView.setAdapter(truyenTranhAdapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Truyen>> call, @NonNull Throwable t) {
                        Log.e("L???i: ", t.toString());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {
                Log.e("L???i: ", t.toString());
            }
        });
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ch??a c?? truy???n y??u th??ch! Quay v??? trang ch???")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Th??ng b??o");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = new Intent(((Dialog) dialog).getContext(), MainScreen.class);
            startActivity(intent);
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void Dialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("T??i kho???n ???? b??? ????ng b??ng! Xin li??n h??? qu???n tr??? vi??n ")
                .setIcon(R.drawable.ic_notifications_red)
                .setTitle("Th??ng b??o");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Intent intent = new Intent(((Dialog) dialog).getContext(), MainScreen.class);
            startActivity(intent);
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}