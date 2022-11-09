package com.example.mangaapp.function;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TaiKhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutTaiKhoan extends AppCompatActivity {
    private TextView tv_ten, tv_email;
    private CheckBox cb1, cb2, cb3, cb4;
    private RecyclerView rcvBinhLuan;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_put_tai_khoan);
        //lấy thông tin từ intent
        Intent intent = getIntent();
        TaiKhoan taiKhoan = (TaiKhoan) intent.getSerializableExtra("clickTaiKhoan");
        Log.e("Intent tài khoản", taiKhoan.toString());
        init();
        //
        tv_ten.setText(taiKhoan.getTaiKhoan());
        tv_email.setText(taiKhoan.getEmail());
        if (taiKhoan.isPhanQuyen()) {
            cb1.setChecked(true);
            cb2.setChecked(false);
        } else {
            cb2.setChecked(true);
            cb1.setChecked(false);
        }
        if (taiKhoan.isTrangThai()) {
            cb3.setChecked(true);
            cb4.setChecked(false);
        } else {
            cb4.setChecked(true);
            cb3.setChecked(false);
        }
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean temp = false;
                boolean temp1 = false;
                if (cb1.isChecked()) {
                    temp = true;
                    cb2.setChecked(false);
                } else {
                    cb1.setChecked(false);
                }
                if (cb3.isChecked()) {
                    temp1 = true;
                    cb4.setChecked(false);
                } else {
                    cb3.setChecked(false);
                }
                TaiKhoan taiKhoan1 = new TaiKhoan(taiKhoan.get_id(), taiKhoan.getTaiKhoan(), taiKhoan.getMatKhau(), taiKhoan.getEmail(), temp, temp1, taiKhoan.getBinhLuans(), taiKhoan.getNgayTao());
                ApiService.apiService.PutTaiKhoan(taiKhoan.get_id(), taiKhoan1).enqueue(new Callback<TaiKhoan>() {
                    @Override
                    public void onResponse(@NonNull Call<TaiKhoan> call, @NonNull Response<TaiKhoan> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<TaiKhoan> call, @NonNull Throwable t) {

                    }
                });
                startActivity(new Intent(PutTaiKhoan.this, GetTaiKhoan.class));
                finish();
            }
        });
        initLinearLayout();
        itemDecoration();
    }

    private void itemDecoration() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvBinhLuan.addItemDecoration(dividerItemDecoration);
    }

    private void initLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvBinhLuan.setLayoutManager(linearLayoutManager);
        rcvBinhLuan.setHasFixedSize(true);
    }

    private void init() {
        tv_ten = findViewById(R.id.tv_tenTaiKhoan);
        tv_email = findViewById(R.id.tv_email);
        cb1 = findViewById(R.id.cb_PhanQuyen_Admin);
        cb2 = findViewById(R.id.cb_PhanQuyen_User);
        cb3 = findViewById(R.id.cb_TrangThai_Admin);
        cb4 = findViewById(R.id.cb_TrangThai_User);
        rcvBinhLuan = findViewById(R.id.rcv_binhluan);
        btn = findViewById(R.id.btn);
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}