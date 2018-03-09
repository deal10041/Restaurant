package com.gmail.deal10041.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.CallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // set a support action bar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // get categories
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // instantiate adapter
        ArrayAdapter<String> adapter = new CategoryAdapter(this, R.layout.category, categories);

        // attach adapter to listview
        ListView list = findViewById(R.id.categories);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {

        // make toast
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, message, duration).show();
    }

    public class OnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get clicked category
            String category = (String) adapterView.getItemAtPosition(i);

            // launch menu activity
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
