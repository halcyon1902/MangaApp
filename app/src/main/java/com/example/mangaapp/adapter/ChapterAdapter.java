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

import java.text.SimpleDateFormat;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private final List<Chapter> mListChapter;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public ChapterAdapter(List<Chapter> mListChapter, Context context) {
        this.mListChapter = mListChapter;
        this.context = context;
        notifyDataSetChanged();
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
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvNgayNhap.setText("Ngày đăng: " + simpleDateFormat.format(chapter.getNgayNhap()));
        holder.cvChapter.setOnClickListener(v -> {
            Intent intent = new Intent(context, GetChapter.class);
            intent.putExtra("clickchapter", chapter);
//            Chapter updateChap = new Chapter((chapter.getLuotXem() + 1), true);
//            ApiService.apiService.UpdateChapter(chapter.get_id(), updateChap).enqueue(new Callback<Chapter>() {
//                @Override
//                public void onResponse(@NonNull Call<Chapter> call, @NonNull Response<Chapter> response) {
//                    Log.e("luot xem: ", "" + updateChap.getLuotXem());
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<Chapter> call, @NonNull Throwable t) {
//                    Log.e("luot xem: ", "fail");
//                }
//            });
//            UpdateLuotXemTruyen(chapter.getTruyen());
            context.startActivity(intent);
        });
    }

//    private void UpdateLuotXemTruyen(String truyen) {
//        ApiService.apiService.GetTruyen(truyen).enqueue(new Callback<Truyen>() {
//            @Override
//            public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
//                Truyen truyen1 = response.body();
//                if (truyen1 == null) {
//                    Log.e("chapter adapter: ", "truyện rỗng" );
//                } else {
//                    truyen1.setLuotXem(truyen1.getLuotXem() + 1);
//                    Truyen upTruyen = new Truyen(truyen1.getLuotXem(),truyen1.isTrangThai(),truyen1.isTinhTrang());
//                    ApiService.apiService.UpdateTruyen(truyen, upTruyen).enqueue(new Callback<Truyen>() {
//                        @Override
//                        public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
//                            Log.e("chapter adapter: ", "update truyện thành công" );
//                        }
//
//                        @Override
//                        public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
//                            Log.e("chapter adapter: ", "update truyện thất bại" );
//                        }
//                    });
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
//
//            }
//        });
//    }

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
