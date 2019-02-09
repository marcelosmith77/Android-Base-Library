package com.marcelosmith77.android.baselibrary.mvvm.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.marcelosmith77.android.baselibrary.activity.INavigator;
import com.marcelosmith77.android.baselibrary.adapter.recyclerview.BaseRecyclerViewAdapter;
import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;

public class MvvmListViewModel<T, V extends MvvmView>  extends MvvmViewModel<T, V> {

    /**
     * Adapter para telas de listagem
     */
    private BaseRecyclerViewAdapter adapter;

    public MvvmListViewModel(INavigator navigator, BaseRecyclerViewAdapter adapter) {
        super(navigator);

        this.navigator = navigator;
        this.adapter = adapter;
    }

    @Override
    public T getModel() {
        throw new RuntimeException("Model no available for listview model type, use adapter.getItems() instead.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void attachMvvmView(V view, @Nullable Bundle savedInstanceState) {
        super.attachMvvmView(view, savedInstanceState);

        if (adapter != null)
            adapter.attachMvvmView(view);
    }


    @Override
    public void detachMvvmView() {
        super.detachMvvmView();

        if (adapter != null)
            adapter.detachMvvmView();
    }

    public BaseRecyclerViewAdapter getAdapter() {
        return adapter;
    }
}