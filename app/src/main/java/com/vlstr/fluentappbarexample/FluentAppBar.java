package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Valentin on 16/05/2017.
 */

public class FluentAppBar extends NestedScrollView {

    private BottomSheetBehavior bottomSheetBehavior;

    public FluentAppBar(Context context) {
        super(context);
        init();
    }

    public FluentAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FluentAppBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setClipToPadding(true);
        setBackgroundColor(Color.WHITE);
        CoordinatorLayout.LayoutParams layoutParams =
                new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) setElevation(getResources().getDimension(R.dimen.bar_elevation));

        LayoutInflater.from(getContext()).inflate(R.layout.content_app_bar, this, true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        bottomSheetBehavior = BottomSheetBehavior.from(this);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.bar_height));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}

