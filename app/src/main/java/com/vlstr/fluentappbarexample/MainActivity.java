package com.vlstr.fluentappbarexample;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.bar_height));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.menu_items_recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MenuItemsAdapter adapter = new MenuItemsAdapter(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView.setAdapter(adapter);
    }
}
