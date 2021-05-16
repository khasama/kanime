package com.example.kanime.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kanime.R;
import com.example.kanime.activity.LogRegActivity;
import com.example.kanime.activity.MainActivity;
import com.example.kanime.model.PhimMoi;
import com.example.kanime.server.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentRegister extends Fragment {
    private Context mContext;
    EditText etEmail, etTdn, etPass, etRePass;
    ImageButton ibRegister;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        anhXa(view);
        init();
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    private void anhXa(View view) {
        etEmail = view.findViewById(R.id.etEmail);
        etTdn = view.findViewById(R.id.etTdn);
        etPass = view.findViewById(R.id.etMatKhau);
        etRePass = view.findViewById(R.id.etReMatKhau);
        ibRegister = view.findViewById(R.id.ibRegister);
    }

    private void init() {
        ibRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String email = etEmail.getText().toString().trim();
                String tdn = etTdn.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                String repass = etRePass.getText().toString().trim();
                if (email.matches(emailPattern) && tdn.length() >= 6 && pass.length() >= 6 && pass.equals(repass)) {
                    register(email, tdn, pass, repass);
                } else if(!email.matches(emailPattern)){
                    Toast.makeText(mContext, "Email không đúng định dạng !!!", Toast.LENGTH_LONG).show();
                } else if(tdn.length() < 6) {
                    Toast.makeText(mContext, "Tên đăng nhập phải có ít nhất 6 kí tự !!!", Toast.LENGTH_LONG).show();
                } else if(pass.length() < 6) {
                    Toast.makeText(mContext, "Mật khẩu phải có ít nhất 6 kí tự !!!", Toast.LENGTH_LONG).show();
                } else if(!pass.equals(repass)) {
                    Toast.makeText(mContext, "Nhập lại mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void register(String email, String tdn, String pass, String repass){
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.urlRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("true")){
                    Toast.makeText(mContext, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, LogRegActivity.class);
                    mContext.startActivities(new Intent[]{intent});
                }else{
                    Toast.makeText(mContext, "Đã tồn tại Email hoặc tên đăng nhập trong hệ thống !!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Đã xảy ra lỗi vui lòng thử lại sau !!!", Toast.LENGTH_SHORT).show();

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> data = new HashMap<>();
                data.put("eml", email);
                data.put("tdndk", tdn);
                data.put("pwdk", pass);
                data.put("rpwdk", repass);
                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

}
