package com.marcelosmith77.android.baselibrary.activity;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;

import com.marcelosmith77.android.easydrawer.activity.AbstractBaseDrawerActivity;

/**
 * Expõe metodos para navegação entre fragmentos e activities
 *
 * Permite substituir um fragmento adicionando ou não o mesmo a pilha de navegação.
 *
 * @todo Se trocar implementacao do navigator pela compatactivity, desta forma,
 * o navigatior fica independente da implementacao do drawer, sendo possível utilizando em qualquer acitivy
 */
public class ActivityNavigator implements INavigator {

    private AbstractBaseDrawerActivity activity;

    public ActivityNavigator(AbstractBaseDrawerActivity activity) {
        this.activity = activity;
    }

    @Override
    public void showFrament(Fragment f) {
        activity.showFrament(f);
    }

    @Override
    public void showFrament(Fragment f, String name) {
        activity.showFrament(f, name);
    }

    @Override
    public void showFrament(Fragment f, boolean addToStack, boolean clearStack) {
        activity.showFrament(f, addToStack, clearStack);
    }

    @Override
    public void showFrament(Fragment f, boolean addToStack, boolean clearStack, Bundle args) {
        activity.showFrament(f, addToStack, clearStack, args);
    }

    @Override
    public void showFrament(Fragment f, boolean addToStack, boolean clearStack, String key, Parcelable parcelable) {
        activity.showFrament(f, addToStack, clearStack, key, parcelable);
    }

    @Override
    public void showFrament(Fragment f, String name, boolean addToStack, boolean clearStack) {
        activity.showFrament(f, name, addToStack, clearStack);
    }

    @Override
    public void showFrament(Fragment f, String name, boolean addToStack, boolean clearStack, Bundle args) {
        activity.showFrament(f, name, addToStack, clearStack, args);
    }

    @Override
    public void back() {
        activity.onBackPressed();
    }
}
