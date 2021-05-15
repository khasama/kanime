package com.example.kanime.model;

public class Server {
    public String idEp;
    public String Server;

    public Server(String idEp, String server) {
        this.idEp = idEp;
        Server = server;
    }

    public String getIdEp() {
        return idEp;
    }

    public void setIdEp(String idEp) {
        this.idEp = idEp;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }
}
