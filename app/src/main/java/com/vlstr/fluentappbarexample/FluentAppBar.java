package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Valentin on 16/05/2017.
 */

//TODO config if 3 or 4 items
//  TODO blur on touch
// TODO colour shift on black background
public class FluentAppBar extends NestedScrollView {

    private TextView label1View;
    private TextView label2View;
    private TextView label3View;
    private TextView label4View;
    private ImageView icon1View;
    private ImageView icon2View;
    private ImageView icon3View;
    private ImageView icon4View;

    private BottomSheetBehavior bottomSheetBehavior;

    private int numItems = 3; //DEFAULT

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

    private void init() {
        setClipToPadding(true);
        setBackgroundColor(Color.WHITE);
        CoordinatorLayout.LayoutParams layoutParams =
                new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setElevation(getResources().getDimension(R.dimen.bar_elevation));

        LayoutInflater.from(getContext()).inflate(R.layout.content_app_bar, this, true);
        initViews();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        bottomSheetBehavior = BottomSheetBehavior.from(this);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.bar_height));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void initViews() {
        label1View = (TextView) findViewById(R.id.label1);
        label2View = (TextView) findViewById(R.id.label2);
        label3View = (TextView) findViewById(R.id.label3);
        label4View = (TextView) findViewById(R.id.label4);
        icon1View = (ImageView) findViewById(R.id.icon1);
        icon2View = (ImageView) findViewById(R.id.icon2);
        icon3View = (ImageView) findViewById(R.id.icon3);
        icon4View = (ImageView) findViewById(R.id.icon4);
    }

    public void setMainMenuItemsContent(String label1, Drawable icon1, String label2, Drawable icon2, String label3, Drawable icon3) {
        label1View.setText(label1);
        icon1View.setImageDrawable(icon1);
        label3View.setText(label2);
        icon3View.setImageDrawable(icon2);
        label4View.setText(label3);
        icon4View.setImageDrawable(icon3);

        label2View.setVisibility(GONE);
        icon2View.setVisibility(GONE);
        numItems = 3;
    }

    public void setMainMenuItemsContent(String label1, Drawable icon1, String label2, Drawable icon2,
                                        String label3, Drawable icon3, String label4, Drawable icon4) {
        label1View.setText(label1);
        icon1View.setImageDrawable(icon1);
        label2View.setText(label2);
        icon2View.setImageDrawable(icon2);
        label3View.setText(label3);
        icon3View.setImageDrawable(icon3);
        label4View.setText(label4);
        icon4View.setImageDrawable(icon4);

        label2View.setVisibility(VISIBLE);
        icon2View.setVisibility(VISIBLE);
        numItems = 4;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
        if (numItems == 3) {
            label2View.setVisibility(GONE);
            icon2View.setVisibility(GONE);
        } else if (numItems == 4) {
            label2View.setVisibility(VISIBLE);
            icon2View.setVisibility(VISIBLE);
        }
    }

    public int getNumItems() {
        return numItems;
    }

    public RecyclerView getSecondaryMenuItemsRecycler() {
        return (RecyclerView) findViewById(R.id.secondary_menu_items_recyler);
    }
}

