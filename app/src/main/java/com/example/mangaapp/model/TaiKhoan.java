package com.example.mangaapp.model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private String ID;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private boolean PhanQuyen;
    private boolean TrangThai;
    private String[] BinhLuans;
    private String NgayTao;

    public TaiKhoan() {
    }

    public TaiKhoan(String ID, String taiKhoan, String matKhau, String email, boolean phanQuyen, boolean trangThai, String[] binhLuans, String ngayTao) {
        this.ID = ID;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        Email = email;
        PhanQuyen = phanQuyen;
        TrangThai = trangThai;
        BinhLuans = binhLuans;
        NgayTao = ngayTao;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
}
