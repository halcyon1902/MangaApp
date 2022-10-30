package com.example.mangaapp.model;

import java.io.Serializable;

public class TheLoai implements Serializable {
    private String TenTheLoai;
    private boolean TrangThai;

    public TheLoai() {
    }

    public TheLoai(String tenTheLoai, boolean trangThai) {
        TenTheLoai = tenTheLoai;
        TrangThai = trangThai;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public String toString() {
        return "TheLoai{" +
                "TenTheLoai='" + TenTheLoai + '\'' +
                ", TrangThai=" + TrangThai +
                '}';
    }
}
