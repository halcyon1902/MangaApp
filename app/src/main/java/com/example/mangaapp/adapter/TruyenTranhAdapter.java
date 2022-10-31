package com.example.mangaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangaapp.R;
import com.example.mangaapp.function.GetChapter;
import com.example.mangaapp.function.GetTruyen;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.Truyen;
import com.example.mangaapp.model.TruyenTranh;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TruyenTranhAdapter extends RecyclerView.Adapter<TruyenTranhAdapter.TruyenTranhViewHolder> {


    private Context context;
    private List<Truyen> mListTruyenTranh;

    public TruyenTranhAdapter(Context context, List<Truyen> mListTruyenTranh) {
        this.context = context;
        this.mListTruyenTranh = mListTruyenTranh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TruyenTranhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyen, parent, false);
        return new TruyenTranhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenTranhViewHolder holder, int position) {
        Truyen truyen = mListTruyenTranh.get(position);
        if (truyen != null) {

            Chapter[] chapter = truyen.getChapters();

            holder.tenTruyebTranh.setText(truyen.getTenTruyen());
            holder.tongChap.setText(""+chapter.length);
            Glide.with(this.context).load(truyen.getAnhBia()).into(holder.imgAnhBia);

            holder.crvTruyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // chuyen list truyện click qua trang 1 truyen
                    Intent intent = new Intent(context, GetTruyen.class);
                    intent.putExtra("clickTruyen", truyen);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void release() {
        context = null;
    }

    @Override
    public int getItemCount() {
        if (mListTruyenTranh != null)
            return mListTruyenTranh.size();
        return 0;
    }

    public class TruyenTranhViewHolder extends RecyclerView.ViewHolder {
        private CardView crvTruyen;
        private TextView tenTruyebTranh;
        private TextView tongChap;
        private ImageView imgAnhBia;


        public TruyenTranhViewHolder(@NonNull View itemView) {
            super(itemView);
            crvTruyen= itemView.findViewById(R.id.crv_TruyenTranh);
            tongChap = itemView.findViewById(R.id.tv_tongChaper);
            tenTruyebTranh = itemView.findViewById(R.id.tv_TenTruyen);
            imgAnhBia = itemView.findViewById(R.id.imgv_AnhBia);
        }
    }
}

