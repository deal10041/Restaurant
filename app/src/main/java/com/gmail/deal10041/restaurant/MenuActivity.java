package com.gmail.deal10041.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.CallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // set a support action bar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // set up action
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // request menu
        MenuRequest request = new MenuRequest(this);
        Intent intent = getIntent();
        request.getMenu(this, intent.getStringExtra("category"));
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {

        // set new list adapter
        ListView list = findViewById(R.id.menuItems);
        list.setAdapter(new MenuItemAdapter(this, R.layout.menu_item, menuItems));
        list.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {

        // make toast
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast.makeText(context, message, duration).show();
    }

    public class OnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get clicked menu item
            MenuItem menuItem = (MenuItem) adapterView.getItemAtPosition(i);

            // launch menu activity
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menu_item", menuItem);
            startActivity(intent);
        }
    }
}
