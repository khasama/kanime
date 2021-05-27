package com.example.kanime.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.server.Url;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {

    TextView tvTaiKhoan, tvEmail;
    EditText etTaiKhoa, etEmail;
    ImageButton ibBack, ibLogout;
    ImageView ivAvatar;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initPreferences();
        anhXa();
        init();
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void init() {
        Bundle bd = getIntent().getExtras();
        if( bd != null){
            String idUser = bd.getString("idUser");
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlUser + idUser, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if(response != null){
                        String id = "";
                        String userName = "";
                        String email = "";
                        String avatar = "";

                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                id = jsonObject.getString("id");
                                userName = jsonObject.getString("user-name");
                                email = jsonObject.getString("email");
                                avatar = jsonObject.getString("avatar");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Picasso.get().load(avatar).into(ivAvatar);
                        tvTaiKhoan.setText(userName);
                        tvEmail.setText(email);
                        etTaiKhoa.setText(userName);
                        etEmail.setText(email);
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

    private void anhXa() {
        tvTaiKhoan = findViewById(R.id.tvTaiKhoan);
        tvEmail = findViewById(R.id.tvEmail);
        ivAvatar = findViewById(R.id.ivAvatar);
        etTaiKhoa = findViewById(R.id.etTaiKhoan);
        etEmail = findViewById(R.id.etEmail);
    }
}