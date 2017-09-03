package com.marcelosmith77.android.baselibrary.adapter.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.marcelosmith77.android.baselibrary.BR;

public abstract class BaseRecyclerViewHolder<M> extends RecyclerView.ViewHolder {

    private final ViewDataBinding binding;

    public BaseRecyclerViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bind(M model) {
        binding.setVariable(BR.viewModel, model);
        binding.executePendingBindings();
    }
}