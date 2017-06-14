package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.MenuRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Valentin on 16/05/2017.
 */


public class FluentAppBar extends NestedScrollView {

    public static final String MORE_ICON_TAG = "more_icon_tag";

    private BottomSheetBehavior bottomSheetBehavior;

    private int backgroundColour;
    private int foregroundColour;

    private OnClickListener onMoreClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            else
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    };

    public FluentAppBar(Context context) {
        super(context);
        init();
    }

    public FluentAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FluentAppBar, 0, 0);
        try {
            backgroundColour = a.getColor(R.styleable.FluentAppBar_fluent_background_colour, Color.WHITE);
            foregroundColour = a.getColor(R.styleable.FluentAppBar_fluent_foreground_colour, Color.DKGRAY);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        setClipToPadding(true);
        setBackgroundColor(backgroundColour);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setElevation(getResources().getDimension(R.dimen.bar_elevation));

        LayoutInflater.from(getContext()).inflate(R.layout.content_app_bar, this, true);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        bottomSheetBehavior = BottomSheetBehavior.from(this);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.bar_height));
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        View moreIcon = findViewWithTag(MORE_ICON_TAG);
        moreIcon.setOnClickListener(onMoreClickListener);
    }

    public void setNavigationMenu(@MenuRes int menuRes, OnClickListener onClickListener){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.nav_items_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        MenuNavigationItemsAdapter adapter = new MenuNavigationItemsAdapter(getContext(), menuRes, onClickListener, foregroundColour);
        recyclerView.setAdapter(adapter);
    }

    public void setSecondaryMenu(@MenuRes int menuRes, OnClickListener onClickListener){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.secondary_menu_items_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MenuSecondaryItemsAdapter adapter = new MenuSecondaryItemsAdapter(getContext(), menuRes, onClickListener, foregroundColour);
        recyclerView.setAdapter(adapter);
    }

    public int getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(int backgroundColour) {
        this.backgroundColour = backgroundColour;
        this.setBackgroundColor(backgroundColour);
    }
}

