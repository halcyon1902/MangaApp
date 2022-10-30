package com.example.mangaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.mangaapp.R;
import com.example.mangaapp.model.TruyenTranh;

import java.util.ArrayList;
import java.util.List;

public class TruyenTranhAdapter extends ArrayAdapter<TruyenTranh> {
    private Context context;
    private ArrayList<TruyenTranh> arrayList;

    public TruyenTranhAdapter(@NonNull Context context, int resource, @NonNull List<TruyenTranh> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen, null);
        }
        if (arrayList.size() > 0) {
            TruyenTranh truyenTranh = this.arrayList.get(position);
            TextView tenTruyebTranh = convertView.findViewById(R.id.tv_TenTruyen);
            TextView tenChap = convertView.findViewById(R.id.tv_Chap);
            ImageView imgAnhBia = convertView.findViewById(R.id.imgv_AnhBia);

            tenTruyebTranh.setText(truyenTranh.getTenTruyen());
            tenChap.setText(truyenTranh.getTenChap());
            Glide.with(this.context).load(truyenTranh.getAnhBia()).into(imgAnhBia);
        }
        return convertView;
    }
}