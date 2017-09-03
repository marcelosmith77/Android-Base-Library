package com.marcelosmith77.android.baselibrary;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SnackbarUtils {

    private int BACKGROUND_COLOR;
    private int TEXT_COLOR;
    private int BUTTON_COLOR;
    private String TEXT;


    public SnackbarUtils(String aText, int aBgColor, int aTextColor, int aButtonColor){
        this.TEXT = aText;
        this.BACKGROUND_COLOR = aBgColor;
        this.TEXT_COLOR = aTextColor;
        this.BUTTON_COLOR = aButtonColor;
    }

    public Snackbar createSnackbar(CoordinatorLayout coordinatorLayout, int duration){
        Snackbar snackie = Snackbar.make(coordinatorLayout, TEXT, duration);
        View snackView = snackie.getView();
        TextView snackViewText = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        Button snackViewButton = (Button) snackView.findViewById(android.support.design.R.id.snackbar_action);
        snackView.setBackgroundColor(BACKGROUND_COLOR);
        snackViewText.setTextColor(TEXT_COLOR);
        snackViewButton.setTextColor(BUTTON_COLOR);
        return snackie;
    }
}
