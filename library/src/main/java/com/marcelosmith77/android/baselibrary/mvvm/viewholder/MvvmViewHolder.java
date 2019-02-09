package com.marcelosmith77.android.baselibrary.mvvm.viewholder;

import android.databinding.ViewDataBinding;

import com.marcelosmith77.android.baselibrary.adapter.recyclerview.BaseRecyclerViewHolder;
import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;
import com.marcelosmith77.android.baselibrary.mvvm.viewmodel.MvvmObservableViewModel;

public abstract class MvvmViewHolder<V extends MvvmView, T extends MvvmObservableViewModel<M, V>,M> extends BaseRecyclerViewHolder {

    public MvvmViewHolder(ViewDataBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Object model) {

        getViewModel().setModel((M) model);

        super.bind(getViewModel());
    }

    protected abstract T getViewModel();
}
