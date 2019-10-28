package com.marcelosmith77.android.baselibrary.adapter.enumeration;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.marcelosmith77.android.baselibrary.BR;
import com.marcelosmith77.android.baselibrary.R;

public abstract class EnumerationSpinnerAdapter<T extends IEnumerationAdapter> extends ArrayAdapter<T> {
    Context mContext;
    int mLayoutResourceId;
    T[] mItems;

    public EnumerationSpinnerAdapter(Context context,  T[] data) {
        this(context, R.layout.spinner_layout, data);
    }

    public EnumerationSpinnerAdapter(Context context, int layoutResourceId, T[] data) {
        super(context, layoutResourceId, data);
        this.mLayoutResourceId = layoutResourceId;
        this.mContext = context;
        this.mItems = data;
    }


    public abstract int getPosition(String value);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;

        if (row == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, mLayoutResourceId, parent, false);

            row = binding.getRoot();

            holder = new Holder(binding);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        holder.bind(mItems[position]);
        return row;
    }

    static class Holder {
        private ViewDataBinding binding;

        public Holder(ViewDataBinding binding){
            this.binding = binding;
        }

        public void bind(IEnumerationAdapter model) {
            this.binding.setVariable(BR.viewModel, model);
            this.binding.executePendingBindings();
        }
    }
}
