package com.example.kanime.server;

public class Url {
    public static String domain = "http://kanime.ga/";
    public static String urlPhimmoi = domain + "api/anime/new.php";
    public static String urlSlide = domain + "api/anime/slide.php";
    public static String urlPhim = domain + "api/anime/anime.php?idPhim=";
    public static String urlSeason = domain + "api/anime/season.php?idPhim=";
    public static String urlMota = domain + "api/anime/loadmota.php?idPhim=";
    public static String urlInfoEp = domain + "api/anime/infoep.php?idEp=";
    public static String urlIframe = domain + "api/anime/iframe.php?idEp=";
    public static String urlSearch = domain + "api/anime/search.php?q=";
    public static String urlRegister = domain + "api/anime/register.php";
    public static String urlLogin = domain + "api/anime/login.php";
    public static String urlShowCmt = domain + "api/anime/show_cmt.php?idPhim=";
    public static String sendComment = domain + "api/anime/send_cmt.php";


    public static String GetAllEp(String idPhim, String idServer){
        String url = domain+"api/anime/episode.php?idPhim="+idPhim+"&idServer="+idServer;
        return url;
    }

    public static String GetServer(String idPhim, String episode){
        String url = domain+"api/anime/server.php?idPhim="+idPhim+"&Episode="+episode;
        return url;
    }

}
