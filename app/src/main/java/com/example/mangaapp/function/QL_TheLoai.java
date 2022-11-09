package com.example.mangaapp.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_ql_the_loai);
        list = new ArrayList<>();
        init();
        initLinearLayout();
        getTheLoai();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.custom_dialog_admin_theloai, null);
                final TextView title = alertLayout.findViewById(R.id.tv_custom_dialog_admin_theloai_title);
                final EditText tentheloai = alertLayout.findViewById(R.id.et_custom_dialog_admin_theloai_tentheloai);
                final CheckBox cb1 = alertLayout.findViewById(R.id.cb_custom_dialog_admin_theloai_trangthai_true);
                final CheckBox cb2 = alertLayout.findViewById(R.id.cb_custom_dialog_admin_theloai_trangthai_false);
                title.setText("Thêm thể loại");
                AlertDialog.Builder builder = new AlertDialog.Builder(QL_TheLoai.this);
                builder.setView(alertLayout);
                builder.setCancelable(true);
                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ten=tentheloai.getText().toString().trim();
                        boolean trangthai = true;
                        if (cb1.isChecked()){
                            trangthai=true;
                        }
                        if (cb2.isChecked()){
                            trangthai=false;
                        }
                        TheLoai theLoai=new TheLoai(ten,trangthai);
                        ApiService.apiService.PostTheLoai(theLoai).enqueue(new Callback<TheLoai>() {
                            @Override
                            public void onResponse(Call<TheLoai> call, Response<TheLoai> response) {

                            }

                            @Override
                            public void onFailure(Call<TheLoai> call, Throwable t) {

                            }
                        });
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void initLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void init() {
        recyclerView = findViewById(R.id.rcv_admin_theloai);
        btn = findViewById(R.id.btn_admin_theloai_add);

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
                Log.e("Lỗi ở quản lý thể loại:", t.toString());
            }
        });
    }
}