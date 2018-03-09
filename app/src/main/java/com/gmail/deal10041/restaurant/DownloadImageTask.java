package com.gmail.deal10041.restaurant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Dylan Wellner on 8-3-2018.
 * Implements a helper class for downloading images from API
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    DownloadImageTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        String imageUrl = strings[0];
        Bitmap image = null;

        // download image
        try {
            InputStream is = new URL(imageUrl). openStream();
            image = BitmapFactory.decodeStream(is);
        } catch(Exception e) {
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
