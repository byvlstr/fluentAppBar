package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Valentin on 16/05/2017.
 */

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.MenuItem> {

    private HashMap<Integer, Pair<String, Drawable>> items;

    private View.OnClickListener onClickListener;

    public MenuItemsAdapter(Context context, View.OnClickListener onClickListener) {

        this.onClickListener = onClickListener;

        TypedArray icons = context.getResources().obtainTypedArray(R.array.fluent_app_bar_icons);
        TypedArray labels = context.getResources().obtainTypedArray(R.array.fluent_app_bar_labels);

        this.items = new HashMap<>();

        for (int i = 0; i < labels.length(); i++) {
            this.items.put(i, new Pair<>(labels.getString(i), icons.getDrawable(i)));
        }

        icons.recycle();
        labels.recycle();
    }

    @Override
    public MenuItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuItem(v);

    }

    @Override
    public void onBindViewHolder(MenuItem holder, int position) {
        holder.label.setText(items.get(position).first);
        holder.icon.setImageDrawable(items.get(position).second);

        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MenuItem extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView label;

        MenuItem(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.menu_item_icon);
            label = (TextView) itemView.findViewById(R.id.menu_item_label);
        }
    }
}
