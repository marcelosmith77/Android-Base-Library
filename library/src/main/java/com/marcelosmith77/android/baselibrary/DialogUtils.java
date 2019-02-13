package com.marcelosmith77.android.baselibrary;


import android.content.Context;
import android.support.annotation.StringRes;

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

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_question)
                .title(R.string.attention)
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .onPositive(positiveCallback).build();

        dialog.show();
    }

    public static void showInfo(Context context, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback) {

        showInfo(context, R.string.attention, content, positiveCallback);
    }

    public static void showInfo(Context context, @StringRes int title, @StringRes int content, MaterialDialog.SingleButtonCallback positiveCallback) {

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_info)
                .title(title)
                .content(content)
                .positiveText(R.string.ok)
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

        KeyboardUtils.hideKeyboard(context);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .iconRes(R.drawable.ic_alert_error)
                .title(title)
                .content(content)
                .positiveText(R.string.ok)
                .onPositive(positiveCallback).build();

        dialog.show();
    }

}
