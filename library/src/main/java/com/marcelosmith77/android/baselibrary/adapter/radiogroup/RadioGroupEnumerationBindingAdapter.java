package com.marcelosmith77.android.baselibrary.adapter.radiogroup;

import android.databinding.InverseBindingListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public abstract class RadioGroupEnumerationBindingAdapter<E extends Enum, T> {

    protected abstract E getEnum(T value);

    protected void setCheckedButton(RadioGroup view, T oldValue, T newValue) {

        if (newValue != null && !newValue.equals(oldValue)) {
            RadioButton newRadioSelected = view.findViewWithTag(getEnum(newValue).name());
            view.check(newRadioSelected.getId());
        }
    }

    protected String getCheckedButtonTag(RadioGroup view) {
        View radioButton = view.findViewById(view.getCheckedRadioButtonId());

        if (radioButton != null) {
            return radioButton.getTag().toString();
        }
        return null;
    }

    protected void setListeners(RadioGroup view, final OnCheckedChangeListener listener,
                                final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnCheckedChangeListener(listener);
        } else {
            view.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (listener != null) {
                        listener.onCheckedChanged(group, checkedId);
                    }

                    attrChange.onChange();
                }
            });
        }
    }
}
