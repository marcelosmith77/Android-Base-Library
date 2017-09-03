package com.marcelosmith77.android.baselibrary;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.marcelosmith77.android.baselibrary.animation.DefaultAnimationListener;

public class AnimationUtils {

    private AnimationUtils() {}

    public static void setFadeInAnimation(final View view, int duration) {

        setFadeInAnimation(view, duration, new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                view.clearAnimation();
            }
        });
    }

    public static void setFadeInAnimation(View view, int duration, DefaultAnimationListener listener) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setAnimationListener(listener);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void setFadeOutAnimation(final View view, int duration) {

        setFadeOutAnimation(view, duration, new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
                view.clearAnimation();
            }
        });
    }

    public static void setFadeOutAnimation(View view, int duration, DefaultAnimationListener listener) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setAnimationListener(listener);
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void setAnimation(Context context, View view, int animation, DefaultAnimationListener listener) {
        setAnimation(view, android.view.animation.AnimationUtils.loadAnimation(context, animation), listener);
        view.getAnimation().setAnimationListener(listener);
    }

    public static void setShowAnimation(final View view, int animation) {

    }
    public static void setShowAnimation(final View view, Animation animation) {
        setAnimation(view, animation, new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static void setHideAnimation(final View view, Animation animation) {
        setAnimation(view, animation, new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    public static void setAnimation(View view, Animation animation, DefaultAnimationListener listener) {

        animation.setAnimationListener(listener);
        view.startAnimation(animation);
    }
}
