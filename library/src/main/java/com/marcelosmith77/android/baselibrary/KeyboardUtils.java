package com.marcelosmith77.android.baselibrary;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtils {

    /**
     * Fecha o teclado virtual do android
     *
     * @param ctx
     */
    public static void hideKeyboard(Context ctx) {

        if (ctx == null)
            return;

        InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Força o foco no componente.
     *
     * @param view
     * @param context
     */
    public static void forceFocus(final EditText view, final Context context) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        view.requestFocusFromTouch();
    }
}
