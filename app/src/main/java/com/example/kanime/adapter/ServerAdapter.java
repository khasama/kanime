package com.example.kanime.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanime.R;
import com.example.kanime.activity.XemPhimActivity;
import com.example.kanime.model.Episode;
import com.example.kanime.model.Server;
import com.example.kanime.ultil.CheckConnect;

import java.util.ArrayList;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ItemHolder>{
    Context context;
    ArrayList<Server> arrayServer;

    public ServerAdapter(Context context, ArrayList<Server> arrayServer) {
        this.context = context;
        this.arrayServer = arrayServer;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_server, null);
        ServerAdapter.ItemHolder itemHolder = new ServerAdapter.ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Server server = arrayServer.get(position);
        holder.Server.setText(server.getServer());
    }

    @Override
    public int getItemCount() {
        return arrayServer.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView Server;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            Server = itemView.findViewById(R.id.tvServer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, XemPhimActivity.class);
                    intent.putExtra("idEp", String.valueOf(arrayServer.get(getPosition()).getIdEp()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
