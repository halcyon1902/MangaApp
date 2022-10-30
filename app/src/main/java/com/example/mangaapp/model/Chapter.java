package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Chapter implements Serializable {

    private String TenChapter;
    private String NgayNhap;
    private boolean TrangThai;
    private String Truyen;
    private String[] LinkAnh;
    private BinhLuan[] BinhLuans;

    public Chapter() {
    }

    public Chapter(String tenChapter, String ngayNhap, boolean trangThai, String truyen, String[] linkAnh, BinhLuan[] binhLuans) {
        TenChapter = tenChapter;
        NgayNhap = ngayNhap;
        TrangThai = trangThai;
        Truyen = truyen;
        LinkAnh = linkAnh;
        BinhLuans = binhLuans;
    }

    public String getTenChapter() {
        return TenChapter;
    }

    public void setTenChapter(String tenChapter) {
        TenChapter = tenChapter;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public String getTruyen() {
        return Truyen;
    }

    public void setTruyen(String truyen) {
        Truyen = truyen;
    }

    public String[] getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String[] linkAnh) {
        LinkAnh = linkAnh;
    }

    public BinhLuan[] getBinhLuans() {
        return BinhLuans;
    }

    public void setBinhLuans(BinhLuan[] binhLuans) {
        BinhLuans = binhLuans;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "TenChapter='" + TenChapter + '\'' +
                ", NgayNhap='" + NgayNhap + '\'' +
                ", TrangThai=" + TrangThai +
                ", Truyen='" + Truyen + '\'' +
                ", LinkAnh=" + Arrays.toString(LinkAnh) +
                ", BinhLuans=" + Arrays.toString(BinhLuans) +
                '}';
    }
}
