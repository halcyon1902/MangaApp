package com.example.mangaapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaapp.R;
import com.example.mangaapp.api.ApiService;
import com.example.mangaapp.function.GetChapter;
import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.Truyen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            intent.putExtra("clickitem", position);
            Chapter updateChap = new Chapter((chapter.getLuotXem() + 1), true);
            ApiService.apiService.UpdateChapter(chapter.get_id(), updateChap).enqueue(new Callback<Chapter>() {
                @Override
                public void onResponse(@NonNull Call<Chapter> call, @NonNull Response<Chapter> response) {
                }

                @Override
                public void onFailure(@NonNull Call<Chapter> call, @NonNull Throwable t) {
                }
            });
            UpdateLuotXemTruyen(chapter.getTruyen());
            context.startActivity(intent);
        });
    }

    private void UpdateLuotXemTruyen(String truyen) {
        ApiService.apiService.GetTruyen(truyen).enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(@NonNull Call<Truyen> call, @NonNull Response<Truyen> response) {
                Truyen truyen1 = response.body();
                if (truyen1 != null) {
                    int luotxem = truyen1.getLuotXem();
                    int luotxemThang = truyen1.getLuotXemThang();
                    Date ngayXepHang = truyen1.getNgayXepHang();
                    int thang = ngayXepHang.getMonth() + 1;
                    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                    int currentMonth = calendar.get(Calendar.MONTH) + 1;
                    Date currentTime = Calendar.getInstance().getTime();
                    if (thang == currentMonth) {
                        luotxemThang += 1;
                    } else {
                        luotxemThang = 1;
                        ngayXepHang = currentTime;
                    }
                    luotxem += 1;
                    Truyen truyen2 = new Truyen(truyen1.isTrangThai(), truyen1.isTinhTrang(), luotxem, luotxemThang, ngayXepHang);
                    ApiService.apiService.UpdateTruyen(truyen, truyen2).enqueue(new Callback<Truyen>() {
                        @Override
                        public void onResponse(Call<Truyen> call, Response<Truyen> response) {

                        }

                        @Override
                        public void onFailure(Call<Truyen> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<Truyen> call, @NonNull Throwable t) {
            }
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