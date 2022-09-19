package com.example.mangaapp.model;

public class TruyenTranh {
    private String tenTruyen, tenChap, anhBia;

    public TruyenTranh(String tenTruyen, String tenChap, String anhBia) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.anhBia = anhBia;
    }

    public TruyenTranh() {
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }
}
