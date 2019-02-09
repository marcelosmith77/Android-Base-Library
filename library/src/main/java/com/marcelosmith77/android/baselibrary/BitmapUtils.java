package com.marcelosmith77.android.baselibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapUtils {

    public static final int FLIP_NONE = 0;
    public static final int FLIP_VERTICAL = 1;
    public static final int FLIP_HORIZONTAL = 2;

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

    public static Bitmap flipImage(Bitmap src, float angle, int type) {
        // create new matrix for transformation
        Matrix matrix = new Matrix();

        matrix.postRotate(angle);

        // if vertical
        if (type == FLIP_VERTICAL) {
            // y = y * -1
            matrix.preScale(1.0f, -1.0f);
        }
        // if horizonal
        else if (type == FLIP_HORIZONTAL) {
            // x = x * -1
            matrix.preScale(-1.0f, 1.0f);
            // unknown type
        } else if (type != FLIP_NONE) {
            return null;
        }

        // return transformed image
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    /**
     * This method returns a bitmap related to resource id. It is ready to use method, you can
     * use it by simply copying in your project.
     *
     * @param context    Context of calling activity
     * @param drawableId Resource ID of bitmap drawable
     * @return Bitmap whose resource id was passed to method.
     */
    public static Bitmap getBitmapFromDrawableId(Context context, int drawableId) {
        Bitmap bitmap = null;
        try {
            BitmapDrawable drawable = (BitmapDrawable) ContextCompat.getDrawable(context, drawableId);
            bitmap = drawable.getBitmap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * This method returns a bitmap related to drawable. It is ready to use method, you can
     * use it by simply copying in your project.
     *
     * @param drawable Drawable resource of image
     * @return Bitmap whose resource id was passed to method.
     */
    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmap = null;
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            bitmap = bitmapDrawable.getBitmap();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
