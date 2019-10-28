package com.marcelosmith77.android.baselibrary;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import androidx.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;

public class DialogUtils {

    public static void showConfirm(Context context, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback, MaterialDialog.SingleButtonCallback negativeCallback) {

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_question)
                .title(R.string.attention)
                .content(content)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(positiveCallback).build();

        dialog.show();
    }

    public static void showConfirm(Context context, String content, MaterialDialog.SingleButtonCallback positiveCallback, MaterialDialog.SingleButtonCallback negativeCallback) {
        showConfirm(context, content, R.string.yes, R.string.no, positiveCallback, negativeCallback);
    }

    public static void showConfirm(Context context, String content, @StringRes int  positiveText, @StringRes int  negativeText,  MaterialDialog.SingleButtonCallback positiveCallback, MaterialDialog.SingleButtonCallback negativeCallback) {
        showConfirm(context, content, positiveText, negativeText,  positiveCallback, negativeCallback, true);
    }

    public static void showConfirm(Context context, String content, @StringRes int  positiveText, @StringRes int  negativeText,  MaterialDialog.SingleButtonCallback positiveCallback, MaterialDialog.SingleButtonCallback negativeCallback, boolean cancellable) {

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_question)
                .title(R.string.attention)
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .canceledOnTouchOutside(cancellable)
                .onPositive(positiveCallback).build();

        dialog.show();
    }

    public static void showInfo(Context context, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback) {

        showInfo(context, R.string.attention, content, positiveCallback);
    }

    public static void showInfo(Context context, @StringRes int title, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback) {
        showInfo(context, title, content, positiveCallback, true);
    }

    public static void showInfo(Context context, @StringRes int title, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback, boolean cancellable) {

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_info)
                .title(title)
                .content(content)
                .positiveText(R.string.ok)
                .canceledOnTouchOutside(cancellable)
                .onPositive(positiveCallback).build();

        dialog.show();
    }

    public static void showError(Context context, @StringRes int title, @StringRes int content) {
        showError(context, title, content, null);
    }

    public static void showError(Context context, @StringRes int content) {
        showError(context, content, null);
    }

    public static void showError(Context context, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback) {
        showError(context, R.string.attention, content, positiveCallback);
    }

    public static void showError(Context context, @StringRes int title, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback) {
        showError(context, title, content, positiveCallback, true);
    }
    public static void showError(Context context, @StringRes int title, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback, boolean cancellable) {

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_error)
                .title(title)
                .content(content)
                .positiveText(R.string.ok)
                .canceledOnTouchOutside(cancellable)
                .onPositive(positiveCallback).build();

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
