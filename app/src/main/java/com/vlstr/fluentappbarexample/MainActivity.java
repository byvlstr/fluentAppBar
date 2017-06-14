package com.vlstr.fluentappbarexample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        MenuSecondaryItemsAdapter adapter = new MenuSecondaryItemsAdapter(this, R.menu.fluent_app_bar_secondary_menu, this);
        recyclerView.setAdapter(adapter);

        RecyclerView navItems = fluentAppBar.getMenuNavigationItemsRecycler();
        navItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MenuNavigationItemsAdapter menuNavigationItemsAdapter = new MenuNavigationItemsAdapter(this, R.menu.fluent_app_bar_main_menu, this);
        navItems.setAdapter(menuNavigationItemsAdapter);
    }

    @Override
    public void onClick(View v) {
        int resId = (int) v.getTag();
        switch (resId){
            //Navigation Menu
            case R.id.nav_ellipsis:
                Toast.makeText(MainActivity.this, "ELLIPSIS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_more:
                Toast.makeText(MainActivity.this, "MORE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_more2:
                Toast.makeText(MainActivity.this, "MORE 2", Toast.LENGTH_SHORT).show();
                break;

            // Secondary Menu
            case R.id.menu_more:
                Toast.makeText(MainActivity.this, "More", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_ellipsis:
                Toast.makeText(MainActivity.this, "Ellipsis", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_more2:
                Toast.makeText(MainActivity.this, "More 2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
