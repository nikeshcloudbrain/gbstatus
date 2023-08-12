package com.gblatestversion.gbversion.gb.adsw;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.gblatestversion.gbversion.gb.Activity.SplashActivity;
import com.gblatestversion.gbversion.gb.BaseApplication;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;


import java.util.Date;

public class WFSAppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    public AppOpenAd appOpenAd = null;
    public AppOpenAd.AppOpenAdLoadCallback appCallback;
    public final BaseApplication appMyApp;
    public static boolean appIsShowingAd = false;
    public Activity appCurrentActivity;
    public long appLoadTime = 0;

    public WFSAppOpenManager(BaseApplication myApplication) {
        this.appMyApp = myApplication;
        this.appMyApp.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void fetchAd() {

        if (isAdAvailable()) {
            return;
        }

        appCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(AppOpenAd ad) {
                WFSAppOpenManager.this.appOpenAd = ad;
                WFSAppOpenManager.this.appLoadTime = (new Date()).getTime();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {

            }
        };
        AdRequest request = getAdRequest();
        AppOpenAd.load(appMyApp, BaseApplication.getAdModel().getAdsAppOpenId(), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, appCallback);
    }


    public AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    public void showAdIfAvailable() {
        if (BaseApplication.getAdModel().getAdsAppOpen().equalsIgnoreCase("Google")) {
            if (!appIsShowingAd && isAdAvailable()) {
                if (BaseApplication.IS_APP_OPEN_SHOWING) {
                    FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            WFSAppOpenManager.this.appOpenAd = null;
                            appIsShowingAd = false;
                            fetchAd();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            appIsShowingAd = true;
                        }
                    };

                    appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                    appOpenAd.show(appCurrentActivity);
                }
            } else {
                fetchAd();
            }
        }
    }

    public void showAdIfSplashAvailable(@NonNull final Activity activity, @NonNull BaseApplication.OnShowAdCompleteListener onShowAdCompleteListener) {
        if (BaseApplication.getAdModel().getAdsAppOpen().equalsIgnoreCase("Google")) {
            if (!appIsShowingAd && isAdAvailable()) {
                FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        WFSAppOpenManager.this.appOpenAd = null;
                        appIsShowingAd = false;
                        fetchAd();
                        onShowAdCompleteListener.onShowAdComplete();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        onShowAdCompleteListener.onShowAdComplete();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        appIsShowingAd = true;
                    }
                };
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(activity);
            } else {
                appCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        WFSAppOpenManager.this.appOpenAd = ad;
                        WFSAppOpenManager.this.appLoadTime = (new Date()).getTime();

                        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                WFSAppOpenManager.this.appOpenAd = null;
                                appIsShowingAd = false;
                                fetchAd();
                                onShowAdCompleteListener.onShowAdComplete();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                onShowAdCompleteListener.onShowAdComplete();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                appIsShowingAd = true;
                            }
                        };
                        appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                        appOpenAd.show(appCurrentActivity);
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        onShowAdCompleteListener.onShowAdComplete();
                    }
                };
                AdRequest request = getAdRequest();
                AppOpenAd.load(appMyApp, BaseApplication.getAdModel().getAdsAppOpenId(), request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, appCallback);
            }
        } else {
            onShowAdCompleteListener.onShowAdComplete();
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        appCurrentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        appCurrentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        appCurrentActivity = null;
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        if (BaseApplication.booleanSplashAds || !(appCurrentActivity instanceof SplashActivity)) {
             showAdIfAvailable();
        }
    }

    public boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - this.appLoadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }
}