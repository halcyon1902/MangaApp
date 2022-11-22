package com.example.mangaapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.model.BinhLuan;

import java.text.SimpleDateFormat;
import java.util.List;

public class BinhLuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE = 1;
    private static final int TYPE_LOADING = 2;
    private final List<BinhLuan> listBinhLuan;
    private boolean isLoadng;

    @SuppressLint("NotifyDataSetChanged")
    public BinhLuanAdapter(List<BinhLuan> mListBinhLuan) {
        this.listBinhLuan = mListBinhLuan;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (listBinhLuan != null && position == listBinhLuan.size() - 1 && isLoadng) {
            return TYPE_LOADING;
        }
        return TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_binh_luan, parent, false);
            return new BinhLuanApdaterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE) {
            BinhLuan binhLuan = listBinhLuan.get(position);
            if (binhLuan == null) {
                return;
            }
            BinhLuanApdaterViewHolder binhLuanApdaterViewHolder = (BinhLuanApdaterViewHolder) holder;
            binhLuanApdaterViewHolder.tvTenTaiKhoan.setText(binhLuan.getTaiKhoan().getTaiKhoan());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            binhLuanApdaterViewHolder.tvNgayNhap.setText("Ngày đăng: " + simpleDateFormat.format(binhLuan.getNgayNhap()));
            binhLuanApdaterViewHolder.tvNoiDung.setText(binhLuan.getNoiDungBL().trim());
        }
    }

    @Override
    public int getItemCount() {
        if (listBinhLuan != null)
            return listBinhLuan.size();
        return 0;
    }

    public void addLoading() {
        isLoadng = true;
        listBinhLuan.add(new BinhLuan());
    }

    public void removeLoading() {
        isLoadng = false;
        int position = listBinhLuan.size() - 1;
        BinhLuan binhLuan = listBinhLuan.get(position);
        if (binhLuan != null) {
            listBinhLuan.remove(position);
            notifyItemRemoved(position);
        }
    }


    public static class BinhLuanApdaterViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTenTaiKhoan;
        private final TextView tvNgayNhap;
        private final TextView tvNoiDung;

        public BinhLuanApdaterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTaiKhoan = itemView.findViewById(R.id.tv_tenTaiKhoan);
            tvNgayNhap = itemView.findViewById(R.id.tv_ngayNhap);
            tvNoiDung = itemView.findViewById(R.id.tv_noi_dung);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
