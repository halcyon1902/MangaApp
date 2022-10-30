package com.example.mangaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {


    private List<TheLoai> mlistTheLoai;

    public TheLoaiAdapter(List<TheLoai> mlistTheLoai) {
        this.mlistTheLoai = mlistTheLoai;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_loai, parent, false);
        return new TheLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {

        TheLoai theLoai = mlistTheLoai.get(position);
        if(theLoai == null)
            return;
        holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        if (mlistTheLoai != null)
            return mlistTheLoai.size();
        return 0;
    }

    public static class TheLoaiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTenTheLoai;

        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenTheLoai = itemView.findViewById(R.id.tv_item_the_loai);
        }
    }
}
