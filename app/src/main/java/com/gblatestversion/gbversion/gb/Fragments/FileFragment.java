package com.gblatestversion.gbversion.gb.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.gblatestversion.gbversion.gb.Activity.tools.RecentStatusActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.StatusDownloadActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.ViewImageActivity;
import com.gblatestversion.gbversion.gb.adaptor.ImageAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.FragmentGnlFileBinding;
import com.gblatestversion.gbversion.gb.model.FileModel;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FileUtils;
import com.gblatestversion.gbversion.gb.utils.ItemOffsetDecoration;
import com.gblatestversion.gbversion.gb.utils.ObjectSerializer;
import com.github.florent37.inlineactivityresult.InlineActivityResult;
import com.github.florent37.inlineactivityresult.Result;
import com.github.florent37.inlineactivityresult.callbacks.FailCallback;
import com.github.florent37.inlineactivityresult.callbacks.SuccessCallback;
import com.preference.PowerPreference;


//import org.apache.commons.io.comparator.LastModifiedFileComparator;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FileFragment extends Fragment {

    Activity activity;
    String category, options;
    String path;

    FragmentGnlFileBinding gbvFileBinding;
    ImageAdapter imageAdapter;

    boolean isSelectAll = false;

    ArrayList<FileModel> list = new ArrayList<>();
    HashMap<Integer, String> dellist = new HashMap<>();
    public FileFragment() {
        // doesn't do anything special
    }
    public FileFragment(Activity activity, String path, String category, String options) {
        this.activity = activity;
        this.path = path;
        this.category = category;
        this.options = options;
    }

    public static FileFragment newInstance(Activity activity, String category, String path, String options) {
        return new FileFragment(activity, path, category, options);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gbvFileBinding = FragmentGnlFileBinding.inflate(getLayoutInflater(), container, false);
        return gbvFileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PowerPreference.getDefaultFile().putBoolean(options, false);
        gbvFileBinding.llBottom.setVisibility(View.GONE);

        gbvFileBinding.recyclerView.addItemDecoration(new ItemOffsetDecoration(activity, com.intuit.sdp.R.dimen._4sdp));
        if (category.equalsIgnoreCase(FileUtils.VIDEO)) {
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            gbvFileBinding.recyclerView.setLayoutManager(manager);

        } else {
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            GridLayoutManager manager1=new GridLayoutManager(activity,3);
            manager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch(imageAdapter.getItemViewType(position)){
                        case 100:
                            return 3;
                        case 101:
                            return 1;
                        default:
                            return -1;
                    }
                }
            });
            gbvFileBinding.recyclerView.setLayoutManager(manager1);

        }
        gbvFileBinding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAll();
            }
        });

        gbvFileBinding.llSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAll();
            }
        });

        imageAdapter = new ImageAdapter(activity, category, new ImageAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, FileModel value) {
                ArrayList<FileModel> tempData = new ArrayList<>();
                tempData.addAll(imageAdapter.stringList);
                tempData.removeAll(Collections.singleton(null));
                for (int i = 0; i < tempData.size(); i++) {
                    if (tempData.get(i).filePath.equalsIgnoreCase(value.filePath)) {
                        position = i;
                        break;
                    }
                }
                if (activity instanceof RecentStatusActivity || activity instanceof StatusDownloadActivity) {
                    int finalPosition = position;
                    WFSAppLoadAds.getInstance().showInterstitial(activity, () -> {
                        try {
                            PowerPreference.getDefaultFile().putString("wastatus", ObjectSerializer.serialize(list));
                            PowerPreference.getDefaultFile().putString("wastatus2", ObjectSerializer.serialize(dellist));
                            PowerPreference.getDefaultFile().putInt("position", finalPosition);

                            Intent intent = new Intent(activity, ViewImageActivity.class);
                            if (activity instanceof RecentStatusActivity) {
                                intent.putExtra(Constant.SAVE_OPTIONS, true);
                            } else if (activity instanceof StatusDownloadActivity) {
                                intent.putExtra(Constant.SAVE_OPTIONS, false);
                            }

                            new InlineActivityResult(FileFragment.this)
                                    .startForResult(intent)
                                    .onSuccess(new SuccessCallback() {
                                        @Override
                                        public void onSuccess(Result result) {
                                            if (result.getResultCode() == Activity.RESULT_OK) {
                                                try {
                                                    list = (ArrayList<FileModel>) ObjectSerializer.deserialize(PowerPreference.getDefaultFile().getString("wastatus"));
                                                    dellist = (HashMap<Integer, String>) ObjectSerializer.deserialize(PowerPreference.getDefaultFile().getString("wastatus2"));


                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    list = new ArrayList<>();
                                                }
                                                PowerPreference.getDefaultFile().putBoolean(Constant.isDelete, false);
                                                imageAdapter.refresh(list);
                                                checkData();
                                            }
                                        }
                                    });
                        } catch (Exception e) {
                            Log.e("TAG", e.toString());
                            e.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onCheckClick(int position, FileModel value) {

                if (value.isSelected) {
                    dellist.remove(position);
                    value.isSelected = false;
                    imageAdapter.stringList.get(position).isSelected = false;
                    if (dellist.size() == 0) {
                        isSelectAll = false;
                    }
                } else {
                    dellist.put(position, value.filePath);
                    value.isSelected = true;
                    imageAdapter.stringList.get(position).isSelected = true;
                    if (dellist.size() == list.size()) {
                        isSelectAll = true;
                    }
                }

                if (dellist.size() > 0) {
                    if (!PowerPreference.getDefaultFile().getBoolean(options, false))
                        hideShowMenu();
                } else {
                    if (PowerPreference.getDefaultFile().getBoolean(options, false)) {
                        hideShowMenu();
                    }
                }
                imageAdapter.notifyItemChanged(position);

            }
        });
        gbvFileBinding.recyclerView.setAdapter(imageAdapter);
        if (activity instanceof RecentStatusActivity && Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            checkPermissions();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh();
                }
            }, 500);
        }
    }

    public void checkPermissions() {
        if (options.equalsIgnoreCase(Constant.OPTIONS1)) {
            if (!PowerPreference.getDefaultFile().getString(Constant.wPath, "").equalsIgnoreCase("") || !Constant.isPackageInstalled(activity, "com.whatsapp")) {
                startFetch();
            } else {
                gbvFileBinding.includedError.progress.setVisibility(View.GONE);
                gbvFileBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
                gbvFileBinding.includedError.llCheck.setVisibility(View.VISIBLE);
                gbvFileBinding.includedError.llCheck.setOnClickListener(v -> {
                    gbvFileBinding.includedError.progress.setVisibility(View.VISIBLE);
                    gbvFileBinding.includedError.tvNodata.setVisibility(View.GONE);
                    gbvFileBinding.includedError.llCheck.setVisibility(View.GONE);
                    getFolderPermission();
                });
            }
        } else {
            if (!PowerPreference.getDefaultFile().getString(Constant.wbPath, "").equalsIgnoreCase("") || !Constant.isPackageInstalled(activity, "com.whatsapp.w4b")) {
                startFetch();
            } else {
                gbvFileBinding.includedError.progress.setVisibility(View.GONE);
                gbvFileBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
                gbvFileBinding.includedError.llCheck.setVisibility(View.VISIBLE);
                gbvFileBinding.includedError.llCheck.setOnClickListener(v -> {
                    gbvFileBinding.includedError.progress.setVisibility(View.VISIBLE);
                    gbvFileBinding.includedError.tvNodata.setVisibility(View.GONE);
                    gbvFileBinding.includedError.llCheck.setVisibility(View.GONE);
                    getFolderPermission();
                });
            }
        }
    }

    public void getFolderPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {

            StorageManager manager = (StorageManager) activity.getSystemService(Context.STORAGE_SERVICE);
            Intent intent = manager.getPrimaryStorageVolume().createOpenDocumentTreeIntent();

            String target = Constant.whatPath;
            if (options.equalsIgnoreCase(Constant.OPTIONS2)) {
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
                                try {
                                    if (options.equalsIgnoreCase(Constant.OPTIONS1) && uri.toString().endsWith(Constant.whatPath)) {
                                        PowerPreference.getDefaultFile().putString(Constant.wPath, uri.toString());
                                        gbvFileBinding.includedError.llCheck.setVisibility(View.GONE);
                                    } else if (options.equalsIgnoreCase(Constant.OPTIONS2) && uri.toString().endsWith(Constant.busiPath)) {
                                        PowerPreference.getDefaultFile().putString(Constant.wbPath, uri.toString());
                                        gbvFileBinding.includedError.llCheck.setVisibility(View.GONE);
                                    } else {
                                        gbvFileBinding.includedError.llCheck.setVisibility(View.VISIBLE);
                                        if (options.equalsIgnoreCase(Constant.OPTIONS1)) {
                                            Toast.makeText(activity, "Please Give Permission to access " + Constant.whatPath3, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(activity, "Please Give Permission to access " + Constant.busiPath3, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    activity.getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startFetch();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {
                                startFetch();
                            }
                        }
                    }).onFail(new FailCallback() {
                        @Override
                        public void onFailed(Result result) {
                            startFetch();
                        }
                    });
        } else {
            startFetch();
        }
    }

    public void startFetch() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, 500);
    }

    public void hideShowMenu() {
        if (PowerPreference.getDefaultFile().getBoolean(options, false)) {
            PowerPreference.getDefaultFile().putBoolean(options, false);
            gbvFileBinding.llBottom.setVisibility(View.GONE);
        } else {
            PowerPreference.getDefaultFile().putBoolean(options, true);
            gbvFileBinding.llBottom.setVisibility(View.VISIBLE);
        }
    }


    public void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list.clear();
                if (activity instanceof RecentStatusActivity) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                        getstatusdata();
                    } else {
                        getdata();
                    }
                } else if (activity instanceof StatusDownloadActivity) {
                    getdata();
                } else {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                        getdataFetch2();
                    } else {
                        getdata();
                    }
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageAdapter.refresh(list);
                        checkData();
                    }
                });
            }
        }).start();
    }


    public void getdata() {
        list.clear();
        File[] listFiles;
        File whatsupFolder = new File(path);
        if (whatsupFolder.exists() && whatsupFolder.isDirectory() && (listFiles = whatsupFolder.listFiles()) != null) {
            Arrays.sort(listFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            for (int i = 0; i < listFiles.length; i++) {
                if (!listFiles[i].getAbsolutePath().contains(".nomedia") && !listFiles[i].isDirectory()) {
                    list.add(new FileModel(listFiles[i].getName(), listFiles[i].getAbsolutePath(), false, Formatter.formatShortFileSize(activity, listFiles[i].length())));
                }
            }
        }
    }

    public void getstatusdata() {
        list.clear();
        DocumentFile file = null;

        if (options.equalsIgnoreCase(Constant.OPTIONS1)) {
            if (!PowerPreference.getDefaultFile().getString(Constant.wPath, "").equalsIgnoreCase("")) {
                String path2 = PowerPreference.getDefaultFile().getString(Constant.wPath, "");
                activity.getContentResolver().takePersistableUriPermission(Uri.parse(path2), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                file = DocumentFile.fromTreeUri(activity, Uri.parse(path2));
            }
        } else {
            if (!PowerPreference.getDefaultFile().getString(Constant.wbPath, "").equalsIgnoreCase("")) {
                String path2 = PowerPreference.getDefaultFile().getString(Constant.wbPath, "");
                activity.getContentResolver().takePersistableUriPermission(Uri.parse(path2), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                file = DocumentFile.fromTreeUri(activity, Uri.parse(path2));
            }
        }

        if (file != null) {
            DocumentFile[] files = file.listFiles();
            for (DocumentFile documentFile : files) {
                if (documentFile != null && documentFile.isDirectory() && documentFile.getName() != null &&
                        documentFile.getName().equalsIgnoreCase(".Statuses")) {
                    DocumentFile[] listFiles = documentFile.listFiles();
                    for (DocumentFile file1 : listFiles) {
                        if (!file1.getUri().toString().contains(".nomedia") && !file1.isDirectory()) {
                            list.add(new FileModel(file1.getName(), file1.getUri().toString(), false, Formatter.formatShortFileSize(activity, file1.length())));
                        }
                    }
                    break;
                }
            }
        }
    }


    public void getdataFetch2() {

        DocumentFile file = null;
        if (PowerPreference.getDefaultFile().getString("CleanPage").equalsIgnoreCase(Constant.WHAT)) {
            if (!PowerPreference.getDefaultFile().getString(Constant.wPath, "").equalsIgnoreCase("")) {
                String path2 = PowerPreference.getDefaultFile().getString(Constant.wPath, "");
                activity.getContentResolver().takePersistableUriPermission(Uri.parse(path2), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                file = DocumentFile.fromTreeUri(activity, Uri.parse(path2));
            }
        } else {
            if (!PowerPreference.getDefaultFile().getString(Constant.wbPath, "").equalsIgnoreCase("")) {
                String path2 = PowerPreference.getDefaultFile().getString(Constant.wbPath, "");
                activity.getContentResolver().takePersistableUriPermission(Uri.parse(path2), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                file = DocumentFile.fromTreeUri(activity, Uri.parse(path2));
            }
        }

        if (file != null) {
            DocumentFile[] files = file.listFiles();
            for (DocumentFile documentFile : files) {

                String checkFolder = "";

                if (PowerPreference.getDefaultFile().getString("CleanPage").equalsIgnoreCase(Constant.WHAT)) {
                    if (category.equalsIgnoreCase(FileUtils.IMAGE)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.imagesReceivedPath.substring(FileUtils.imagesReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.VIDEO)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.videosReceivedPath.substring(FileUtils.videosReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.DOCUMENT)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.documentsReceivedPath.substring(FileUtils.documentsReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.AUDIO)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.audiosReceivedPath.substring(FileUtils.audiosReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.VOICE)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.voiceReceivedPath.substring(FileUtils.voiceReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.WALLPAPER)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wallReceivedPath.substring(FileUtils.wallReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.GIF)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.gifReceivedPath.substring(FileUtils.gifReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    }
                } else {
                    if (category.equalsIgnoreCase(FileUtils.IMAGE)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbImagesReceivedPath.substring(FileUtils.wbImagesReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.VIDEO)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbVideosReceivedPath.substring(FileUtils.wbVideosReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.DOCUMENT)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbDocumentsReceivedPath.substring(FileUtils.wbDocumentsReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.AUDIO)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbAudiosReceivedPath.substring(FileUtils.wbAudiosReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.VOICE)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbVoiceReceivedPath.substring(FileUtils.wbVoiceReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.WALLPAPER)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbWallReceivedPath.substring(FileUtils.wbWallReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    } else if (category.equalsIgnoreCase(FileUtils.GIF)) {
                        if (documentFile.getName() != null && documentFile.getName().equalsIgnoreCase(FileUtils.wbGifReceivedPath.substring(FileUtils.wbGifReceivedPath.lastIndexOf("/") + 1))) {
                            checkFolder = documentFile.getName();
                        }
                    }
                }

                if (documentFile != null && documentFile.isDirectory() && documentFile.getName() != null && checkFolder.equalsIgnoreCase(documentFile.getName())) {
                    DocumentFile[] listFiles = documentFile.listFiles();
                    for (DocumentFile file1 : listFiles) {
                        if (options.equalsIgnoreCase(Constant.OPTIONS2)) {
                            if (file1 != null && file1.isDirectory() && file1.getName() != null &&
                                    file1.getName().equalsIgnoreCase(path.substring(path.lastIndexOf("/") + 1))) {
                                DocumentFile[] listFiles1 = file1.listFiles();
                                for (DocumentFile file2 : listFiles1) {
                                    if (!file2.getUri().toString().contains(".nomedia") && !file2.isDirectory()) {
                                        list.add(new FileModel(file2.getName(), file2.getUri().toString(), false, Formatter.formatShortFileSize(activity, file2.length())));
                                    }
                                }
                            }
                        } else {
                            if (!file1.getUri().toString().contains(".nomedia") && !file1.isDirectory()) {
                                list.add(new FileModel(file1.getName(), file1.getUri().toString(), false, Formatter.formatShortFileSize(activity, file1.length())));
                            }
                        }
                    }
                }
            }
        }
    }


    public void checkData() {
        gbvFileBinding.includedError.progress.setVisibility(View.GONE);
        if (imageAdapter.getItemCount() > 0) {
            gbvFileBinding.includedError.tvNodata.setVisibility(View.GONE);
        } else
            gbvFileBinding.includedError.tvNodata.setVisibility(View.VISIBLE);
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

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Confirm Delete....");
            builder.setMessage("Are you sure, You Want To Delete This Status?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                Constant.showLoader(activity, "Deleting Please Wait...");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<FileModel> models = new ArrayList<>();
                        for (int data = 0; data < list.size(); data++) {
                            if (list.get(data).isSelected) {

                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && list.get(data).filePath.contains("content")) {
                                    DocumentFile file = DocumentFile.fromSingleUri(activity, Uri.parse(list.get(data).filePath));
                                    try {
                                        DocumentsContract.deleteDocument(activity.getContentResolver(), file.getUri());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    File file = new File(list.get(data).filePath);
                                    if (file.exists()) {
                                        deleteFile(activity, list.get(data).filePath);
                                    }
                                }

                                PowerPreference.getDefaultFile().putBoolean(Constant.isDelete, true);

                            } else {
                                models.add(list.get(data));
                            }
                        }

                        dellist.clear();
                        list = models;

                        activity.runOnUiThread(new Runnable() {
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
            Constant.showToast("not selected any item !");
        }
    }


    public void deleteFile(Context context, String path) {
        new File(path).delete();

        int id = getMediaIdFromPath(path, context);
        Uri uri = Uri.withAppendedPath(MediaStore.Files.getContentUri("external"), String.valueOf(id));
        context.getContentResolver().delete(
                uri,
                null, null);
    }

    @SuppressLint("Range")
    private int getMediaIdFromPath(String path, Context context) {
        final String[] columns = {MediaStore.Files.FileColumns._ID};

        Cursor cursor = null;

        int id = 0;

        try {
            cursor = context.getContentResolver().query(MediaStore.Files.getContentUri("external"),
                    columns, MediaStore.Files.FileColumns.DATA + "=? ",
                    new String[]{path}, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    id = cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return id;
    }

}