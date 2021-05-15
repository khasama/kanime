package com.example.kanime.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanime.R;
import com.example.kanime.activity.ThongTinPhimActivity;
import com.example.kanime.model.PhimMoi;
import com.example.kanime.model.Season;
import com.example.kanime.ultil.CheckConnect;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.ItemHolder>{
    Context context;
    ArrayList<Season> arraySeason;

    public SeasonAdapter(Context context, ArrayList<Season> arraySeason) {
        this.context = context;
        this.arraySeason = arraySeason;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_season, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Season season = arraySeason.get(position);
        holder.Season.setText(season.getSeason());
        Log.e("eee", season.getId());
    }

    @Override
    public int getItemCount() {
        return arraySeason.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView Season;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            Season = itemView.findViewById(R.id.tvSeason);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ThongTinPhimActivity.class);
                    intent.putExtra("idPhim", String.valueOf(arraySeason.get(getPosition()).getId()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
