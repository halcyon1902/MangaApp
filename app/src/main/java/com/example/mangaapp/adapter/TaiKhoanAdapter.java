package com.example.mangaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.function.PutTaiKhoan;
import com.example.mangaapp.model.TaiKhoan;

import java.util.List;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.TacGiaViewHolder> {
    private List<TaiKhoan> list;
    private Context context;

    public TaiKhoanAdapter(List<TaiKhoan> listTaiKhoan,Context context) {
        this.list = listTaiKhoan;
        this.context = context;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public TacGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taikhoan, parent, false);
        return new TacGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacGiaViewHolder holder, int position) {
        TaiKhoan taiKhoan = list.get(position);
        if (taiKhoan == null) {
            return;
        }
        holder.tv_TenTaiKhoan.setText(taiKhoan.getTaiKhoan());
        if (taiKhoan.isPhanQuyen()) {
            holder.tv_PhanQuyen.setText("Admin");
            holder.tv_PhanQuyen.setTextColor(Color.parseColor("#0000FF"));
        } else {
            holder.tv_PhanQuyen.setText("User");
            holder.tv_PhanQuyen.setTextColor(Color.parseColor("#FF0000"));
        }
        if (taiKhoan.isTrangThai()) {
            holder.tv_TrangThai.setText("Bình thường");
            holder.tv_TrangThai.setTextColor(Color.parseColor("#008000"));
        } else {
            holder.tv_TrangThai.setText("Bị cấm");
            holder.tv_TrangThai.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.cv_TaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PutTaiKhoan.class);
                intent.putExtra("clickTaiKhoan", taiKhoan);
                Log.e("Sau khi click vào tài khoản: ", "" + taiKhoan);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class TacGiaViewHolder extends RecyclerView.ViewHolder {

        private final CardView cv_TaiKhoan;
        private TextView tv_TenTaiKhoan, tv_PhanQuyen, tv_TrangThai;

        public TacGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TenTaiKhoan = itemView.findViewById(R.id.tv_tenTaiKhoan);
            tv_TrangThai = itemView.findViewById(R.id.tv_trangThai);
            tv_PhanQuyen = itemView.findViewById(R.id.tv_phanQuyen);
            cv_TaiKhoan = itemView.findViewById(R.id.cv_item_TaiKhoan);
        }
    }
}
