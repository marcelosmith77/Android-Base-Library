package com.marcelosmith77.android.baselibrary.mvvm.viewholder;

import androidx.databinding.ViewDataBinding;

import com.marcelosmith77.android.baselibrary.BR;
import com.marcelosmith77.android.baselibrary.adapter.recyclerview.BaseRecyclerViewHolder;
import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;
import com.marcelosmith77.android.baselibrary.mvvm.viewmodel.MvvmObservableViewModel;

public abstract class MvvmViewHolder<V extends MvvmView, T extends MvvmObservableViewModel<M, V>,M> extends BaseRecyclerViewHolder<M> {

    private final V view;

    public MvvmViewHolder(ViewDataBinding binding, V view) {
        super(binding);
        this.view = view;
    }

    @Override
    public void bind(M model) {

        getViewModel().setModel((M) model);
        getViewModel().attachMvvmView(view, null);

        binding.setVariable(BR.viewModel, getViewModel());
        binding.executePendingBindings();
    }

    protected abstract T getViewModel();
}
