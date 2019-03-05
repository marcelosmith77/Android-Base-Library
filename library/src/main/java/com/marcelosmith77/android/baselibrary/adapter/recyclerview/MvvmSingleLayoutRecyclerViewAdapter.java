package com.marcelosmith77.android.baselibrary.adapter.recyclerview;

import android.support.annotation.LayoutRes;

import java.util.ArrayList;
import java.util.List;

import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;

/**
 * Adapter de um único layout para toda a lista
 */
public abstract class MvvmSingleLayoutRecyclerViewAdapter<VH extends BaseRecyclerViewHolder,M, V extends MvvmView> extends BaseRecyclerViewAdapter<VH, M, V> {
    private final int layoutId;
    private List<M> items;

    public MvvmSingleLayoutRecyclerViewAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        this.items = new ArrayList<>();
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setItems(List<M> items) {
        this.items = items;
    }

    @Override
    public List<M> getItems() {
        return items;
    }
}