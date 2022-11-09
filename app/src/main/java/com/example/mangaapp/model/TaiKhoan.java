package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Arrays;

public class TaiKhoan implements Serializable {
    private String _id;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private boolean PhanQuyen;
    private boolean TrangThai;
    private String[] BinhLuans;
    private String NgayTao;

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "_id='" + _id + '\'' +
                ", TaiKhoan='" + TaiKhoan + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", Email='" + Email + '\'' +
                ", PhanQuyen=" + PhanQuyen +
                ", TrangThai=" + TrangThai +
                ", BinhLuans=" + Arrays.toString(BinhLuans) +
                ", NgayTao='" + NgayTao + '\'' +
                '}';
    }

    public TaiKhoan(String _id, String taiKhoan, String matKhau, String email, boolean phanQuyen, boolean trangThai, String[] binhLuans, String ngayTao) {
        this._id = _id;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        Email = email;
        PhanQuyen = phanQuyen;
        TrangThai = trangThai;
        BinhLuans = binhLuans;
        NgayTao = ngayTao;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(boolean phanQuyen) {
        PhanQuyen = phanQuyen;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public String[] getBinhLuans() {
        return BinhLuans;
    }

    public void setBinhLuans(String[] binhLuans) {
        BinhLuans = binhLuans;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public TaiKhoan() {
    }

}
