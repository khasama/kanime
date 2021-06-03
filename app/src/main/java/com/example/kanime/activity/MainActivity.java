package com.example.kanime.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.adapter.MenuLeftAdapter;
import com.example.kanime.adapter.PhimMoiAdapter;
import com.example.kanime.model.MenuLeft;
import com.example.kanime.model.PhimMoi;
import com.example.kanime.server.Url;
import com.example.kanime.ultil.CheckConnect;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcNew;
    NavigationView nvMenu;
    ListView lvMenu;
    DrawerLayout drlayout;
    ImageButton btnopenmenu, btnsearch;
    ArrayList<PhimMoi> arrayPhimmoi;
    PhimMoiAdapter phimMoiAdapter;
    ArrayList<MenuLeft> arrayMenulef;
    MenuLeftAdapter menuLeftAdapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPreferences();
        anhXa();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            GetPhimMoi();
            GetMenuLeft();
            MenuClick();
        }else{
            CheckConnect.Thongbao(getApplicationContext(), "Kiểm tra lại kết nối!!!");
            finish();
        }
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void MenuClick() {

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        drlayout.closeDrawer(GravityCompat.START);
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
                            Intent intent = new Intent(MainActivity.this, LogRegActivity.class);
                            startActivities(new Intent[]{intent});
                        }else{
                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            intent.putExtra("idUser", savedData);
                            startActivities(new Intent[]{intent});
                        }
                        break;
                }
            }
        });
    }

    private void GetMenuLeft() {
        arrayMenulef.add(new MenuLeft("Trang Chủ", "https://img.icons8.com/ultraviolet/40/4a90e2/home.png"));
        arrayMenulef.add(new MenuLeft("Thể Loại", "https://img.icons8.com/ultraviolet/40/4a90e2/movie.png"));
        arrayMenulef.add(new MenuLeft("Trạng Thái", "https://img.icons8.com/ultraviolet/40/4a90e2/movie.png"));
        arrayMenulef.add(new MenuLeft("Năm Phát Hành", "https://img.icons8.com/ultraviolet/40/4a90e2/movie.png"));
        arrayMenulef.add(new MenuLeft("Thông Tin", "https://img.icons8.com/ultraviolet/40/4a90e2/gender-neutral-user.png"));
    }

    private void GetPhimMoi() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlPhimmoi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int ID = 0;
                    String TenPhim = "";
                    int Luotxem = 0;
                    String Anhbia = "";
                    String Anhnen = "";
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            TenPhim = jsonObject.getString("name");
                            Luotxem = jsonObject.getInt("view");
                            Anhbia = jsonObject.getString("bia");
                            Anhnen = jsonObject.getString("nen");
                            arrayPhimmoi.add(new PhimMoi(ID, TenPhim, Luotxem, Anhbia, Anhnen));
                            phimMoiAdapter.notifyDataSetChanged();
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
        rcNew = findViewById(R.id.listNew);
        lvMenu = findViewById(R.id.listviewMenu);
        nvMenu = findViewById(R.id.nvMenu);
        drlayout = findViewById(R.id.drawerlayout);
        btnopenmenu = findViewById(R.id.btnOpenMenu);
        btnsearch = findViewById(R.id.btnSearch);
        arrayPhimmoi = new ArrayList<>();
        phimMoiAdapter = new PhimMoiAdapter(getApplicationContext(), arrayPhimmoi);
        rcNew.setHasFixedSize(true);
        rcNew.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rcNew.setAdapter(phimMoiAdapter);
        arrayMenulef = new ArrayList<>();
        menuLeftAdapter = new MenuLeftAdapter(arrayMenulef, getApplicationContext());
        lvMenu.setAdapter(menuLeftAdapter);
    }

    public void openMenu(View view){
        drlayout.openDrawer(GravityCompat.START);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }

    public void Search(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivities(new Intent[]{intent});
    }
}