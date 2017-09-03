package com.marcelosmith77.android.baselibrary.mvvm.viewmodel;


import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;

/**
 * Interface para definir uma View Model
 */
public abstract class MvvmObservableViewModel<T, V extends MvvmView> extends BaseObservable {

    private T model;
    private V mvvmView;

    public void attachMvvmView(V view, @Nullable Bundle savedInstanceState) {
        this.mvvmView = view;
        if(savedInstanceState != null) { restoreInstanceState(savedInstanceState); }
    }

    public void detachMvvmView() {
        mvvmView = null;
    }

    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) { }

    public void onDestroy(){}

    public void setModel(T model) {
        this.model = model;
    }

    public T getModel() {
        return this.model;
    }

    public V getMvvmView() {
        return mvvmView;
    }
}
