package com.example.mangaapp.model;

import java.io.Serializable;

public class TacGia implements Serializable {
    private String TenTacGia;
    private boolean TrangThai;

    public TacGia() {
    }

    public TacGia(String tenTacGia, boolean trangThai) {
        TenTacGia = tenTacGia;
        TrangThai = trangThai;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TacGia{" +
                "TenTacGia='" + TenTacGia + '\'' +
                ", TrangThai=" + TrangThai +
                '}';
    }
}
