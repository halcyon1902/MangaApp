package com.example.mangaapp;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mangaapp.adapter.TruyenTranhAdapter;
import com.example.mangaapp.model.TruyenTranh;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
    GridView gvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init() {
        truyenTranhArrayList = new ArrayList<>();
        truyenTranhArrayList.add(new TruyenTranh("Danh Môn Chí Ái", "Chapter 359.2", "https://i.truyenvua.xyz/ebook/190x247/danh-mon-chi-ai_1567691484.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Attack On Titan", "Chapter 141", "https://i.truyenvua.xyz/ebook/190x247/attack-on-titan_1552228880.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Hakaijuu", "Chapter 83", "https://3.bp.blogspot.com/-0HvXfl4yywU/V5ZMEkAuHZI/AAAAAAAAA0w/RZ-6yrtx8-M/hakaijuu.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch Thiên Tà Thần", "Chapter 556", "https://i.truyenvua.xyz/ebook/190x247/nghich-thien-ta-than_1537240417.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Vua Sinh Tồn", "Chapter 76", "https://3.bp.blogspot.com/-SZp0rjc6udI/W27dOU44XoI/AAAAAAAAYoM/T9MzAUxGfsw9O4KpPELkd9DBFMrytnHjQCHMYCw/vua-sinh-ton"));
        truyenTranhArrayList.add(new TruyenTranh("One Piece", "Chapter 1060", "https://i.truyenvua.xyz/ebook/190x247/dao-hai-tac_1552224567.jpg?gf=hdfgdfg&mobile=2"));
        truyenTranhArrayList.add(new TruyenTranh("Onepunch Man", "Chapter 217", "https://i.truyenvua.xyz/ebook/190x247/onepunch-man_1552232163.jpg?gf=hdfgdfg&mobile=2"));
        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }

    private void anhXa() {
        gvDSTruyen = findViewById(R.id.gvDSTruyen);
    }

    private void setUp() {
        gvDSTruyen.setAdapter(adapter);
    }

    private void setClick() {

    }
}