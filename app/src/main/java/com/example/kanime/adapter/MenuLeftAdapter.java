package com.example.kanime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanime.R;
import com.example.kanime.model.MenuLeft;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuLeftAdapter extends BaseAdapter {
    ArrayList<MenuLeft> arrMenuleft;
    Context context;

    public MenuLeftAdapter(ArrayList<MenuLeft> arrMenuleft, Context context) {
        this.arrMenuleft = arrMenuleft;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrMenuleft.size();
    }

    @Override
    public Object getItem(int position) {
        return arrMenuleft.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView tvTenMenu;
        ImageView ivAnhMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_menutrai, null);
            viewHolder.tvTenMenu = convertView.findViewById(R.id.tvTenMenu);
            viewHolder.ivAnhMenu = convertView.findViewById(R.id.ivAnhMenu);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuLeft menuLeft = (MenuLeft) getItem(position);
        viewHolder.tvTenMenu.setText(menuLeft.getTenMenu());
        Picasso.get().load(menuLeft.getAnhMenu()).into(viewHolder.ivAnhMenu);
        return convertView;
    }
}
