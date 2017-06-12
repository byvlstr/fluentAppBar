package com.vlstr.fluentappbarexample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFluentAppBar();

    }

    private void setupFluentAppBar() {
        FluentAppBar fluentAppBar = (FluentAppBar) findViewById(R.id.fluent_app_bar);
        RecyclerView recyclerView = fluentAppBar.getSecondaryMenuItemsRecycler();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MenuSecondaryItemsAdapter adapter = new MenuSecondaryItemsAdapter(this, R.menu.fluent_app_bar_main_menu);
        recyclerView.setAdapter(adapter);

        Drawable d = getResources().getDrawable(R.drawable.ellipsis1);
        String yo = "Amazingness";
        fluentAppBar.setMainMenuItemsContent(yo, d, yo, d, yo, d, yo, d);
    }
}
