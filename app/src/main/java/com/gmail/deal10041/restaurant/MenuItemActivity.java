package com.gmail.deal10041.restaurant;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // set a support action bar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // set up action
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Comic_Sans.ttf");

        // fill in views
        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("menu_item");

        TextView name = findViewById(R.id.name);
        name.setText(menuItem.getName());
        name.setTypeface(typeface, Typeface.BOLD);

        ImageView image = findViewById(R.id.image);
        new DownloadImageTask(image).execute(menuItem.getImageUrl());

        TextView description = findViewById(R.id.description);
        description.setText(menuItem.getDescription());
        description.setTypeface(typeface);

        TextView price = findViewById(R.id.price);
        price.setText("€ " + String.format("%.2f", menuItem.getPrice()));
        price.setTypeface(typeface);
    }
}
