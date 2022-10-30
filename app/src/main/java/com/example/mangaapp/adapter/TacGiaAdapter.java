package com.example.mangaapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.model.TacGia;
import com.example.mangaapp.model.TheLoai;

import java.util.List;

public class TacGiaAdapter extends RecyclerView.Adapter<TacGiaAdapter.TacGiaViewHolder>{

    private List<TacGia> mlistTacGia;

    public TacGiaAdapter(List<TacGia> mlistTacGia) {
        this.mlistTacGia = mlistTacGia;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public TacGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_loai,parent, false);
        return new TacGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacGiaViewHolder holder, int position) {
        TacGia tacGia = mlistTacGia.get(position);
        if(tacGia == null)
            return;
        holder.tvTenTacGia.setText(tacGia.getTenTacGia());
    }

    @Override
    public int getItemCount() {
        if (mlistTacGia != null)
            return mlistTacGia.size();
        return 0;
    }

    public class TacGiaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTenTacGia;
        public TacGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTacGia = itemView.findViewById(R.id.tv_item_the_loai);
        }
    }
}
