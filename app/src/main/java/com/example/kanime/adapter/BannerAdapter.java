package com.example.kanime.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.kanime.R;
import com.example.kanime.activity.ThongTinPhimActivity;
import com.example.kanime.model.SlidePhim;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<SlidePhim> arraySlide;

    public BannerAdapter(Context context, ArrayList<SlidePhim> arraySlide) {
        this.context = context;
        this.arraySlide = arraySlide;
    }

    @Override
    public int getCount() {
        return arraySlide.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView ivBgBanner = view.findViewById(R.id.ivBgBanner);
        TextView tvTenPhim = view.findViewById(R.id.tvTenPhim);
        TextView tvLuotXem = view.findViewById(R.id.tvLuotXem);

        Picasso.get().load(arraySlide.get(position).getAnhnen()).into(ivBgBanner);
        tvTenPhim.setText(arraySlide.get(position).getTenphim());
        tvLuotXem.setText(arraySlide.get(position).getLuotxem());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThongTinPhimActivity.class);
                intent.putExtra("idPhim", arraySlide.get(position).getId());
                context.startActivities(new Intent[]{intent});
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
