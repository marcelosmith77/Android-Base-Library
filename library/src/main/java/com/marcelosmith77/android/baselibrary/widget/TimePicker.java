package com.marcelosmith77.android.baselibrary.widget;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

import com.marcelosmith77.android.baselibrary.R;

public class TimePicker extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener listener;
    final public static String PARAM_HOUR = "PARAM_HOUR";
    final public static String TIMER_PICKER_FRAGMENT_NAME = "timePicker";

    public TimePicker() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Long when = getArguments().getLong(PARAM_HOUR);

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();

        if (when != null) {
            c.setTime(new Date(when));
        }

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), R.style.DialogTheme, getListener(), hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }

    public TimePickerDialog.OnTimeSetListener getListener() {
        return this.listener;
    }
}