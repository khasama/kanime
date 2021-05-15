package com.example.kanime.model;

public class Season {
    public String idPhim;
    public String Season;

    public Season(String id, String season) {
        this.idPhim = id;
        Season = season;
    }

    public String getId() {
        return idPhim;
    }

    public void setId(String id) {
        this.idPhim = id;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }
}
