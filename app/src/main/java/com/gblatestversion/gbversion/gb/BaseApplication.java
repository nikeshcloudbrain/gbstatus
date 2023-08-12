package com.gblatestversion.gbversion.gb;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.adsw.WFSAppOpenManager;
import com.gblatestversion.gbversion.gb.adsw.WFSModelAd;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.ads.MobileAds;



public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private Activity activity;
    private Context context;

    public static final String ADS_COUNT_SHOW = "ads_count_show";
    public static final String ADS_COUNT_BACK_SHOW = "ads_count_back_show";

    public static final boolean IS_APP_OPEN_SHOWING = true;
    public static final boolean IS_NATIVE_AD_LAST = false;

    public static boolean booleanSplashAds = false;
    public WFSAppOpenManager openManager;
    public static final String TAG = BaseApplication.class.getSimpleName();

    public static WFSModelAd adModel;
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> {
        });
        Fresco.initialize(this);

        mInstance = this;
        openManager=new WFSAppOpenManager(this);
    }

    public static synchronized BaseApplication getInstance() {
        BaseApplication baseApplication;

        baseApplication = mInstance;
             return baseApplication;
    }

    public static WFSModelAd getAdModel() {
        if (adModel == null) {
            adModel = WFSAppLoadAds.getModelAd(new WFSModelAd());
        }
        return adModel;
    }



    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public void showAdIfAvailable(@NonNull Activity activity, @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
        openManager.showAdIfSplashAvailable(activity, onShowAdCompleteListener);
    }
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }
    static {
        System.loadLibrary("native-lib");
    }
}
