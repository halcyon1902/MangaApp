package com.example.mangaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.function.GetChapter;
import com.example.mangaapp.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private final List<Chapter> mListChapter;
    private Context context;

    public ChapterAdapter(List<Chapter> mListChapter, Context context) {
        this.mListChapter = mListChapter;
        this.context = context;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = mListChapter.get(position);
        if (chapter == null)
            return;
        holder.tvTenChapter.setText(chapter.getTenChapter());
        holder.tvNgayNhap.setText("Ngày đăng: "+chapter.getNgayNhap());
        holder.cvChapter.setOnClickListener(v -> {
            Intent intent = new Intent(context, GetChapter.class);
            intent.putExtra("clickchapter", chapter);
            Log.e("Sau khi click vào chapter: ", "" + chapter);
            context.startActivity(intent);
        });
    }

    public void release() {
        context = null;
    }

    @Override
    public int getItemCount() {
        if (mListChapter != null)
            return mListChapter.size();
        return 0;
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTenChapter;
        private final TextView tvNgayNhap;
        private final CardView cvChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenChapter = itemView.findViewById(R.id.tv_item_ten_chapter);
            tvNgayNhap = itemView.findViewById(R.id.tv_ngay_dang_chapter);
            cvChapter = itemView.findViewById(R.id.cv_chapter);
        }
    }
}
