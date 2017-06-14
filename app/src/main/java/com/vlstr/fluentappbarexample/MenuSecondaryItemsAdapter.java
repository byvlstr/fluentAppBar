package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.support.annotation.MenuRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
    private View.OnClickListener onClickListener;
    private int foregroundColour;

    private ArrayList<MenuEntry> itemss;

    public MenuSecondaryItemsAdapter(Context context, @MenuRes int secondaryMenuId, View.OnClickListener onClickListener, int foregroundColour) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.foregroundColour = foregroundColour;
        this.itemss = new ArrayList<>();

        MenuParserHelper.parseMenu(context, secondaryMenuId, itemss);
    }

    @Override
    public MenuItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuItem(v);

    }

    @Override
    public void onBindViewHolder(MenuItem holder, int position) {
        holder.label.setText(itemss.get(position).getTitle());
        holder.label.setTextColor(foregroundColour);
        holder.icon.setImageDrawable(itemss.get(position).getIcon());
        holder.icon.setColorFilter(foregroundColour);
        holder.itemView.setTag(itemss.get(position).getResId());

        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return itemss.size();
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
