package com.marcelosmith77.android.baselibrary.ads;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

public class RewardedAdsHelper<T> implements RewardedVideoAdListener {

    private static final String REWARDED_TAG = "REWARDED";

    private RewardedVideoAd mRewardedVideoAd;
    private RewardItem reward;
    private List<String> testDevices;
    private T extras;

    /**
     * callback listener do banner de recompensa
     */
    private RewardedCallback<T> callback;

    private Context context;
    private String adUnitId;

    public RewardedAdsHelper(Context context, String adUnitId){
        this.context = context;
        this.adUnitId = adUnitId;
        this.testDevices = new ArrayList<>();
    }

    /**
     * Requisita banner de recompensa
     */
    public void requestRewardedBanner() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        reward = null;

        if (!mRewardedVideoAd.isLoaded()) {

            AdRequest.Builder builder = new AdRequest.Builder();
            builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

            if (!testDevices.isEmpty()) {
                for (String deviceId : testDevices) {
                    Log.i(REWARDED_TAG, "setup test device: " + deviceId);
                    builder.addTestDevice(deviceId);
                }
            }

            mRewardedVideoAd.loadAd(adUnitId, builder.build());
        }
    }

    public boolean isLoaded() {
        return mRewardedVideoAd != null && mRewardedVideoAd.isLoaded();
    }

    public void showRewardedVideo(T extras, RewardedCallback<T> callback) {
        this.extras = extras;
        this.callback = callback;

        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        } else {
            // Vamos prosseguir.
            try {
                if (callback != null) {
                    this.callback.onRewarded(extras, null);
                }
            } finally {
                // Preload the next video ad.
                loadRewardedVideoAd();
            }
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.i(REWARDED_TAG, "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.i(REWARDED_TAG, "onRewardedVideoAdClosed");

        try {
//            if (reward != null) {
                if (callback != null) {
                    this.callback.onRewarded(extras, reward);
//                }
            }
        } finally {
            // Preload the next video ad.
            loadRewardedVideoAd();
        }
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Log.i(REWARDED_TAG, "onRewardedVideoAdFailedToLoad");
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.i(REWARDED_TAG, "onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.i(REWARDED_TAG, "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Log.i(REWARDED_TAG, "onRewarded");
        this.reward = reward;
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.i(REWARDED_TAG, "onRewardedVideoCompleted");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.i(REWARDED_TAG, "onRewardedVideoStarted");
    }

    public interface RewardedCallback<T> {
        void onRewarded(T extras, RewardItem reward);
    }

    public void addTestDevice(String deviceId) {
        this.testDevices.add(deviceId);
    }
}
