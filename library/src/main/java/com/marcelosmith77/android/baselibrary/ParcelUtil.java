package com.marcelosmith77.android.baselibrary;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.parceler.Parcels;

public class ParcelUtil {

    /* Gets a Parcelable wrapped with Parceler from a Bundle and returns null
     * if the bundle does not contain a value for key. */
    @Nullable
    public static <T> T getParcelable(@NonNull Bundle bundle, @NonNull String key) {
        //noinspection ConstantConditions
        return getParcelable(bundle, key, null);
    }

    /* Gets a Parcelable wrapped with Parceler from a Bundle and returns defaultObject
     * if the bundle does not contain a value for key. */
    @NonNull
    public static <T> T getParcelable(@NonNull Bundle bundle, @NonNull String key, @NonNull T defaultObject) {
        if(bundle != null && bundle.containsKey(key)) {
            return Parcels.unwrap(bundle.getParcelable(key));
        } else {
            return defaultObject;
        }
    }
}
