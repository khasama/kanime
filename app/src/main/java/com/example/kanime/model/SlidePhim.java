package com.example.kanime.model;

public class SlidePhim {
    public String id;
    public String Tenphim;
    public String Luotxem;
    public String Anhbia;
    public String Anhnen;

    public SlidePhim(String id, String tenphim, String luotxem, String anhbia, String anhnen) {
        this.id = id;
        Tenphim = tenphim;
        Luotxem = luotxem;
        Anhbia = anhbia;
        Anhnen = anhnen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenphim() {
        return Tenphim;
    }

    public void setTenphim(String tenphim) {
        Tenphim = tenphim;
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

    public String getAnhnen() {
        return Anhnen;
    }

    public void setAnhnen(String anhnen) {
        Anhnen = anhnen;
    }
}
