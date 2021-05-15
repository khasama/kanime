package com.example.kanime.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.adapter.SearchAdapter;
import com.example.kanime.model.PhimMoi;
import com.example.kanime.model.PhimSearch;
import com.example.kanime.server.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText etSearch;
    RecyclerView rvListSearch;
    ImageButton btnSearch;
    ArrayList<PhimSearch> arrayPhimsearch;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhXa();
        suggestSearch();
    }

    private void suggestSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String q = etSearch.getText().toString();
                if(q.length() > 2){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlSearch + q, new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {

                            if(response.length() != 0){
                                searchAdapter.clear();
                                String id = "";
                                String TenPhim = "";
                                String TenKhac = "";
                                String Luotxem = "";
                                String Anhbia = "";
                                for(int i = 0; i < response.length(); i++){
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        id = jsonObject.getString("id");
                                        TenPhim = jsonObject.getString("name");
                                        TenKhac = jsonObject.getString("oname");
                                        Luotxem = jsonObject.getString("view");
                                        Anhbia = jsonObject.getString("bia");
                                        arrayPhimsearch.add(new PhimSearch(id, TenPhim, TenKhac, Luotxem, Anhbia));
                                        searchAdapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                rvListSearch.setAdapter(searchAdapter);
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
        });
    }

    private void anhXa() {
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rvListSearch = findViewById(R.id.rvListSearch);
        arrayPhimsearch = new ArrayList<>();
        searchAdapter = new SearchAdapter(getApplicationContext(), arrayPhimsearch);
        rvListSearch.setHasFixedSize(true);
        rvListSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

    }

    public void goHome(View view) {
        finish();
    }

//    public void Search(View view) {
//        String q = etSearch.getText().toString();
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlSearch + q, new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//
//                if(response.length() != 0){
//                    searchAdapter.clear();
//                    String id = "";
//                    String TenPhim = "";
//                    String TenKhac = "";
//                    String Luotxem = "";
//                    String Anhbia = "";
//                    for(int i = 0; i < response.length(); i++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            id = jsonObject.getString("id");
//                            TenPhim = jsonObject.getString("name");
//                            TenKhac = jsonObject.getString("oname");
//                            Luotxem = jsonObject.getString("view");
//                            Anhbia = jsonObject.getString("bia");
//                            arrayPhimsearch.add(new PhimSearch(id, TenPhim, TenKhac, Luotxem, Anhbia));
//                            searchAdapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    rvListSearch.setAdapter(searchAdapter);
//                }
//                else{
//                    Toast.makeText(SearchActivity.this, "Không có kết quả !!", Toast.LENGTH_LONG).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("aaa", error.toString());
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }


}