package com.example.kanime.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kanime.R;
import com.example.kanime.adapter.FragmentAdapter;
import com.example.kanime.fragment.FragmentLogin;
import com.example.kanime.fragment.FragmentRegister;
import com.google.android.material.tabs.TabLayout;

public class LogRegActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        anhXa();
        init();
    }

    private void init() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentAdapter.addFragment(new FragmentLogin(), "Đăng nhập");
        fragmentAdapter.addFragment(new FragmentRegister(), "Đăng ký");
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void anhXa() {
        tabLayout = findViewById(R.id.mytablayout);
        viewPager = findViewById(R.id.myviewpager);
    }

    public void returnHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivities(new Intent[]{intent});
    }
}