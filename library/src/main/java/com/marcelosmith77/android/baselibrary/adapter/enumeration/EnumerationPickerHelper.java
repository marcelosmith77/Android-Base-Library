package com.marcelosmith77.android.baselibrary.adapter.enumeration;


import android.content.Context;
import android.widget.NumberPicker;


/**
 * Helper para popular os valores de NumberPicker baseados em um enumeration
 * @param <T>
 */
public class EnumerationPickerHelper<T extends Enum<T>> {

    public void setDisplayedValues(Context context, NumberPicker picker, Enum<T> enumValues[]) {
        String[] values = new String[enumValues.length];

        int index = 0;

        for (Enum<T> p : enumValues) {
            values[index++] = ((IEnumerationAdapter) p).getLabel();
        }

        picker.setDisplayedValues(values);
        picker.setMinValue(0);
        picker.setMaxValue(index - 1);
    }
}