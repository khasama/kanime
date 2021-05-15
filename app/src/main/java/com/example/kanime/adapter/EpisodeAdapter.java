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
import com.example.kanime.activity.XemPhimActivity;
import com.example.kanime.model.Episode;
import com.example.kanime.model.PhimMoi;
import com.example.kanime.ultil.CheckConnect;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ItemHolder>{
    Context context;
    ArrayList<Episode> arrayEpisode;

    public EpisodeAdapter(Context context, ArrayList<Episode> arrayEpisode) {
        this.context = context;
        this.arrayEpisode = arrayEpisode;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_episode, null);
        EpisodeAdapter.ItemHolder itemHolder = new EpisodeAdapter.ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Episode episode = arrayEpisode.get(position);
        holder.Episode.setText(episode.getEpisode());
    }

    @Override
    public int getItemCount() {
        return arrayEpisode.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView Episode;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            Episode = itemView.findViewById(R.id.tvEpisode);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, XemPhimActivity.class);
                    intent.putExtra("idEp", String.valueOf(arrayEpisode.get(getPosition()).getIdEP()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
