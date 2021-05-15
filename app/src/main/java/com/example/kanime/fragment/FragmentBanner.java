package com.example.kanime.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.adapter.BannerAdapter;
import com.example.kanime.model.SlidePhim;
import com.example.kanime.server.Url;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentBanner extends Fragment {
    ArrayList<SlidePhim> arraySlide = new ArrayList<>();
    View view;
    private Context mContext;
    ViewPager viewPager;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int curentItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        anhXa();
        getData();
        return view;
    }

    private void anhXa() {
        viewPager = view.findViewById(R.id.vpBanner);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlSlide, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    String id = "";
                    String TenPhim = "";
                    String Luotxem = "";
                    String Anhbia = "";
                    String Anhnen = "";
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("id");
                            TenPhim = jsonObject.getString("name");
                            Luotxem = jsonObject.getString("view");
                            Anhbia = jsonObject.getString("bia");
                            Anhnen = jsonObject.getString("nen");
                            arraySlide.add(new SlidePhim(id, TenPhim, Luotxem, Anhbia, Anhnen));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    bannerAdapter = new BannerAdapter(getActivity(), arraySlide);
                    viewPager.setAdapter(bannerAdapter);
                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            curentItem = viewPager.getCurrentItem();
                            curentItem++;
                            if(curentItem >= viewPager.getAdapter().getCount()){
                                curentItem = 0;
                            }
                            viewPager.setCurrentItem(curentItem, true);
                            handler.postDelayed(runnable, 5000);
                        }
                    };
                    handler.postDelayed(runnable, 5000);
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
