package com.example.mangaapp.api;

import com.example.mangaapp.model.Chapter;
import com.example.mangaapp.model.TacGia;
import com.example.mangaapp.model.TaiKhoan;
import com.example.mangaapp.model.TheLoai;
import com.example.mangaapp.model.Truyen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.7:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //Tài khoản
    @GET("TaiKhoan")
    Call<List<TaiKhoan>> GetTaiKhoan();
    @POST("TaiKhoan")
    Call<TaiKhoan> PostTaiKhoan(@Body TaiKhoan taiKhoan);

    @GET("Chapter/{id}")
    Call<Chapter> GetChapter(@Path("id") String ChapterID);

    @GET("Truyen/{id}")
    Call<Truyen> GetTruyen(@Path("id") String truyenID);

    @GET("TheLoai/{id}")
    Call<TheLoai> GetTheLoai(@Path("id") String theLoaiID);

    @GET("TacGia/{id}")
    Call<TacGia> GetTacGia(@Path("id") String tacGiaID);

}
