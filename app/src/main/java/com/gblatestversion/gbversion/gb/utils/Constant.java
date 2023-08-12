package com.gblatestversion.gbversion.gb.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.gblatestversion.gbversion.gb.BaseApplication;
import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.DialogLoaderBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.net.URLConnection;


public class Constant {



    public static String emptyError = "Please Enter Something";

    public static final String whatPath = "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia";
    public static final String busiPath = "Android%2Fmedia%2Fcom.whatsapp.w4b%2FWhatsApp%20Business%2FMedia";

    public static final String whatPath3 = "Android/media/com.whatsapp/WhatsApp/Media";
    public static final String busiPath3 = "Android/media/com.whatsapp.w4b/WhatsApp Business/Media";

    public static final String wPath = "wPath";
    public static final String cPath = "cPath";

    public static final String wbPath = "wbPath";
    public static final String isStatus = "isStatus";
    public static final String isDelete = "isDelete";

    public static final String CATEGORY = "CATEGORY";

    public static final String WHAT_OPTIONS = "WHAT_OPTIONS";
    public static final String SAVE_OPTIONS = "SAVE_OPTIONS";
    public static final String MENU_OPTIONS = "MENU_OPTIONS";

    public static final String OPTIONS1 = "OPTIONS1";
    public static final String OPTIONS2 = "OPTIONS2";

    public static final String WHAT = "WHAT";
    public static final String WBUS = "WBUS";


    public static void Log(String message) {
        Log.e("errorLog", message);
    }







    public static native String getMainApi();

    public static native String getKey1();

    public static native String getKey2();


    public static void showRateDialog(Activity activity, boolean isAds) {
        try {
            View bottomSheetView = activity.getLayoutInflater().inflate(R.layout.dialog_exit, null);






            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
            bottomSheetDialog.setContentView(bottomSheetView);
            AppCompatButton bottomSheetButton = bottomSheetView.findViewById(R.id.btnExit);

            bottomSheetDialog.setOnShowListener(dialogInterface -> {
                if (BaseApplication.getAdModel().getAdsExit().equalsIgnoreCase("YES")) {
                    bottomSheetDialog.findViewById(R.id.cardViewAdsMain).setVisibility(View.VISIBLE);
                    new Handler().postDelayed(() -> WFSAppLoadAds.getInstance().showNativeMediaMatch(activity, bottomSheetDialog.findViewById(R.id.frameViewAdsMain)), 500);
                } else {
                    bottomSheetDialog.findViewById(R.id.cardViewAdsMain).setVisibility(View.GONE);
                }
            });
            bottomSheetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    bottomSheetDialog.dismiss();
                    activity.finishAffinity();
                }
            });

            bottomSheetDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Dialog mLoadingDialog;

    public static void showLog(String message) {
        Log.e("errorLog", message);
    }

    public static void dismissLoader() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }

    public static void showLoader(Context mActivity, String msg) {

        try {
            mLoadingDialog = new Dialog(mActivity);
            DialogLoaderBinding binding = DialogLoaderBinding.inflate(LayoutInflater.from(mActivity));
            mLoadingDialog.setContentView(binding.getRoot());
            mLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.txtMsg.setText(msg);
            mLoadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(String message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isImageFile(String str) {
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(str);
        return guessContentTypeFromName != null && guessContentTypeFromName.startsWith("image");
    }

    public static boolean isVideoFile(String str) {
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(str);
        return guessContentTypeFromName != null && guessContentTypeFromName.startsWith("video");
    }

    public static void shareApp(Activity activity, String text) {
        try {
            Intent i = new Intent();
            i.setAction("android.intent.action.SEND");
            i.setType("text/plain");
            i.putExtra("android.intent.extra.SUBJECT", activity.getResources().getString(R.string.app_name));
            i.putExtra("android.intent.extra.TEXT", text);
            activity.startActivity(Intent.createChooser(i, "Share Application"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getImagesFolder() {
        File dCimDirPath = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + BaseApplication.getInstance().getString(R.string.folder_output) + "/Images/");

        if (!dCimDirPath.exists())
            dCimDirPath.mkdirs();

        return dCimDirPath.getAbsolutePath();
    }

    public static String getVideosFolder() {
        File dCimDirPath = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + BaseApplication.getInstance().getString(R.string.folder_output) + "/Videos/");

        if (!dCimDirPath.exists())
            dCimDirPath.mkdirs();

        return dCimDirPath.getAbsolutePath();
    }

    public static String getQRFolder() {
        File dCimDirPath = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + BaseApplication.getInstance().getString(R.string.folder_output) + "/QrImages/");

        if (!dCimDirPath.exists())
            dCimDirPath.mkdirs();

        return dCimDirPath.getAbsolutePath();
    }

    public static void mediaScanner(Context context, String str, String str2, String str3) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{str + new File(str2).getName()}, new String[]{str3}, new MediaScannerConnection.MediaScannerConnectionClient() {
                public void onMediaScannerConnected() {
                }

                public void onScanCompleted(String str, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isPackageInstalled(Activity activity, String packageName) {
        try {
            activity.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
