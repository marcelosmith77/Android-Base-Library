package com.marcelosmith77.android.baselibrary.converter;

import android.databinding.InverseMethod;
import android.graphics.Color;
import android.widget.TextView;

import com.marcelosmith77.android.baselibrary.DateTimeUtils;
import com.marcelosmith77.android.baselibrary.formatter.CurrencyFormatter;

import java.util.Date;

public class DataTypeConverters {

    /**
     * Converter string to double
     * @param view
     * @param oldValue
     * @param value
     * @return
     */
    public static double fromCurrencyString(TextView view, double oldValue, String value) {

        try {
            return CurrencyFormatter.currencyStringToDouble(value);
        } catch (Exception e) {
            return oldValue;
        }
    }

    @InverseMethod("fromCurrencyString")
    public static String toCurrencyString(TextView view, double oldValue, double value) {

        try {
            // Don't return a different value if the parsed value
            // doesn't change
            String inView = view.getText().toString();
            double parsed = CurrencyFormatter.currencyStringToDouble(inView);

            if (parsed  == value) {
                return view.getText().toString();
            }

        } catch (Exception e) {
            // Old number was broken
        }

        return CurrencyFormatter.currencyDoubleToString(value);
    }

    /**
     * Usados nas listas onde não é feito o bind de escrita ****
     * @param value
     * @return
     */
    public static String toCurrencyString(double value) {
        return CurrencyFormatter.currencyDoubleToString(value);
    }

    /**
     * Conversão int -> String
     * @param value
     * @return
     */
    @InverseMethod("toInt")
    public static String toString(int value) {
        return String.valueOf(value);
    }

    /**
     * Conversão String -> int
     * @param value
     * @return
     */
    public static int toInt(String value) {
        return Integer.valueOf(value);
    }

    /**
     * Conversão HEX color to int
     * @param value
     * @return
     */
    @InverseMethod("toHexColor")
    public static int toColor(String value) {
        return Color.parseColor(value);
    }

    /**
     * Conversão int to HEX color
     * @param value
     * @return
     */
    public static String toHexColor(int value) {
        return String.format("#%06X", (0xFFFFFF & value));
    }

    /**
     * Recupera a hora formatada a partir do objeto Date
     * @param date
     * @return
     */
    public static String toTime(Date date) {
        return DateTimeUtils.hora(date);
    }


    /**
     * Recupera a data formatada a partir do objeto Date
     * @param date
     * @return
     */
    public static String toShortDate(Date date) {
        return DateTimeUtils.shortDate(date);
    }
}
