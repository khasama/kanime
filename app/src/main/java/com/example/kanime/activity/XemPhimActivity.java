package com.example.kanime.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.adapter.EpisodeAdapter;
import com.example.kanime.adapter.MenuLeftAdapter;
import com.example.kanime.adapter.SeasonAdapter;
import com.example.kanime.adapter.ServerAdapter;
import com.example.kanime.model.Episode;
import com.example.kanime.model.MenuLeft;
import com.example.kanime.model.Server;
import com.example.kanime.server.Url;
import com.example.kanime.ultil.CheckConnect;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class XemPhimActivity extends AppCompatActivity {
    NavigationView nvMenu;
    ListView lvMenu;
    DrawerLayout drlayout;
    ImageButton btnopenmenu, btnsearch;
    ArrayList<MenuLeft> arrayMenulef;
    MenuLeftAdapter menuLeftAdapter;
    TextView tvTenPhim, tvTap, tvNameServer;
    RecyclerView rvListEp, rvListServer;
    WebView wvIframe;
    ArrayList<Episode> arrayEpisode;
    EpisodeAdapter episodeAdapter;
    ArrayList<Server> arrayServer;
    ServerAdapter serverAdapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_phim);
        anhXa();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            GetMenuLeft();
            GetInfoEpisode();
            initPreferences();
            MenuClick();
        }else{
            CheckConnect.Thongbao(getApplicationContext(), "Kiểm tra lại kết nối!!!");
            finish();
        }
    }

    private void GetInfoEpisode() {
        Bundle bd = getIntent().getExtras();
        if( bd != null){
            String idEp = bd.getString("idEp");
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlInfoEp + idEp, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if(response != null){
                        String idPhim = "";
                        String TenPhim = "";
                        String Tap = "";
                        String NameServer = "";
                        String MainServer = "";
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                idPhim = jsonObject.getString("id");
                                TenPhim = jsonObject.getString("name");
                                Tap = jsonObject.getString("episode");
                                NameServer = jsonObject.getString("server");
                                MainServer = jsonObject.getString("mainserver");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tvTenPhim.setText(TenPhim);
                        tvNameServer.setText(NameServer);
                        tvTap.setText(Tap);
                        GetIframe(idEp);
                        GetEpisode(idPhim,MainServer);
                        GetServer(idPhim, Tap);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("aaa", error.toString());
                }
            });
            requestQueue.add(jsonArrayRequest);
        }
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void GetMenuLeft() {
        arrayMenulef.add(new MenuLeft("Trang Chủ", "https://img.icons8.com/ultraviolet/40/4a90e2/home.png"));
        arrayMenulef.add(new MenuLeft("Thể Loại", "https://img.icons8.com/ultraviolet/40/4a90e2/movie.png"));
        arrayMenulef.add(new MenuLeft("Trạng Thái", "https://img.icons8.com/ultraviolet/40/4a90e2/movie.png"));
        arrayMenulef.add(new MenuLeft("Năm Phát Hành", "https://img.icons8.com/ultraviolet/40/4a90e2/movie.png"));
        arrayMenulef.add(new MenuLeft("Thông Tin", "https://img.icons8.com/ultraviolet/40/4a90e2/gender-neutral-user.png"));
        Log.e("aaa", "3");
    }

    private void GetIframe(String idEp){
        WebSettings webSettings = wvIframe.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvIframe.loadUrl(Url.urlIframe + idEp);
    }

    private void GetEpisode(String idPhim, String idServer) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.GetAllEp(idPhim, idServer), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    String idEp = "";
                    String Episode = "";
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idEp = jsonObject.getString("idep");
                            Episode = jsonObject.getString("episode");
                            arrayEpisode.add(new Episode(idEp, Episode));
                            episodeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("aaa", error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetServer(String idPhim, String episode) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.GetServer(idPhim, episode), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    String idEp = "";
                    String server = "";
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idEp = jsonObject.getString("idEp");
                            server = jsonObject.getString("server");
                            arrayServer.add(new Server(idEp, server));
                            serverAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("aaa", error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void anhXa() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lvMenu = findViewById(R.id.listviewMenu);
        nvMenu = findViewById(R.id.nvMenu);
        drlayout = findViewById(R.id.drawerlayout);
        btnopenmenu = findViewById(R.id.btnOpenMenu);
        btnsearch = findViewById(R.id.btnSearch);
        arrayMenulef = new ArrayList<>();
        menuLeftAdapter = new MenuLeftAdapter(arrayMenulef, getApplicationContext());
        lvMenu.setAdapter(menuLeftAdapter);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvTap = findViewById(R.id.tvTap);
        tvNameServer = findViewById(R.id.tvNameServer);
        rvListEp = findViewById(R.id.rvListEp);
        rvListServer = findViewById(R.id.rvListServer);
        wvIframe = findViewById(R.id.wvIframe);
        arrayEpisode = new ArrayList<>();
        episodeAdapter = new EpisodeAdapter(getApplicationContext(), arrayEpisode);
        rvListEp.setHasFixedSize(true);
        rvListEp.setAdapter(episodeAdapter);
        rvListEp.setLayoutManager(new GridLayoutManager(getApplicationContext(), 6));
        arrayServer = new ArrayList<>();
        serverAdapter = new ServerAdapter(getApplicationContext(), arrayServer);
        rvListServer.setHasFixedSize(true);
        rvListServer.setAdapter(serverAdapter);
        rvListServer.setLayoutManager(layoutManager);
    }

    public void openMenu(View view){
        drlayout.openDrawer(GravityCompat.START);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivities(new Intent[]{intent});
    }

    public void Search(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivities(new Intent[]{intent});
    }

    private void MenuClick() {

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivities(new Intent[]{intent});
                        break;
                    case 1:
                        drlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        drlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        drlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        String savedData = sharedPreferences.getString("idUser", "");
                        if(savedData.length() == 0 ){
                            intent = new Intent(getApplicationContext(), LogRegActivity.class);
                            startActivities(new Intent[]{intent});
                        }else{
                            intent = new Intent(getApplicationContext(), UserActivity.class);
                            intent.putExtra("idUser", savedData);
                            startActivities(new Intent[]{intent});
                        }
                        break;
                }
            }
        });
    }
}