package com.marcelosmith77.android.baselibrary;

import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.Nullable;

public class ActivityUtils {

    /**
     * Tries to cast an Activity Context to another type
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T castActivityFromContext(Context context, Class<T> castClass) {
        if(castClass.isInstance(context)) {
            return (T) context;
        }

        while(context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();

            if(castClass.isInstance(context)) {
                return (T) context;
            }
        }

        return null;
    }



}

