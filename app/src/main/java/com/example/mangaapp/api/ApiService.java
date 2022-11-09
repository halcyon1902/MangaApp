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
import retrofit2.http.PUT;
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

    @PUT("TaiKhoan/{id}")
    Call<TaiKhoan> PutTaiKhoan(@Path("id") String taikhoanID, @Body TaiKhoan taiKhoan);

    //Chapter
    @GET("Chapter/{id}")
    Call<Chapter> GetChapter(@Path("id") String ChapterID);

    //Truyện
    @GET("Truyen")
    Call<List<Truyen>> GetTatCaTruyen();

    @GET("Truyen/{id}")
    Call<Truyen> GetTruyen(@Path("id") String truyenID);

    @GET("Truyen/SearchTruyenTheoTacGia/{id}")
    Call<List<Truyen>> GetTruyenTheoTacGia(@Path("id") String truyenID);

    @GET("Truyen/SearchTruyenTheoTheLoai/{id}")
    Call<List<Truyen>> GetTruyenTheoTheLoai(@Path("id") String truyenID);

    @GET("Truyen/SearchTruyen/{id}")
    Call<List<Truyen>> GetTruyenTheoTenTruyenOrTacGia(@Path("id") String truyenID);

    //Thể loại
    @GET("TheLoai/{id}")
    Call<TheLoai> GetTheLoai(@Path("id") String theLoaiID);

    @PUT("TheLoai/{id}")
    Call<TheLoai> PutTheLoai(@Path("id") String theLoaiID, @Body TheLoai theLoai);

    @GET("TheLoai")
    Call<List<TheLoai>> GetTatCaTheLoai();

    //Tác giả
    @GET("TacGia/{id}")
    Call<TacGia> GetTacGia(@Path("id") String tacGiaID);

}
