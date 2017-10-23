package com.vlstr.fluentappbarexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.vlstr.fluentappbar.FluentAppBar;

import net.hockeyapp.android.CrashManager;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FluentAppBar fluentAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPhotosRecyclerView();
        setupFluentAppBar();
    }

    // TERRIBLE CODE BUT I COULDN'T BE BOTHERED FOR THIS EXAMPLE ADAPTER. SORRY...
    private void setupPhotosRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new RecyclerView.Adapter<PhotoHolder>() {

            @Override
            public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = getLayoutInflater().inflate(R.layout.item_photo, parent, false);
                return new PhotoHolder(view);
            }

            @Override
            public void onBindViewHolder(PhotoHolder holder, int position) {
                switch (position) {
                    case 0:
                        holder.photo.setImageBitmap(getSmallerImage(R.drawable.fuerte));
                        break;
                    case 1:
                        holder.photo.setImageBitmap(getSmallerImage(R.drawable.buddy));
                        break;
                    case 2:
                        holder.photo.setImageBitmap(getSmallerImage(R.drawable.lights1));
                        break;
                    case 3:
                        holder.photo.setImageBitmap(getSmallerImage(R.drawable.lights2));
                        break;
                    case 4:
                        holder.photo.setImageBitmap(getSmallerImage(R.drawable.sunset));
                        break;
                    case 5:
                        holder.photo.setImageBitmap(getSmallerImage(R.drawable.vulcan));
                        break;
                }
            }

            @Override
            public int getItemCount() {
                return 6;
            }
        });

        //recyclerView.setVisibility(GONE);
    }

    private void setupFluentAppBar() {
        fluentAppBar = (FluentAppBar) findViewById(R.id.fluent_app_bar);
        fluentAppBar.setNavigationMenu(R.menu.fluent_app_bar_main_menu, this);
        fluentAppBar.setSecondaryMenu(R.menu.fluent_app_bar_secondary_menu, this);
        fluentAppBar.setBlurRadius(10);
    }

    @Override
    public void onClick(View v) {
        int resId = (int) v.getTag();
        switch (resId){
            //Navigation Menu
            case R.id.nav_all:
                fluentAppBar.collapse();
                break;
            case R.id.nav_album:
                break;
            case R.id.nav_keywords:
                break;

            // Secondary Menu
            case R.id.menu_sync:
                fluentAppBar.collapse();
                break;
            case R.id.menu_assistant:
                break;
            case R.id.menu_shared:
                break;
        }
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fluent_app_bar_main_menu, menu);
        return true;
    }

    private Bitmap getSmallerImage(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        int newHeight = (int)(bitmap.getHeight()*( 1000.0 / bitmap.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap,1000,newHeight,true);
        return scaled;
    }
}
