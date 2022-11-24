package com.example.mangaapp.model;

import java.io.Serializable;
import java.util.Arrays;

public class Truyen implements Serializable {

    private String _id;
    private String TenTruyen;
    private String[] TheLoais;
    private boolean TrangThai;
    private boolean TinhTrang;
    private String GioiThieu;
    private String AnhBia;
    private int LuotThich;
    private int LuotXem;
    private int LuotTheoDoi;
    private String[] TacGias;
    private Chapter[] Chapters;

    public Truyen(int luotThich) {
        LuotThich = luotThich;
    }

    public Truyen() {
    }

    public Truyen(int luotXem, boolean trangThai, boolean tinhTrang) {
        LuotXem = luotXem;
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
    }

    public Truyen(String tenTruyen, String[] theLoais, boolean trangThai, boolean tinhTrang, String gioiThieu,
                  String anhBia, int luotThich, int luotXem, int luotTheoDoi, String[] tacGias, Chapter[]
                          chapters) {
        TenTruyen = tenTruyen;
        TheLoais = theLoais;
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        GioiThieu = gioiThieu;
        AnhBia = anhBia;
        LuotThich = luotThich;
        LuotXem = luotXem;
        LuotTheoDoi = luotTheoDoi;
        TacGias = tacGias;
        Chapters = chapters;
    }

    public Truyen(String _id, String tenTruyen, String[] theLoais, boolean trangThai, boolean tinhTrang,
                  String gioiThieu, String anhBia, int luotThich, int luotXem, int luotTheoDoi, String[] tacGias,
                  Chapter[] chapters) {
        this._id = _id;
        TenTruyen = tenTruyen;
        TheLoais = theLoais;
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        GioiThieu = gioiThieu;
        AnhBia = anhBia;
        LuotThich = luotThich;
        LuotXem = luotXem;
        LuotTheoDoi = luotTheoDoi;
        TacGias = tacGias;
        Chapters = chapters;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String[] getTheLoais() {
        return TheLoais;
    }

    public void setTheLoais(String[] theLoais) {
        TheLoais = theLoais;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getGioiThieu() {
        return GioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        GioiThieu = gioiThieu;
    }

    public String getAnhBia() {
        return AnhBia;
    }

    public void setAnhBia(String anhBia) {
        AnhBia = anhBia;
    }

    public int getLuotThich() {
        return LuotThich;
    }

    public void setLuotThich(int luotThich) {
        LuotThich = luotThich;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }

    public int getLuotTheoDoi() {
        return LuotTheoDoi;
    }

    public void setLuotTheoDoi(int luotTheoDoi) {
        LuotTheoDoi = luotTheoDoi;
    }

    public String[] getTacGias() {
        return TacGias;
    }

    public void setTacGias(String[] tacGias) {
        TacGias = tacGias;
    }

    public Chapter[] getChapters() {
        return Chapters;
    }

    public void setChapters(Chapter[] chapters) {
        Chapters = chapters;
    }

    @Override
    public String toString() {
        return "Truyen{" +
                "_id='" + _id + '\'' +
                ", TenTruyen='" + TenTruyen + '\'' +
                ", TheLoais=" + Arrays.toString(TheLoais) +
                ", TrangThai=" + TrangThai +
                ", TinhTrang=" + TinhTrang +
                ", GioiThieu='" + GioiThieu + '\'' +
                ", AnhBia='" + AnhBia + '\'' +
                ", LuotThich=" + LuotThich +
                ", LuotXem=" + LuotXem +
                ", LuotTheoDoi=" + LuotTheoDoi +
                ", TacGias=" + Arrays.toString(TacGias) +
                ", Chapters=" + Arrays.toString(Chapters) +
                '}';
    }
}
