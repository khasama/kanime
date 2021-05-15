package com.example.kanime.model;

public class PhimSearch {
    public String  ID;
    public String Tenphim;
    public String Tenkhac;
    public String Luotxem;
    public String Anhbia;

    public PhimSearch(String ID, String tenphim, String tenkhac, String luotxem, String anhbia) {
        this.ID = ID;
        Tenphim = tenphim;
        Tenkhac = tenkhac;
        Luotxem = luotxem;
        Anhbia = anhbia;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenphim() {
        return Tenphim;
    }

    public void setTenphim(String tenphim) {
        Tenphim = tenphim;
    }

    public String getTenkhac() {
        return Tenkhac;
    }

    public void setTenkhac(String tenkhac) {
        Tenkhac = tenkhac;
    }

    public String getLuotxem() {
        return Luotxem;
    }

    public void setLuotxem(String luotxem) {
        Luotxem = luotxem;
    }

    public String getAnhbia() {
        return Anhbia;
    }

    public void setAnhbia(String anhbia) {
        Anhbia = anhbia;
    }
}
