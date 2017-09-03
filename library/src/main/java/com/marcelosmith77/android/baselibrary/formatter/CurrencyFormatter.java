package com.marcelosmith77.android.baselibrary.formatter;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

//////////////////////////////////////////////////////////////////////////////////
//Currency Format Manipulation with user input
// class which formats what user is entering into currency for their locale
// provides methods for changing format form string to int and then to double and then
// back to string
public class CurrencyFormatter {

    private static final String TAG = "CurrencyFormatter";

    public CurrencyFormatter() {

    }

    //Returns a textwatcher which automatically formats input into currency
    // eg user enters 1 = £0.01
    // user enters 1 again = £0.11
    // 1 again = £1.11
    public static TextWatcher getCurrencyInputTextWatcher(final EditText etNumOfTables) {
        TextWatcher textWatcher = new TextWatcher() {
            private String current = "";

            // allows user to enter currency value, shows fixed symbol and decimal
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals(current)) {
                    etNumOfTables.removeTextChangedListener(this);
                    // strip off the currency symbol
                    String replaceable = String.format("[%s,.\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    double parsed = (cleanString != null && !"".equals(cleanString.trim()) ? Double.parseDouble(cleanString.trim()) : 0D);

                    // format the double into a currency format
                    String formated = NumberFormat.getCurrencyInstance().format((parsed / 100));

                    current = formated;
                    etNumOfTables.setText(formated);
                    etNumOfTables.setSelection(formated.length());

                    etNumOfTables.addTextChangedListener(this);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        return textWatcher;
    }

    // returns currency represented in a string format into an int
    // used for storing value in db
    public static int currencyStringIntoInteger(String currencyString) {

        String replaceable = String.format("[%s,.\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
        String cleanString = currencyString.toString().replaceAll(replaceable, "");

        /*
        currencyString = currencyString.replaceAll("\\.", "");
        currencyString = currencyString.replaceAll("\\,", "");
        currencyString = currencyString.replaceAll(escapeCurrencySymbol(NumberFormat.getCurrencyInstance().getCurrency().getSymbol()), "");
        currencyString = currencyString.trim();
        int currencyInt = Integer.parseInt(cleanString);
        */

        int currencyInt = (cleanString != null && !"".equals(cleanString.trim()) ? Integer.parseInt(cleanString.trim()) : 0);

        Log.i(TAG, "Input string = " + currencyString);

        return currencyInt;
    }

    private static String escapeCurrencySymbol(String symbol) {
        symbol = symbol.replaceAll("\\$", "\\\\\\$");
        return symbol;
    }

    // returns currency represented in a int format into an double
    public static double currencyIntToDouble(int currencyInt) {
        Currency c = Currency.getInstance(Locale.getDefault());
        // calculate how many digits the decimal point needs to be by dividing
        // the result to the power of 10, 1 digit = 10^1 =1, 2 digit = 10^2 =
        // 100
        double decimalPointPlaceCalculation = Math.pow(10,
                c.getDefaultFractionDigits());
        // divides by a number to place the decimal point in orrect position
        // depending on the locale
        double inputDouble = (double) currencyInt
                / decimalPointPlaceCalculation;
        return inputDouble;
    }

    public static String currencyDoubleToString(double currencyDouble) {
        String currencyString = NumberFormat.getCurrencyInstance()
                .format(currencyDouble);
        return currencyString;
    }

    public static int currencyDoubleToInt(double currencyDouble) {
        //0.74
        Currency c = Currency.getInstance(Locale.getDefault());

        int decimalPointPlaceCalculation = (int) Math.pow(10,
                c.getDefaultFractionDigits());

        double currencyInt = currencyDouble * decimalPointPlaceCalculation;
        return (int) currencyInt;
    }

    public static double currencyStringToDouble(String currencyString) {
        int currencyInt = currencyStringIntoInteger(currencyString);
        double currencyDouble = currencyIntToDouble(currencyInt);
        return currencyDouble;
    }

    // Used for getting int value from db and return string value in currency format e.g. 1230 = £12.30
    public static String currencyIntToString(int currencyInt) {
        double currencyDouble = currencyIntToDouble(currencyInt);
        String currencyString = currencyDoubleToString(currencyDouble);
        return currencyString;
    }

}