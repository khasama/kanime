package com.example.kanime.model;

public class Episode {
    public String idEP;
    public String Episode;

    public Episode(String idEP, String episode) {
        this.idEP = idEP;
        Episode = episode;
    }

    public String getIdEP() {
        return idEP;
    }

    public void setIdEP(String idEP) {
        this.idEP = idEP;
    }

    public String getEpisode() {
        return Episode;
    }

    public void setEpisode(String episode) {
        Episode = episode;
    }
}
