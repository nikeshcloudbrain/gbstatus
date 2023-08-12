package com.gblatestversion.gbversion.gb.utils;

import android.app.Activity;
import android.content.Intent;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

public class uiController {

    public static void onBackPressed(Activity activity) {


        WFSAppLoadAds.getInstance().showInterstitialBack(activity, () -> {
            activity.finish();
        });
    }

    public static void gotoActivity(Activity activity, Class<?> activityClass, boolean isAds, boolean isFinish) {
        sendIntent(activity, new Intent(activity, activityClass), isAds, isFinish);
    }

    public static void sendIntent(Activity activity, Intent intent, boolean isAds, boolean isFinish) {
        if (isAds) {
            WFSAppLoadAds.getInstance().showInterstitialBack(activity, () -> {

                activity.startActivity(intent);
                    if (isFinish) activity.finish();

            });
        } else {
            activity.startActivity(intent);
            if (isFinish) activity.finish();
        }
    }
}
