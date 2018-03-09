package com.gmail.deal10041.restaurant;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dylan Wellner on 9-3-2018.
 * Implements a adapter for categories
 */

public class CategoryAdapter extends ArrayAdapter<String> {

    private ArrayList<String> categories;

    public CategoryAdapter(@NonNull Context context, int resource, ArrayList<String> categories) {
        super(context, resource, categories);
        this.categories = categories;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category, parent, false);
        }

        // load in Comic Sans
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/Comic_Sans.ttf");

        // set text
        TextView textView = convertView.findViewById(R.id.category);
        textView.setText(categories.get(position));
        textView.setTypeface(typeface, Typeface.BOLD);

        // alternate background colors
        if(position % 2 == 1) {
            convertView.setBackgroundColor(0xFFebebe0);
        }

        return convertView;
    }
}
