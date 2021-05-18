package com.example.kanime.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanime.R;
import com.example.kanime.model.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemHolder>{

    Context context;
    ArrayList<Comment> arrayCmt;

    public CommentAdapter(Context context, ArrayList<Comment> arrayCmt) {
        this.context = context;
        this.arrayCmt = arrayCmt;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_cmt, null);
        CommentAdapter.ItemHolder itemHolder = new CommentAdapter.ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Comment comment = arrayCmt.get(position);
        holder.tvUserName.setText(comment.getUserName());
        holder.tvDate.setText(comment.getDate());
        holder.tvContent.setText(comment.getContent());
        Picasso.get().load(comment.getAvatar()).into(holder.ivAvatar);
        Log.e("aaaaaa", comment.getContent());
    }

    @Override
    public int getItemCount() {
        return arrayCmt.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tvUserName, tvDate, tvContent;
        public ImageView ivAvatar;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, XemPhimActivity.class);
//                    intent.putExtra("idEp", String.valueOf(arrayEpisode.get(getPosition()).getIdEP()));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivities(new Intent[]{intent});
//                }
//            });
        }
    }
}
