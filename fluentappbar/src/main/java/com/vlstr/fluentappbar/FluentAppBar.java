package com.vlstr.fluentappbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by Valentin on 16/05/2017.
 */

public class FluentAppBar extends NestedScrollView {

    public static final int DISABLE_FLUENT = 0;
    public static final int CLICK_FLUENT = 50;
    public static final int FULL_FLUENT = 100;

    public static final String MORE_ICON_TAG = "more_icon_tag";

    private int blurRadius = 20;
    private int backgroundAlpha = 78;

    private BottomSheetBehavior bottomSheetBehavior;

    private int backgroundColour;
    private int foregroundColour;
    private int fluentAppBarType;
    private boolean keepFluentRipple;

    private MenuSecondaryItemsAdapter menuSecondaryItemsAdapter;
    private MenuNavigationItemsAdapter menuNavigationItemsAdapter;

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
            fluentAppBarType = a.getInt(R.styleable.FluentAppBar_fluent_app_bar_type, FULL_FLUENT);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        setClipToPadding(true);
        setBackgroundColor(backgroundColour);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(getResources().getDimension(R.dimen.fluentappbar_bar_elevation));
        }

        LayoutInflater.from(getContext()).inflate(R.layout.content_app_bar, this, true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        bottomSheetBehavior = BottomSheetBehavior.from(this);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.fluentappbar_bar_height));
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);

        View moreIcon = findViewWithTag(MORE_ICON_TAG);
        moreIcon.setOnClickListener(onMoreClickListener);

        if (fluentAppBarType == FULL_FLUENT) {
            handleShowFluentBlur();
        }

        if (fluentAppBarType == DISABLE_FLUENT) {
            keepFluentRipple = false;
        }
    }

    ///// ********************************************** /////
    ///// *********    PUBLIC APIS/METHODS    ********** /////
    ///// ********************************************** /////

    public void setNavigationMenu(@MenuRes int menuRes, OnClickListener onClickListener) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.nav_items_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        menuNavigationItemsAdapter = new MenuNavigationItemsAdapter(getContext(), menuRes, onClickListener,
                foregroundColour);
        recyclerView.setAdapter(menuNavigationItemsAdapter);
    }

    public void setSecondaryMenu(@MenuRes int menuRes, OnClickListener onClickListener) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.secondary_menu_items_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        menuSecondaryItemsAdapter = new MenuSecondaryItemsAdapter(getContext(), menuRes, onClickListener,
                foregroundColour);
        recyclerView.setAdapter(menuSecondaryItemsAdapter);
    }

    public int getBackgroundColour() {
        return backgroundColour;
    }

    public void setBackgroundColour(@ColorInt int backgroundColour) {
        this.backgroundColour = backgroundColour;
        this.setBackgroundColor(backgroundColour);
    }

    public int getForegroundColour() {
        return foregroundColour;
    }

    public void setForegroundColour(@ColorInt int foregroundColour) {
        this.foregroundColour = foregroundColour;

        menuNavigationItemsAdapter.setForegroundColour(foregroundColour);
        menuNavigationItemsAdapter.notifyDataSetChanged();

        menuSecondaryItemsAdapter.setForegroundColour(foregroundColour);
        menuSecondaryItemsAdapter.notifyDataSetChanged();
    }

    public int getFluentAppBarType() {
        return fluentAppBarType;
    }

    public void setFluentAppBarType(int fluentAppBarType) {
        this.fluentAppBarType = fluentAppBarType;
        menuNavigationItemsAdapter.setForegroundColour(foregroundColour);
        menuNavigationItemsAdapter.notifyDataSetChanged();

        menuSecondaryItemsAdapter.setForegroundColour(foregroundColour);
        menuSecondaryItemsAdapter.notifyDataSetChanged();
    }

    public boolean isFluentRipple() {
        return keepFluentRipple;
    }

    public void setFluentRipple(boolean keepFluentRipple) {
        this.keepFluentRipple = keepFluentRipple;
        menuNavigationItemsAdapter.setKeepFluentRipple(keepFluentRipple);
        menuSecondaryItemsAdapter.setKeepFluentRipple(keepFluentRipple);
    }

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public int getBackgroundAlpha() {
        return backgroundAlpha;
    }

    public void setBackgroundAlpha(int backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
    }

    public void collapse() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }, 500);
    }

    public void collapseWithoutDelay() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void expand() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    ///// ********************************************** /////
    ///// *********    END OF APIS/METHODS    ********** /////
    ///// ********************************************** /////


    private OnClickListener onMoreClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }
    };

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (fluentAppBarType == CLICK_FLUENT) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    handleShowFluentBlur();
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    BlurView blurView = (BlurView) findViewById(R.id.blurview);
                    blurView.setBlurEnabled(false);
                    setBackgroundColor(backgroundColour);
                }
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
    };

    private void handleShowFluentBlur() {
        setBackground(null);
        final ViewGroup rootView = (ViewGroup) getRootView();
        final Drawable windowBackground = getBackground();
        BlurView blurView = (BlurView) findViewById(R.id.blurview);
        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(getContext()))
                .blurRadius(blurRadius);
        int transparentBackgroundColour = Color.argb(backgroundAlpha,
                Color.red(backgroundColour),
                Color.green(backgroundColour),
                Color.blue(backgroundColour));
        blurView.setOverlayColor(transparentBackgroundColour);
    }

}

