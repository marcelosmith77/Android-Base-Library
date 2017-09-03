package com.marcelosmith77.android.baselibrary;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

public class ActivityUtils {

    /**
     * Tries to cast an Activity Context to another type
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T castActivityFromContext(Context context, Class<T> castClass) {
        if(castClass.isInstance(context)) {
            return (T) context;
        }

        while(context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();

            if(castClass.isInstance(context)) {
                return (T) context;
            }
        }

        return null;
    }

    /**
     * Exibe alerta
     *
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message resource id
     * @param onClickListener - on OK button click listener
     */
    public static void showAlert(Context context, int title, int message, DialogInterface.OnClickListener onClickListener) {

        if (context == null)
            return;

        AlertDialog dialog = new AlertDialog.Builder(context, R.style.DialogTheme)
                .setIcon(R.drawable.ic_alert)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, onClickListener)
                .create();

        dialog.show();
    }
}

