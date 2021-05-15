package com.example.kanime.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanime.R;
import com.example.kanime.activity.ThongTinPhimActivity;
import com.example.kanime.model.PhimSearch;
import com.example.kanime.ultil.CheckConnect;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemHolder>{
    Context context;
    ArrayList<PhimSearch> arrayPhimsearch;

    public SearchAdapter(Context context, ArrayList<PhimSearch> arrayPhimsearch) {
        this.context = context;
        this.arrayPhimsearch = arrayPhimsearch;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_phimsearch, null);
        SearchAdapter.ItemHolder itemHolder = new SearchAdapter.ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        PhimSearch phimSearch = arrayPhimsearch.get(position);
        holder.TenPhim.setText(phimSearch.getTenphim());
        holder.TenKhac.setText(phimSearch.getTenkhac());
        holder.LuotXem.setText("Lượt xem: " + phimSearch.getLuotxem());
        Picasso.get().load(phimSearch.getAnhbia()).into(holder.AnhPhim);
    }

    @Override
    public int getItemCount() {
        return arrayPhimsearch.size();
    }

    public void clear(){
        arrayPhimsearch.clear();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView AnhPhim;
        public TextView TenPhim;
        public TextView TenKhac;
        public TextView LuotXem;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            AnhPhim = itemView.findViewById(R.id.ivAnhPhimSearch);
            TenPhim = itemView.findViewById(R.id.tvTenPhimSearch);
            TenKhac = itemView.findViewById(R.id.tvTenKhacSearch);
            LuotXem = itemView.findViewById(R.id.tvLuotXemSearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ThongTinPhimActivity.class);
                    intent.putExtra("idPhim", String.valueOf(arrayPhimsearch.get(getPosition()).getID()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
