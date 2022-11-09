package com.example.mangaapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheLoaiAdminAdapter extends RecyclerView.Adapter<TheLoaiAdminAdapter.TheLoaiViewHolder> {


    private final List<TheLoai> list;

    @SuppressLint("NotifyDataSetChanged")
    public TheLoaiAdminAdapter(List<TheLoai> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_theloai, parent, false);
        return new TheLoaiViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {

        TheLoai theLoai = list.get(position);
        if (theLoai == null)
            return;
        holder.tv_ten.setText(theLoai.getTenTheLoai());
        if (theLoai.isTrangThai()) {
            holder.tv_trangthai.setText("Bình thường");
            holder.tv_trangthai.setTextColor(Color.parseColor("#008000"));
        } else {
            holder.tv_trangthai.setText("Bị ẩn");
            holder.tv_trangthai.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.btn_edit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
            View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_dialog_admin_theloai_adapter, null);
            //init
            TextView tv_customdialog_tentheloai;
            CheckBox cb1, cb2;
            tv_customdialog_tentheloai = dialogView.findViewById(R.id.et_custom_dialog_admin_theloai_tentheloai);
            cb1 = dialogView.findViewById(R.id.cb_custom_dialog_admin_theloai_trangthai_true);
            cb2 = dialogView.findViewById(R.id.cb_custom_dialog_admin_theloai_trangthai_false);
            //
            if (theLoai.isTrangThai()) {
                cb1.setChecked(true);
                cb2.setChecked(false);
            } else {
                cb1.setChecked(false);
                cb2.setChecked(true);
            }
            tv_customdialog_tentheloai.setText(theLoai.getTenTheLoai());
            builder.setView(dialogView);
            builder.setPositiveButton("Cập nhật", (dialog, which) -> {
                String ten = tv_customdialog_tentheloai.getText().toString().trim();
                String id = theLoai.get_id();
                boolean trangthai = cb1.isChecked();
                if (cb2.isChecked()) {
                    trangthai = false;
                }
                TheLoai theLoai1 = new TheLoai(id, ten, trangthai);
                ApiService.apiService.PutTheLoai(id, theLoai1).enqueue(new Callback<TheLoai>() {
                    @Override
                    public void onResponse(@NonNull Call<TheLoai> call, @NonNull Response<TheLoai> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<TheLoai> call, @NonNull Throwable t) {
                        Log.e("Có lỗi ở adapter admin thể loại:",t.toString());
                    }
                });
            });
            builder.setCancelable(true);
            builder.show();

        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public static class TheLoaiViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_trangthai;
        private final TextView tv_ten;
        private final ImageView btn_edit;

        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_edit = itemView.findViewById(R.id.btn_admin_theloai_edit);
            tv_ten = itemView.findViewById(R.id.tv_item_admin_theloai_tentheloai);
            tv_trangthai = itemView.findViewById(R.id.tv_item_admin_theloai_trangthai);

        }
    }
}
