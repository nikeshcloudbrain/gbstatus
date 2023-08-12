package com.gblatestversion.gbversion.gb.Activity.tools;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.format.Formatter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ActivityWhatCleanerBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FileUtils;
import com.github.florent37.inlineactivityresult.InlineActivityResult;
import com.github.florent37.inlineactivityresult.Result;
import com.github.florent37.inlineactivityresult.callbacks.FailCallback;
import com.github.florent37.inlineactivityresult.callbacks.SuccessCallback;

import com.preference.PowerPreference;

import java.io.File;

public class WhatCleanerActivity extends AppCompatActivity {

    ActivityWhatCleanerBinding gbvWhatCleanerBinding;
    String status;
    boolean checked = false;

    @Override
    protected void onResume() {
        super.onResume();


        if (PowerPreference.getDefaultFile().getBoolean(Constant.isDelete, false))
            checkPermissions();


        PowerPreference.getDefaultFile().getBoolean(Constant.isDelete, false);
    }

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvWhatCleanerBinding = ActivityWhatCleanerBinding.inflate(getLayoutInflater());
        setContentView(gbvWhatCleanerBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvWhatCleanerBinding.frameViewAdsMain);

        if (getIntent() != null && getIntent().hasExtra(Constant.WHAT_OPTIONS)) {
            status = getIntent().getStringExtra(Constant.WHAT_OPTIONS);
        }

        PowerPreference.getDefaultFile().getBoolean(Constant.isDelete, false);
        setToolbar();
        listener();
        checkPermissions();
    }

    public void checkPermissions() {
        if (status.equalsIgnoreCase(Constant.WHAT)) {
            if (!PowerPreference.getDefaultFile().getString(Constant.wPath, "").equalsIgnoreCase("") || !Constant.isPackageInstalled(this, "com.whatsapp")) {
                checkSize();
            } else {
                getFolderPermission();
            }
        } else {
            if (!PowerPreference.getDefaultFile().getString(Constant.wbPath, "").equalsIgnoreCase("") || !Constant.isPackageInstalled(this, "com.whatsapp.w4b")) {
                checkSize();
            } else {
                getFolderPermission();
            }
        }
    }

    public void getFolderPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {

            StorageManager manager = (StorageManager) getSystemService(STORAGE_SERVICE);
            Intent intent = manager.getPrimaryStorageVolume().createOpenDocumentTreeIntent();

            String target = Constant.whatPath;
            if (status.equalsIgnoreCase(Constant.WBUS)) {
                target = Constant.busiPath;
            }

            Uri uri = intent.getParcelableExtra("android.provider.extra.INITIAL_URI");
            String scheme = uri.toString().replace("/root/", "/document/");
            scheme = scheme + "%3A" + target;
            uri = Uri.parse(scheme);
            intent.putExtra("android.provider.extra.INITIAL_URI", uri);
            intent.putExtra("android.content.extra.SHOW_ADVANCED", true);


            new InlineActivityResult(this)
                    .startForResult(intent)
                    .onSuccess(new SuccessCallback() {
                        @Override
                        public void onSuccess(Result result) {
                            if (result.getData() != null && result.getData().getData() != null) {
                                Uri uri = result.getData().getData();
                                if (status.equalsIgnoreCase(Constant.WHAT) && uri.toString().endsWith(Constant.whatPath)) {
                                    PowerPreference.getDefaultFile().putString(Constant.wPath, uri.toString());
                                } else if (status.equalsIgnoreCase(Constant.WBUS) && uri.toString().endsWith(Constant.busiPath)) {
                                    PowerPreference.getDefaultFile().putString(Constant.wbPath, uri.toString());
                                } else {
                                    if (status.equalsIgnoreCase(Constant.WHAT)) {
                                        Toast.makeText(WhatCleanerActivity.this, "Please Give Permission to access " + Constant.whatPath3, Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(WhatCleanerActivity.this, "Please Give Permission to access " + Constant.busiPath3, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                checkSize();
                            } else {
                                checkSize();
                            }
                        }
                    }).onFail(new FailCallback() {
                        @Override
                        public void onFailed(Result result) {
                            checkSize();
                        }
                    });
        } else {
            checkSize();
        }
    }


    public void setToolbar() {
        gbvWhatCleanerBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }


    public void listener() {
        gbvWhatCleanerBinding.cvVoice.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoWall(FileUtils.VOICE, FileUtils.voiceReceivedPath);
            } else {
                gotoWall(FileUtils.VOICE, FileUtils.wbVoiceReceivedPath);
            }

        });

        gbvWhatCleanerBinding.cvWallpaper.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoWall(FileUtils.WALLPAPER, FileUtils.wallReceivedPath);
            } else {
                gotoWall(FileUtils.WALLPAPER, FileUtils.wbWallReceivedPath);
            }
        });

        gbvWhatCleanerBinding.cvImages.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoData(FileUtils.IMAGE, FileUtils.imagesReceivedPath, FileUtils.imagesSentPath);
            } else {
                gotoData(FileUtils.IMAGE, FileUtils.wbImagesReceivedPath, FileUtils.wbImagesSentPath);
            }
        });

        gbvWhatCleanerBinding.cvVideos.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoData(FileUtils.VIDEO, FileUtils.videosReceivedPath, FileUtils.videosSentPath);
            } else {
                gotoData(FileUtils.VIDEO, FileUtils.wbVideosReceivedPath, FileUtils.wbVideosSentPath);
            }
        });


        gbvWhatCleanerBinding.cvAudio.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoData(FileUtils.AUDIO, FileUtils.audiosReceivedPath, FileUtils.audiosSentPath);
            } else {
                gotoData(FileUtils.AUDIO, FileUtils.wbAudiosReceivedPath, FileUtils.wbAudiosSentPath);
            }
        });

        gbvWhatCleanerBinding.cvDocs.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoData(FileUtils.DOCUMENT, FileUtils.documentsReceivedPath, FileUtils.documentsSentPath);
            } else {
                gotoData(FileUtils.DOCUMENT, FileUtils.wbDocumentsReceivedPath, FileUtils.wbDocumentsSentPath);
            }
        });

        gbvWhatCleanerBinding.cvGif.setOnClickListener(view -> {
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                gotoData(FileUtils.GIF, FileUtils.gifReceivedPath, FileUtils.gifSentPath);
            } else {
                gotoData(FileUtils.GIF, FileUtils.wbGifReceivedPath, FileUtils.wbGifSentPath);
            }
        });
    }

    public void gotoWall(String data, String path) {
        PowerPreference.getDefaultFile().putString("CleanPage", status);
        WFSAppLoadAds.getInstance().showInterstitial(this, () -> startActivity(new Intent(this, CleanWallActivity.class).putExtra(Constant.CATEGORY, data).putExtra("folderPath", path).putExtra("page", status)));
    }

    public void gotoData(String data, String path, String path2) {
        PowerPreference.getDefaultFile().putString("CleanPage", status);
        WFSAppLoadAds.getInstance().showInterstitial(this, () -> startActivity(new Intent(this, CleanDataActivity.class).putExtra(Constant.CATEGORY, data).putExtra("folderPath", path).putExtra("folderPath2", path2).putExtra("page", status)));
    }


    public void checkSize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (status.equalsIgnoreCase(Constant.WHAT)) {
                    folderSize(Environment.getExternalStorageDirectory().toString() + FileUtils.mString, FileUtils.IMAGE);
                } else {
                    folderSize(Environment.getExternalStorageDirectory().toString() + FileUtils.mStringBusiness, FileUtils.IMAGE);
                }
            }
        }).start();
    }

    public void folderSize(String path, String folder) {
        if (Build.VERSION.SDK_INT < 30) {

            if (new File(path).exists() && new File(path).listFiles() != null) {
                File[] files = new File(path).listFiles();

                for (int i = 0; i < files.length; i++) {

                    if (files[i] != null && files[i].isDirectory() && files[i].getName() != null) {
                        long size = 0;

                        File[] listFiles = files[i].listFiles();
                        if (listFiles == null)
                            break;

                        for (File file1 : listFiles) {

                            if (file1 != null && file1.isDirectory() && file1.getName() != null) {

                                File[] listFiles1 = file1.listFiles();
                                if (listFiles1 == null)
                                    break;
                                for (File file2 : listFiles1) {
                                    if (!file2.getAbsolutePath().toString().contains(".nomedia") && !file2.isDirectory()) {
                                        size = size + file2.length();
                                    }
                                }
                            } else {
                                if (!file1.getAbsolutePath().toString().contains(".nomedia")) {
                                    size = size + file1.length();
                                }
                            }
                        }

                        if (status.equalsIgnoreCase(Constant.WHAT)) {
                            if (files[i].getName().equalsIgnoreCase(FileUtils.imagesReceivedPath.substring(FileUtils.imagesReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtImageSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.videosReceivedPath.substring(FileUtils.videosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVideoSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.documentsReceivedPath.substring(FileUtils.documentsReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtDocsSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.audiosReceivedPath.substring(FileUtils.audiosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtAudioSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.voiceReceivedPath.substring(FileUtils.voiceReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVoiceSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wallReceivedPath.substring(FileUtils.wallReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtWallSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbGifReceivedPath.substring(FileUtils.wbGifReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtGifSize, size);
                            }
                        } else {
                            if (files[i].getName().equalsIgnoreCase(FileUtils.wbImagesReceivedPath.substring(FileUtils.wbImagesReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtImageSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbVideosReceivedPath.substring(FileUtils.wbVideosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVideoSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbDocumentsReceivedPath.substring(FileUtils.wbDocumentsReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtDocsSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbAudiosReceivedPath.substring(FileUtils.wbAudiosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtAudioSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbVoiceReceivedPath.substring(FileUtils.wbVoiceReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVoiceSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbWallReceivedPath.substring(FileUtils.wbWallReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtWallSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbGifReceivedPath.substring(FileUtils.wbGifReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtGifSize, size);
                            }
                        }
                    }
                }
            }
        } else {

            DocumentFile file = null;
            if (status.equalsIgnoreCase(Constant.WHAT)) {
                if (!PowerPreference.getDefaultFile().getString(Constant.wPath, "").equalsIgnoreCase("")) {
                    String path2 = PowerPreference.getDefaultFile().getString(Constant.wPath, "");
                    getContentResolver().takePersistableUriPermission(Uri.parse(path2), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    file = DocumentFile.fromTreeUri(this, Uri.parse(path2));
                }
            } else {
                if (!PowerPreference.getDefaultFile().getString(Constant.wbPath, "").equalsIgnoreCase("")) {
                    String path2 = PowerPreference.getDefaultFile().getString(Constant.wbPath, "");
                    getContentResolver().takePersistableUriPermission(Uri.parse(path2), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    file = DocumentFile.fromTreeUri(this, Uri.parse(path2));
                }
            }

            if (file != null) {
                DocumentFile[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {

                    if (files[i] != null && files[i].isDirectory() && files[i].getName() != null) {
                        long size = 0;

                        DocumentFile[] listFiles = files[i].listFiles();
                        for (DocumentFile file1 : listFiles) {

                            if (file1 != null && file1.isDirectory() && file1.getName() != null) {

                                DocumentFile[] listFiles1 = file1.listFiles();
                                for (DocumentFile file2 : listFiles1) {
                                    if (!file2.getUri().toString().contains(".nomedia") && !file2.isDirectory()) {
                                        size = size + file2.length();
                                    }
                                }
                            } else {
                                if (!file1.getUri().toString().contains(".nomedia")) {
                                    size = size + file1.length();
                                }
                            }
                        }

                        if (status.equalsIgnoreCase(Constant.WHAT)) {
                            if (files[i].getName().equalsIgnoreCase(FileUtils.imagesReceivedPath.substring(FileUtils.imagesReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtImageSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.videosReceivedPath.substring(FileUtils.videosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVideoSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.documentsReceivedPath.substring(FileUtils.documentsReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtDocsSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.audiosReceivedPath.substring(FileUtils.audiosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtAudioSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.voiceReceivedPath.substring(FileUtils.voiceReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVoiceSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wallReceivedPath.substring(FileUtils.wallReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtWallSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbGifReceivedPath.substring(FileUtils.wbGifReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtGifSize, size);
                            }
                        } else {
                            if (files[i].getName().equalsIgnoreCase(FileUtils.wbImagesReceivedPath.substring(FileUtils.wbImagesReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtImageSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbVideosReceivedPath.substring(FileUtils.wbVideosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVideoSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbDocumentsReceivedPath.substring(FileUtils.wbDocumentsReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtDocsSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbAudiosReceivedPath.substring(FileUtils.wbAudiosReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtAudioSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbVoiceReceivedPath.substring(FileUtils.wbVoiceReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtVoiceSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbWallReceivedPath.substring(FileUtils.wbWallReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtWallSize, size);
                            } else if (files[i].getName().equalsIgnoreCase(FileUtils.wbGifReceivedPath.substring(FileUtils.wbGifReceivedPath.lastIndexOf("/") + 1))) {
                                setText(gbvWhatCleanerBinding.txtGifSize, size);
                            }
                        }
                    }
                }
            }
        }

    }

    public void setText(TextView text, long data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(Formatter.formatShortFileSize(WhatCleanerActivity.this, data));
            }
        });

    }

}