package com.example.kanime.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.activity.LogRegActivity;
import com.example.kanime.activity.MainActivity;
import com.example.kanime.server.Url;

import java.util.HashMap;
import java.util.Map;

public class FragmentLogin extends Fragment {
    private Context mContext;
    EditText etTdn, etPass;
    ImageButton ibLogin;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        anhXa(view);
        initPreferences();
        init();
        return view;
    }

    private void init() {
        ibLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tdn = etTdn.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                if (tdn.equals("") || pass.equals("")) {
                    Toast.makeText(mContext, "Vui lòng điền đầy đủ !!!", Toast.LENGTH_LONG).show();
                } else {
                    Login(tdn, pass);
                }
            }
        });
    }

    private void anhXa(View view) {
        etTdn = view.findViewById(R.id.etTdn);
        etPass = view.findViewById(R.id.etMatKhau);
        ibLogin = view.findViewById(R.id.ibLogin);
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    public void Login(String tdn, String pass) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.urlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("false")){
                    Toast.makeText(mContext, "Sai tên đăng nhập hoặc mật khẩu !!!", Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("idUser", response);
                    editor.commit();
                    Toast.makeText(mContext, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivities(new Intent[]{intent});
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Đã xảy ra lỗi vui lòng thử lại sau !!!", Toast.LENGTH_SHORT).show();

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("tdn", tdn);
                data.put("pw", pass);
                return data;
            }
        };
        requestQueue.add(stringRequest);
    }
}
