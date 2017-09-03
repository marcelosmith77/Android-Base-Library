package com.marcelosmith77.android.baselibrary;

import android.content.res.Configuration;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import com.marcelosmith77.android.baselibrary.formatter.CurrencyFormatter;

public class CurrencyUtils {

    private static final int MAX_CURRENCY_LENGHT = 10;

    public static void setupCurrencyView(EditText view) {
        setupCurrencyView(view, null, MAX_CURRENCY_LENGHT);
    }

    /**
     * Formata máscara para moeda com tamanho máximo parametrizado
     * @param view
     * @param maxLength
     */
    public static void setupCurrencyView(EditText view, int maxLength) {
        setupCurrencyView(view, null, maxLength);
    }

    /**
     * Formata máscara para moeda com valor default
     * @param view
     */
    public static void setupCurrencyView(EditText view, Double value) {
        setupCurrencyView(view, value, MAX_CURRENCY_LENGHT);
    }

    /**
     * Formata máscara para moeda com tamanho máximo parametrizado e valor default pré-definido
     * @param view
     * @param maxLength
     */
    public static void setupCurrencyView(EditText view, Double value, int maxLength) {
        int symbolLength = NumberFormat.getCurrencyInstance().getCurrency().getSymbol().length();

        view.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength + symbolLength)});
        view.addTextChangedListener(CurrencyFormatter.getCurrencyInputTextWatcher(view));
        view.setRawInputType(Configuration.KEYBOARD_12KEY);

        if (value != null)
            setupCurrencyValue(view, value);
    }


    /**
     * Seta texto formatado para moeda no editText informado
     *
     * @param view
     * @param value
     */
    public static void setupCurrencyValue(EditText view, Double value) {
        if (view != null)
            view.setText(CurrencyFormatter.currencyDoubleToString(value != null ? value : 0D));
    }

    /**
     * Seta texto formatado para moeda no TextView informado
     *
     * @param view
     * @param value
     */
    public static void setupCurrencyValue(TextView view, Double value) {

        if (view != null)
            view.setText(CurrencyFormatter.currencyDoubleToString(value != null ? value : 0D));
    }

}
