package com.marcelosmith77.android.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
import androidx.databinding.adapters.ListenerUtil;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marcelosmith77.android.baselibrary.R;

/**
 * View to allow the selection of a numeric value by pressing plus/minus buttons.  Pressing and holding
 * a button will update the value repeatedly.
 * <p>
 * This view can be configured with a minimum and maximum value.  There is also a label that will
 * display below the current value.
 * </p>
 *
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = ValueSelector.class, attribute = "value", method = "getValue"),
})
public class ValueSelector extends RelativeLayout {


    private int minValue = 0;
    private int maxValue = 100;
    private int value = 0;

    private boolean plusButtonIsPressed = false;
    private boolean minusButtonIsPressed = false;
    private final long REPEAT_INTERVAL_MS = 100l;
    private OnValueChangeListener listener = null;

    View rootView;
    TextView valueTextView;
    View minusButton;
    View plusButton;

    Handler handler = new Handler();

    public ValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ValueSelector(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * Get the current minimum value that is allowed
     *
     * @return
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Set the minimum value that will be allowed
     *
     * @param minValue
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;

        invalidate();
        requestLayout();
    }

    /**
     * Get the current maximum value that is allowed
     *
     * @return
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Set the maximum value that will be allowed
     *
     * @param maxValue
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;

        invalidate();
        requestLayout();
    }

    /**
     * Get the current value
     *
     * @return the current value
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the current value.  If the passed in value exceeds the current min or max, the value
     * will be set to the respective min/max.
     *
     * @param newValue new value
     */
    public void setValue(int newValue) {
        value = newValue;
        if(newValue < minValue) {
            value = minValue;
        } else if (newValue > maxValue) {
            value = maxValue;
        }

        valueTextView.setText(String.valueOf(value));

        if (listener != null) {
            listener.onValueChange(this, value);
        }
    }

    private void init(Context context, AttributeSet attrs) {

        //read xml attributes
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ValueSelector, 0, 0);
        minValue = ta.getInt(R.styleable.ValueSelector_minValue, 0);
        maxValue = ta.getInt(R.styleable.ValueSelector_maxValue, 100);
        value = ta.getInt(R.styleable.ValueSelector_value, 0);

        rootView = inflate(context, R.layout.value_selector_layout, this);
        valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);

        minusButton = rootView.findViewById(R.id.minusButton);
        plusButton = rootView.findViewById(R.id.plusButton);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
            }
        });
        minusButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View arg0) {
                        minusButtonIsPressed = true;
                        handler.post(new AutoDecrementer());
                        return false;
                    }
                }
        );
        minusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)) {
                    minusButtonIsPressed = false;
                }
                return false;
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
            }
        });
        plusButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View arg0) {
                        plusButtonIsPressed = true;
                        handler.post(new AutoIncrementer());
                        return false;
                    }
                }
        );

        plusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)) {
                    plusButtonIsPressed = false;
                }
                return false;
            }
        });

        setValue(value);

        ta.recycle();
    }

    private void incrementValue() {
        setValue(++value);
    }

    private void decrementValue() {
        setValue(--value);
    }

    private class AutoIncrementer implements Runnable {
        @Override
        public void run() {
            if(plusButtonIsPressed){
                incrementValue();
                handler.postDelayed( new AutoIncrementer(), REPEAT_INTERVAL_MS);
            }
        }
    }
    private class AutoDecrementer implements Runnable {
        @Override
        public void run() {
            if(minusButtonIsPressed){
                decrementValue();
                handler.postDelayed(new AutoDecrementer(), REPEAT_INTERVAL_MS);
            }
        }
    }

    public void addListener(OnValueChangeListener listener) {
        this.listener = listener;
    }
    public void removeListener(OnValueChangeListener listener) {
        this.listener = null;
    }

    public interface OnValueChangeListener {
        void onValueChange(ValueSelector view, int value);
    }

    @InverseBindingAdapter(attribute = "value")
    public static int getValue(ValueSelector view) {
        return view.getValue();
    }

    @BindingAdapter("value")
    public static void setValue(ValueSelector view, int value) {
        if (value != view.getValue()) {
            view.setValue(value);
        }
    }

    @BindingAdapter(value = {"onValueChange", "valueAttrChanged"}, requireAll = false)
    public static void setListeners(ValueSelector view,
                                    final OnValueChangeListener onValueChangeListener,
                                    final InverseBindingListener inverseBindingListener) {
        ValueSelector.OnValueChangeListener newListener;

        if (inverseBindingListener == null) {
            newListener = onValueChangeListener;
        } else {
            newListener = new OnValueChangeListener() {
                @Override
                public void onValueChange(ValueSelector valueSelector,
                                          int newValue) {
                    if (onValueChangeListener != null) {
                        onValueChangeListener.onValueChange(valueSelector,
                                newValue);
                    }
                    inverseBindingListener.onChange();
                }
            };
        }


        ValueSelector.OnValueChangeListener oldListener = ListenerUtil.trackListener(view, newListener, R.id.onValueChangeListener);

        if (oldListener != null) {
            view.removeListener(oldListener);
        }
        if (newListener != null) {
            view.addListener(newListener);
        }
    }

}
