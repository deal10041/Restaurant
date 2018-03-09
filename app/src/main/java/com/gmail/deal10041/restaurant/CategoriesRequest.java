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
 * Implements a helper class for requesting categories
 */

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface CallBack {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    private Context context;
    private CallBack delegate;

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public void getCategories(CallBack activity) {

        // make new request for data
        RequestQueue queue = Volley.newRequestQueue(context);
        delegate = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories",null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // print error message thrown
        delegate.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response){

        ArrayList<String> categories = new ArrayList<>();

        try {
            // get array of categories
            JSONArray array = response.getJSONArray("categories");

            // fill array list with categories
            for (int i = 0; i < array.length(); i++) {
                categories.add(array.getString(i));
            }
        }
        catch (JSONException j) {System.out.println(j.getMessage());}

        // pass categories to activity
        delegate.gotCategories(categories);
    }
}
