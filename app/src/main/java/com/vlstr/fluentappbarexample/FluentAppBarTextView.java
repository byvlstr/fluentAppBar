package com.vlstr.fluentappbarexample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;

/**
 * Created by Valentin on 16/05/2017.
 */

public class FluentAppBarTextView extends AppCompatTextView {

    public FluentAppBarTextView(Context context) {
        super(context);
        init();
    }

    public FluentAppBarTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FluentAppBarTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLines(1);
        setGravity(Gravity.CENTER);
    }
}
