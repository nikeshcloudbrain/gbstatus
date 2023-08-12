package com.gblatestversion.gbversion.gb.Activity.tools;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.text.format.Formatter;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.gblatestversion.gbversion.gb.adaptor.ImageAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityCleanWallBinding;
import com.gblatestversion.gbversion.gb.model.FileModel;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FileUtils;
import com.gblatestversion.gbversion.gb.utils.ItemOffsetDecoration;
import com.preference.PowerPreference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CleanWallActivity extends AppCompatActivity {

    String category;
    String path;

    ArrayList<FileModel> list = new ArrayList<>();
    HashMap<Integer, String> dellist = new HashMap<>();

    ActivityCleanWallBinding gbvCleanWallBinding;

    ImageAdapter imageAdapter;
    boolean isSelectAll = false;

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvCleanWallBinding = ActivityCleanWallBinding.inflate(getLayoutInflater());
        setContentView(gbvCleanWallBinding.getRoot());

        if (getIntent() != null && getIntent().hasExtra(Constant.CATEGORY)) {
            category = getIntent().getExtras().getString(Constant.CATEGORY);
            path = getIntent().getExtras().getString("folderPath");
        }
        
        gbvCleanWallBinding.llSelectAll.setOnClickListener(view -> selectAll());
        gbvCleanWallBinding.llDelete.setOnClickListener(view -> {
            deleteAll();
        });

        PowerPreference.getDefaultFile().putBoolean(Constant.MENU_OPTIONS, false);
        gbvCleanWallBinding.llBottom.setVisibility(View.GONE);
        setToolbar();
        startWall();

    }


    public void setToolbar() {
        gbvCleanWallBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());
        /*if(PowerPreference.getDefaultFile().getBoolean(AdUtils.QurekaOnOff) && PowerPreference.getDefaultFile().getBoolean(AdUtils.AdsOnOff)){
            gbvCleanWallBinding.includedToolbar.ivQureka.setVisibility(View.VISIBLE);
        }else{
            gbvCleanWallBinding.includedToolbar.ivQureka.setVisibility(View.INVISIBLE);

        }
        gbvCleanWallBinding.includedToolbar.ivQureka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdUtils.gotoAds(AslCleanWallActivity.this);
            }
        });*/
    }

    public void startWall() {
        gbvCleanWallBinding.recyclerView.addItemDecoration(new ItemOffsetDecoration(this, com.intuit.sdp.R.dimen._4sdp));
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager1=new GridLayoutManager(this,3);
        manager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(imageAdapter.getItemViewType(position)){
                    case 1:
                        return 3;
                    case 0:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        gbvCleanWallBinding.recyclerView.setLayoutManager(manager1);
        imageAdapter = new ImageAdapter(this, category, new ImageAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, FileModel value) {
            }

            @Override
            public void onCheckClick(int position, FileModel value) {
                if (value.isSelected) {
                    dellist.remove(position);
                    value.isSelected = false;
                    if (dellist.size() == 0) {
                        isSelectAll = false;
                    }
                } else {
                    dellist.put(position, value.filePath);
                    value.isSelected = true;

                    if (dellist.size() == list.size()) {
                        isSelectAll = true;
                    }
                }

                if (dellist.size() > 0) {
                    if (!PowerPreference.getDefaultFile().getBoolean(Constant.MENU_OPTIONS, false)) {
                        hideShowMenu();
                    }
                } else {
                    if (PowerPreference.getDefaultFile().getBoolean(Constant.MENU_OPTIONS, false)) {
                        hideShowMenu();
                    }
                }
                imageAdapter.notifyItemChanged(position);
            }
        });
        gbvCleanWallBinding.recyclerView.setAdapter(imageAdapter);
        new Handler().postDelayed(this::refresh, 500);
    }

    public void hideShowMenu() {
        if (PowerPreference.getDefaultFile().getBoolean(Constant.MENU_OPTIONS, false)) {
            PowerPreference.getDefaultFile().putBoolean(Constant.MENU_OPTIONS, false);
            gbvCleanWallBinding.llBottom.setVisibility(View.GONE);
        } else {
            PowerPreference.getDefaultFile().putBoolean(Constant.MENU_OPTIONS, true);
            gbvCleanWallBinding.llBottom.setVisibility(View.VISIBLE);
        }
    }


    public void refresh() {
        this.list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                    getdataFetch2();
                } else {
                    setData();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageAdapter.refresh(list);
                        checkData();
                    }
                });
            }
        }).start();
    }


    public void getdataFetch2() {
        DocumentFile file = null;
        if (PowerPreference.getDefaultFile().getString("CleanPage").equalsIgnoreCase(Constant.WHAT)) {
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

                String checkFolder = "";

                if (PowerPreference.getDefaultFile().getString("CleanPage").equalsIgnoreCase(Constant.WHAT)) {
                    if (category.equalsIgnoreCase(FileUtils.VOICE)) {
                        if (files[i].getName().equalsIgnoreCase(FileUtils.voiceReceivedPath.substring(FileUtils.voiceReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = files[i].getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.WALLPAPER)) {
                        if (files[i].getName().equalsIgnoreCase(FileUtils.wallReceivedPath.substring(FileUtils.wallReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = files[i].getName();
                        }
                    }
                } else {
                    if (category.equalsIgnoreCase(FileUtils.VOICE)) {
                        if (files[i].getName().equalsIgnoreCase(FileUtils.wbVoiceReceivedPath.substring(FileUtils.wbVoiceReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = files[i].getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.WALLPAPER)) {
                        if (files[i].getName().equalsIgnoreCase(FileUtils.wbWallReceivedPath.substring(FileUtils.wbWallReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = files[i].getName();
                        }
                    }
                }

                if (files[i] != null && files[i].isDirectory() && files[i].getName() != null && checkFolder.equalsIgnoreCase(files[i].getName())) {
                    DocumentFile[] listFiles = files[i].listFiles();
                    for (DocumentFile file1 : listFiles) {
                        if (file1 != null && file1.isDirectory() && file1.getName() != null) {
                            DocumentFile[] listFiles1 = file1.listFiles();
                            for (DocumentFile file2 : listFiles1) {
                                if (!file2.getUri().toString().contains(".nomedia") && !file2.isDirectory()) {
                                    list.add(new FileModel(file2.getName(), file2.getUri().toString(), false, Formatter.formatShortFileSize(this, file2.length())));
                                }
                            }
                        } else {
                            if (!file1.getUri().toString().contains(".nomedia") && !file1.isDirectory()) {
                                list.add(new FileModel(file1.getName(), file1.getUri().toString(), false, Formatter.formatShortFileSize(this, file1.length())));
                            }
                        }

                    }
                }
            }
        }
    }

    public void setData() {
        File[] dirListByAscendingDate = new File(path).listFiles();
        if (dirListByAscendingDate == null) return;
        for (int i = 0; i < dirListByAscendingDate.length; i++) {
            if (!dirListByAscendingDate[i].getAbsolutePath().contains(".nomedia") && !dirListByAscendingDate[i].isDirectory()) {
                list.add(new FileModel(dirListByAscendingDate[i].getName(), dirListByAscendingDate[i].getAbsolutePath(), false, Formatter.formatShortFileSize(this, dirListByAscendingDate[i].length())));
            } else if (dirListByAscendingDate[i].isDirectory()) {
                File[] list1 = new File(path).listFiles();
                if (list1 == null) return;
                for (int j = 0; j < list1.length; j++) {
                    if (!list1[i].getAbsolutePath().contains(".nomedia") && !list1[i].isDirectory()) {
                        list.add(new FileModel(list1[i].getName(), list1[i].getAbsolutePath(), false, Formatter.formatShortFileSize(this, list1[i].length())));
                    }
                }
            }
        }
    }

    public void checkData() {
        gbvCleanWallBinding.includedError.progress.setVisibility(View.GONE);
        if (imageAdapter.getItemCount() > 0) {
            gbvCleanWallBinding.includedError.tvNodata.setVisibility(View.GONE);
        } else
            gbvCleanWallBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
    }

    public void selectAll() {
        if (isSelectAll) {
            isSelectAll = false;
            if (dellist.size() == list.size()) {
                dellist.clear();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).isSelected = false;
                }
            } else {
                selectAll();
            }
        } else {
            isSelectAll = true;
            for (int i = 0; i < list.size(); i++) {
                list.get(i).isSelected = true;
                dellist.put(i, list.get(i).filePath);
            }
        }

        imageAdapter.notifyDataSetChanged();
    }


    public void deleteAll() {
        if (dellist.size() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Delete....");
            builder.setMessage("Are you sure, You Want To Delete This Status?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                Constant.showLoader(CleanWallActivity.this,"Deleting Please Wait...");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<FileModel> models = new ArrayList<>();
                        for (int data = 0; data < list.size(); data++) {
                            if (list.get(data).isSelected) {

                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && list.get(data).filePath.contains("content")) {
                                    DocumentFile file = DocumentFile.fromSingleUri(CleanWallActivity.this, Uri.parse(list.get(data).filePath));
                                    try {
                                        DocumentsContract.deleteDocument(getContentResolver(), file.getUri());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    File file = new File(list.get(data).filePath);
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                }
                                PowerPreference.getDefaultFile().putBoolean(Constant.isDelete, true);

                            } else {
                                models.add(list.get(data));
                            }
                        }

                        dellist.clear();
                        list = models;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageAdapter.refresh(list);
                                checkData();
                                hideShowMenu();
                                Constant.dismissLoader();
                            }
                        });
                    }
                }).start();


            });
            builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
            builder.show();
        } else {
            Constant.showToast("Status not selected");
        }
    }


}