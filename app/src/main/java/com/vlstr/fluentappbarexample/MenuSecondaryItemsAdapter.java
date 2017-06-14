package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.support.annotation.MenuRes;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Valentin on 16/05/2017.
 */

public class MenuSecondaryItemsAdapter extends RecyclerView.Adapter<MenuSecondaryItemsAdapter.MenuItem> {

    private Context context;
    private ArrayList<MenuEntry> itemss;
    private View.OnClickListener onClickListener;

    public MenuSecondaryItemsAdapter(Context context, @MenuRes int secondaryMenuId, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.itemss = new ArrayList<>();

        parseMenu(secondaryMenuId);
    }

    @Override
    public MenuItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuItem(v);

    }

    @Override
    public void onBindViewHolder(MenuItem holder, int position) {
        holder.label.setText(itemss.get(position).getTitle());
        holder.icon.setImageDrawable(itemss.get(position).getIcon());
        holder.itemView.setTag(itemss.get(position).getResId());

        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return itemss.size();
    }

    private void parseMenu(@MenuRes int menuRes) {
        PopupMenu p = new PopupMenu(context, null);
        Menu menu = p.getMenu();
        new MenuInflater(context).inflate(menuRes, menu);

        for (int i = 0; i < menu.size(); i++) {
            android.view.MenuItem item = menu.getItem(i);
            itemss.add(new MenuEntry(item.getTitle().toString(), item.getIcon(), item.getItemId()));
        }
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
