package com.example.kanime.model;

public class MenuLeft {
    public String TenMenu;
    public String AnhMenu;

    public MenuLeft(String tenMenu, String anhMenu) {
        TenMenu = tenMenu;
        AnhMenu = anhMenu;
    }

    public String getTenMenu() {
        return TenMenu;
    }

    public void setTenMenu(String tenMenu) {
        TenMenu = tenMenu;
    }

    public String getAnhMenu() {
        return AnhMenu;
    }

    public void setAnhMenu(String anhMenu) {
        AnhMenu = anhMenu;
    }
}
