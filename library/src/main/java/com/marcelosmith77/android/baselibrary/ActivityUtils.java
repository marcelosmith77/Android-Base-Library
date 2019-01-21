package com.marcelosmith77.android.baselibrary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
     * Show alert dialog
     *
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message resource id
     */
    public static void showAlert(Context context, int title, int message) {

        showAlert(context, title, message, null);
    }

    /**
     * Show alert dialog
     *
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message resource id
     * @param onClickListener - on OK button click listener
     */
    @SuppressWarnings("WeakerAccess")
    public static void showAlert(Context context, int title, int message, DialogInterface.OnClickListener onClickListener) {

        if (context == null)
            return;

        AlertDialog dialog = new AlertDialog.Builder(context, R.style.DialogTheme)
                .setIcon(R.drawable.ic_alert)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, onClickListener != null ? onClickListener : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        dialog.show();
    }

    /**
     * Show confirmation dialog
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message resource id
     * @param yesListener - Positive button click listener
     * @param noListener - Negative button click listener
     */
    public static void showConfirm(Context context, int title, int message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirm(context, title, message, R.string.yes, R.string.no, yesListener, noListener);
    }

    /**
     * Show confirmation dialog
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message resource id
     * @param positiveButton - Positive button title
     * @param negativeButton - Negative button title
     * @param yesListener - Positive button click listener
     * @param noListener - Negative button click listener
     */
    @SuppressWarnings("WeakerAccess")
    public static void showConfirm(Context context, @StringRes int title, @StringRes int positiveButton, @StringRes int negativeButton, int message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {

        if (context == null)
            return;

        AlertDialog dialog = new AlertDialog.Builder(context, R.style.DialogTheme)
                .setIcon(R.drawable.ic_alert)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButton, yesListener != null ? yesListener : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(negativeButton, noListener != null ? noListener : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        dialog.show();
    }

    /**
     * Show confirmation dialog
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message Text
     * @param yesListener - Positive button click listener
     * @param noListener - Negative button click listener
     */
    public static void showConfirm(Context context, String title, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirm(context, title, message, R.string.yes, R.string.no, yesListener, noListener);
    }

    /**
     * Show confirmation dialog
     * @param context - Activity context
     * @param title - title resource id
     * @param message - message resource id
     * @param positiveButton - Positive button title
     * @param negativeButton - Negative button title
     * @param yesListener - Positive button click listener
     * @param noListener - Negative button click listener
     */
    @SuppressWarnings("WeakerAccess")
    public static void showConfirm(Context context, String title, String message, @StringRes int positiveButton, @StringRes int negativeButton, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {

        if (context == null)
            return;

        AlertDialog dialog = new AlertDialog.Builder(context, R.style.DialogTheme)
                .setIcon(R.drawable.ic_alert)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButton, yesListener != null ? yesListener : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(negativeButton, noListener != null ? noListener : new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();

        dialog.show();
    }

    public static void dismissWithCheck(Dialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing()) {

                //get the Context object that was used to great the dialog
                Context context = ((ContextWrapper) dialog.getContext()).getBaseContext();

                // if the Context used here was an activity AND it hasn't been finished or destroyed
                // then dismiss it
                if (context instanceof Activity) {

                    // Api >=17
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                            dismissWithTryCatch(dialog);
                        }
                    } else {

                        // Api < 17. Unfortunately cannot check for isDestroyed()
                        if (!((Activity) context).isFinishing()) {
                            dismissWithTryCatch(dialog);
                        }
                    }
                } else
                    // if the Context used wasn't an Activity, then dismiss it too
                    dismissWithTryCatch(dialog);
            }
            dialog = null;
        }
    }

    public static void dismissWithTryCatch(Dialog dialog) {
        try {
            dialog.dismiss();
        } catch (final IllegalArgumentException e) {
            // Do nothing.
        } catch (final Exception e) {
            // Do nothing.
        } finally {
            dialog = null;
        }
    }
}

