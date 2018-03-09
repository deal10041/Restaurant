package com.gmail.deal10041.restaurant;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Dylan Wellner on 6-3-2018.
 * Implements an adapter for menu items
 */

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuItems;

    public MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> menuItems) {
        super(context, resource, menuItems);
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        // set image
        ImageView imageView = convertView.findViewById(R.id.image);
        new DownloadImageTask(imageView).execute(menuItems.get(position).getImageUrl());

        // load in Comic Sans
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/Comic_Sans.ttf");

        // set name
        TextView name = convertView.findViewById(R.id.name);
        name.setText(menuItems.get(position).getName());
        name.setTypeface(typeface, Typeface.BOLD);

        // set price
        TextView price = convertView.findViewById(R.id.price);
        price.setText("€ " + String.format("%.2f", menuItems.get(position).getPrice()));
        price.setTypeface(typeface);

        // dynamically set row height of grid view
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int itemHeight = screenHeight > screenWidth ? screenHeight / 7 : screenWidth / 7;
        convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, itemHeight));

        // alternates background colors
        if(position % 2 == 1) {
            convertView.setBackgroundColor(0xFFebebe0);
        }

        return convertView;
    }
}
