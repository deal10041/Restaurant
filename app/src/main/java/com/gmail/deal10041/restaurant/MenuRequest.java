package com.gmail.deal10041.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dylan Wellner on 6-3-2018.
 * Implements a helper class for requesting menu items
 */

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface CallBack {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    private Context context;
    private MenuRequest.CallBack delegate;
    private String category;

    public MenuRequest(Context context) {
        this.context = context;
    }

    public void getMenu(MenuRequest.CallBack activity, String aCategory) {

        RequestQueue queue = Volley.newRequestQueue(context);
        delegate = activity;
        category = aCategory;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu",null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        delegate.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try {
            // get array of items
            JSONArray array = response.getJSONArray("items");
            for(int i = 0; i < array.length(); i++) {

                // select items of category
                JSONObject itemObject = array.getJSONObject(i);
                if(itemObject.getString("category").equals(category)) {

                    // fill menu item with all data
                    MenuItem currentItem = new MenuItem();
                    currentItem.setCategory(itemObject.getString("category"));
                    currentItem.setDescription(itemObject.getString("description"));
                    currentItem.setImageUrl(itemObject.getString("image_url"));
                    currentItem.setPrice(itemObject.getDouble("price"));
                    currentItem.setName(itemObject.getString("name"));

                    // add item to list
                    menuItems.add(currentItem);
                }
            }
        }
        catch(JSONException j) { System.out.println(j); }

        // pass items on to activity
        delegate.gotMenu(menuItems);
    }
}
