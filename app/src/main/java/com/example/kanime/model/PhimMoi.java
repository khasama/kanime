package com.example.kanime.model;

public class PhimMoi {
    public int ID;
    public String Tenphim;
    public int Luotxem;
    public String Anhbia;
    public String Anhnen;

    public PhimMoi(int ID, String tenphim, int luotxem, String anhbia, String anhnen) {
        this.ID = ID;
        Tenphim = tenphim;
        Luotxem = luotxem;
        Anhbia = anhbia;
        Anhnen = anhnen;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenphim() {
        return Tenphim;
    }

    public void setTenphim(String tenphim) {
        Tenphim = tenphim;
    }

    public int getLuotxem() {
        return Luotxem;
    }

    public void setLuotxem(int luotxem) {
        Luotxem = luotxem;
    }

    public String getAnhbia() {
        return Anhbia;
    }

    public void setAnhbia(String anhbia) {
        Anhbia = anhbia;
    }

    public String getAnhnen() {
        return Anhnen;
    }

    public void setAnhnen(String anhnen) {
        Anhnen = anhnen;
    }

}
