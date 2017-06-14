package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Valentin on 16/05/2017.
 */


public class FluentAppBar extends NestedScrollView {

    public static final String MORE_ICON_TAG = "more_icon_tag";

    private BottomSheetBehavior bottomSheetBehavior;

    private int backgroundColour;

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
            backgroundColour = a.getColor(R.styleable.FluentAppBar_fluent_background, Color.WHITE);
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


    public RecyclerView getMenuNavigationItemsRecycler() {
        return (RecyclerView) findViewById(R.id.nav_items_recycler);
    }

    public RecyclerView getSecondaryMenuItemsRecycler() {
        return (RecyclerView) findViewById(R.id.secondary_menu_items_recyler);
    }

    public int getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(int backgroundColour) {
        this.backgroundColour = backgroundColour;
    }
}

