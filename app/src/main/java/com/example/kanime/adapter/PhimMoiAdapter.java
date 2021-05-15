package com.example.kanime.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.kanime.ultil.CheckConnect;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhimMoiAdapter extends RecyclerView.Adapter<PhimMoiAdapter.ItemHolder> {
    Context context;
    ArrayList<PhimMoi> arrayPhimmoi;

    public PhimMoiAdapter(Context context, ArrayList<PhimMoi> arrayPhimmoi) {
        this.context = context;
        this.arrayPhimmoi = arrayPhimmoi;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_phimmoi, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        PhimMoi phimmoi = arrayPhimmoi.get(position);
        holder.TenPhim.setText(phimmoi.getTenphim());
        Picasso.get().load(phimmoi.getAnhbia()).into(holder.AnhPhim);
    }

    @Override
    public int getItemCount() {
        return arrayPhimmoi.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView AnhPhim;
        public TextView TenPhim;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            AnhPhim = itemView.findViewById(R.id.ivPhimMoi);
            TenPhim = itemView.findViewById(R.id.tvTenPhim);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ThongTinPhimActivity.class);
                    intent.putExtra("idPhim", String.valueOf(arrayPhimmoi.get(getPosition()).getID()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivities(new Intent[]{intent});
                }
            });
        }
    }
}
