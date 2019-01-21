package com.marcelosmith77.android.baselibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapUtils {

    private BitmapUtils(){}

    /**
     * Realiza o download de uma imagem e retorna um Objeto Bitmap
     *
     * @param imageUrl
     * @return Bitmap - Bitmap da imagem baixada
     */
    public static Bitmap getBitmapFromURL(String imageUrl) {
        InputStream inputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // IGNORE
                }
            }

            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    // IGNORE
                }
            }
        }
    }
}
