package com.gblatestversion.gbversion.gb.Activity;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_AUDIO;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_MEDIA_VIDEO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.gblatestversion.gbversion.gb.Activity.tools.SpeechToTextActivity;
import com.gblatestversion.gbversion.gb.BaseApplication;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityMainBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this, binding.frameViewAdsMain);
        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        listener();
        if (checkPermission()) {

        } else {
            requestPermission();
        }
    }

    public void listener() {
        binding.StoryDownloader.setOnClickListener(this);
        binding.ScanningTools.setOnClickListener(this);

        binding.ChatTools.setOnClickListener(this);
        binding.DPgen.setOnClickListener(this);

        binding.TxtEffect.setOnClickListener(this);
        binding.PrankTools.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        if (BaseApplication.getAdModel().getExtraScreen().equalsIgnoreCase("Yes")) {
            finish();
        } else {
            Constant.showRateDialog(MainActivity.this, false);
        }
    }

    @Override
    public void onClick(View view) {

        onStartClick(view);

    }


    public void onStartClick(View view) {

        WFSAppLoadAds.getInstance().showInterstitial(MainActivity.this, () -> {
            if (view.getId() == binding.StoryDownloader.getId()) {
                startActivity(new Intent(this, DownloadOptionsActivity.class));
            } else if (view.getId() == binding.ScanningTools.getId()) {

                startActivity(new Intent(this, ScanningOptionsActivity.class));


            } else if (view.getId() == binding.ChatTools.getId()) {
                startActivity(new Intent(this, ChatToolOptionActivity.class));
            } else if (view.getId() == binding.DPgen.getId()) {
                startActivity(new Intent(this, DPGenratorActivity.class));
            } else if (view.getId() == binding.TxtEffect.getId()) {

                startActivity(new Intent(this, TextEffectOptionActivity.class));


            } else if (view.getId() == binding.PrankTools.getId()) {

                startActivity(new Intent(this, PrankToolOptionActivity.class));


            }
        });
    }


    private boolean checkPermission() {

        if (Build.VERSION.SDK_INT >= 33) {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_MEDIA_IMAGES);
            int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_MEDIA_AUDIO);
            int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_MEDIA_VIDEO);
            int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

            return result == PackageManager.PERMISSION_GRANTED &&
                    result1 == PackageManager.PERMISSION_GRANTED &&
                    result2 == PackageManager.PERMISSION_GRANTED &&
                    result3 == PackageManager.PERMISSION_GRANTED;
        } else {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
            int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);

            return result == PackageManager.PERMISSION_GRANTED &&
                    result1 == PackageManager.PERMISSION_GRANTED &&
                    result2 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {

        if (Build.VERSION.SDK_INT >= 33) {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA, READ_MEDIA_AUDIO, READ_MEDIA_VIDEO, READ_MEDIA_IMAGES}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA}, PERMISSION_REQUEST_CODE);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean data = true;
                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (Build.VERSION.SDK_INT >= 33) {
                    data = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                }
                Intent i;
                boolean writeD = shouldShowRequestPermissionRationale(permissions[0]);
                boolean readD = shouldShowRequestPermissionRationale(permissions[1]);
                boolean camera = shouldShowRequestPermissionRationale(permissions[2]);
                boolean data1 = true;

                if (Build.VERSION.SDK_INT >= 33) {
                    data1 = shouldShowRequestPermissionRationale(permissions[3]);
                }

                if (camera && write && read && data) {

                } else if (!camera || !writeD || !readD || !data1) {
                 /*   rePermissionDialog((dialog, which) -> {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    });
*/
                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if (Build.VERSION.SDK_INT >= 33) {

                            if (shouldShowRequestPermissionRationale(CAMERA) || shouldShowRequestPermissionRationale(READ_MEDIA_AUDIO) ||
                                    shouldShowRequestPermissionRationale(READ_MEDIA_VIDEO) || shouldShowRequestPermissionRationale(READ_MEDIA_IMAGES)) {

                                requestPermissions(new String[]{CAMERA, READ_MEDIA_AUDIO, READ_MEDIA_VIDEO, READ_MEDIA_IMAGES},
                                        PERMISSION_REQUEST_CODE);
                            }

                        } else {
                            if (shouldShowRequestPermissionRationale(CAMERA) || shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE) ||
                                    shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {

                                requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                                        PERMISSION_REQUEST_CODE);

                            }
                        }
                    }

                }
            }
        }

    }

    private void rePermissionDialog(DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this).setTitle("Permission Denied").setMessage("You need to allow access to the permissions. Without this permission you can't access your storage. Are you sure deny this permission?").setPositiveButton("Give Permission", okListener).setNegativeButton("Deny Permission", null).create().show();
    }
}