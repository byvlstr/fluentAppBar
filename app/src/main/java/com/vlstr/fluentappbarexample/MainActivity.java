package com.vlstr.fluentappbarexample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resId = (int) v.getTag();
                switch (resId){
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
        };
        MenuSecondaryItemsAdapter adapter = new MenuSecondaryItemsAdapter(this, R.menu.fluent_app_bar_secondary_menu, onClickListener);
        recyclerView.setAdapter(adapter);

        Drawable d = getResources().getDrawable(R.drawable.ellipsis1);
        String yo = "Amazingness";
        fluentAppBar.setMainMenuItemsContent(yo, d, yo, d, yo, d, yo, d);
    }
}
