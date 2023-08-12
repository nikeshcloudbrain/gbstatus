package com.gblatestversion.gbversion.gb.Activity.tools;


import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adaptor.VPimageAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityViewImageBinding;
import com.gblatestversion.gbversion.gb.model.FileModel;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.ObjectSerializer;
import com.preference.PowerPreference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewImageActivity extends AppCompatActivity {

    ActivityViewImageBinding gbvViewImageBinding;

    ArrayList<FileModel> strings = new ArrayList<>();
    HashMap<Integer, String> dellist = new HashMap<>();
    com.gblatestversion.gbversion.gb.adaptor.VPimageAdapter VPimageAdapter;
    boolean isDelete = false;

    String finalPath = "";

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, () -> {
            if (!isDelete) {
                setResult(RESULT_CANCELED);
                finish();
            } else {
                setResult(RESULT_OK);
                finish();
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvViewImageBinding = ActivityViewImageBinding.inflate(getLayoutInflater());
        setContentView(gbvViewImageBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,gbvViewImageBinding.frameViewAds);

        setToolbar();
        listener();
        startViewImage();
    }

    public void setToolbar() {
        gbvViewImageBinding.ivBack.setOnClickListener(view -> onBackPressed());
    }


    public void listener() {
        if (getIntent() != null && getIntent().hasExtra(Constant.SAVE_OPTIONS)) {
            if (getIntent().getBooleanExtra(Constant.SAVE_OPTIONS, false)) {
                gbvViewImageBinding.llSave.setVisibility(View.VISIBLE);
            } else {
                gbvViewImageBinding.llSave.setVisibility(View.GONE);
            }
        }

        gbvViewImageBinding.llDelete.setOnClickListener(view -> {
            deleteStatus();
        });
        gbvViewImageBinding.ivBack.setOnClickListener(view -> onBackPressed());
        gbvViewImageBinding.llShare.setOnClickListener(view -> {
            if (strings.size() > 0) {
                if (Constant.isImageFile(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath)) {

                    Uri uri;
                    if (strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath.startsWith("content")) {
                        uri = Uri.parse(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                    } else {
                        uri = FileProvider.getUriForFile(ViewImageActivity.this, getApplicationContext().getPackageName() + ".provider", new File(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath));
                    }
                    if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.N)) {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            String sAux;
                            sAux = "Download App :  " + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(shareIntent, "Share image using"));
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("image/*");
                            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            String sAux = "\n" + getResources().getString(R.string.app_name) + ", Present Your Creativity\n\n ";
                            sAux = sAux + "Download Now :  " + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(shareIntent, "Share image using"));
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                if (Constant.isVideoFile(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath)) {
                    Uri uri;
                    if (strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath.startsWith("content")) {
                        uri = Uri.parse(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                    } else {
                        uri = FileProvider.getUriForFile(ViewImageActivity.this, getApplicationContext().getPackageName() + ".provider", new File(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath));
                    }
                    if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.N)) {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("video/*");
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            String sAux;
                            sAux = "Download App :  " + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(shareIntent, "Share video using"));
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("video/*");
                            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            String sAux = "\n" + getResources().getString(R.string.app_name) + ", Present Your Creativity\n\n ";
                            sAux = sAux + "Download Now :  " + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(shareIntent, "Share Video using"));
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                return;
            }
            finish();


        });
        gbvViewImageBinding.llRepost.setOnClickListener(view -> {

            if (strings.size() > 0) {
                if (Constant.isImageFile(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath)) {
                    Uri uri;
                    if (strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath.startsWith("content")) {
                        uri = Uri.parse(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                    } else {
                        uri = FileProvider.getUriForFile(ViewImageActivity.this, getApplicationContext().getPackageName() + ".provider", new File(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath));
                    }
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("image/*");
                    intent.setPackage("com.whatsapp");
                    intent.putExtra("android.intent.extra.STREAM", uri);
                    startActivity(Intent.createChooser(intent, "Share Image!"));
                    return;
                }
                if (Constant.isVideoFile(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath)) {
                    Uri uri;
                    if (strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath.startsWith("content")) {
                        uri = Uri.parse(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                    } else {
                        uri = FileProvider.getUriForFile(ViewImageActivity.this, getApplicationContext().getPackageName() + ".provider", new File(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath));
                    }
                    Intent intent2 = new Intent("android.intent.action.SEND");
                    intent2.setType("*/*");
                    intent2.setPackage("com.whatsapp");
                    intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent2.putExtra("android.intent.extra.STREAM", uri);
                    startActivity(intent2);
                    return;
                }
            } else {
                finish();
            }
        });

        gbvViewImageBinding.llSave.setOnClickListener(view -> {
            if (strings.size() > 0) {
                try {
                    download(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                } catch (Exception unused) {
                    Constant.showToast("Sorry we can't move file.try with other file.");
                }
            } else {
                finish();
            }
        });
    }


    public void download(String str) throws UnsupportedEncodingException {
        if (copyFileInSavedDir(this, str)) {
            Toast.makeText(this, "Status is Saved Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to Download", Toast.LENGTH_SHORT).show();
        }
    }


    static boolean copyFileInSavedDir(Context context, String str) {

        Uri uri;
        if (str.startsWith("content")) {
            uri = Uri.parse(str);
        } else {
            uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(str));
        }
        String mimeType = context.getContentResolver().getType(uri);
        String filename;
        if (mimeType == null) {
            String path = getPath(context, uri);
            if (path == null) {
                filename = getFileName(context, uri);
            } else {
                File file = new File(path);
                filename = file.getName();
            }
            Log.e("filename1: ", "" + filename);
        } else {
            Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            returnCursor.moveToFirst();
            filename = returnCursor.getString(nameIndex);
            Log.e("filename2: ", "" + filename);
        }

        File file;
        if (filename.contains("jpeg") || filename.contains("png") || filename.contains("jpg") || filename.contains(".gif")) {
            file = getDir(context, "Images");
            if (!file.exists()) {
                file.mkdirs();
            }
            String sourcePath = file.getAbsolutePath();
            File fileSave = new File(sourcePath + "/" + filename);
            try {
                copyFileStream(fileSave, uri, context);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception: ", "" + e.getLocalizedMessage());
            }
            mediaScanner(context, getDir(context, "Images") + "/", fileSave.getName(), "image/*");
        } else {
            file = getDir(context, "Videos");
            if (!file.exists()) {
                file.mkdirs();
            }
            String sourcePath = file.getAbsolutePath();
            File fileSave = new File(sourcePath + "/" + filename);
            try {
                copyFileStream(fileSave, uri, context);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception123: ", "" + e.getLocalizedMessage());
            }
            mediaScanner(context, getDir(context, "Videos") + "/", fileSave.getName(), "video/*");
        }
        return true;
    }

    static File getDir(Context context, String str) {
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "DCIM" + File.separator + context.getResources().getString(R.string.folder_output) + File.separator + str);
        file.mkdirs();
        return file;
    }

    public static String getFileName(Context context, Uri uri) {
        if (uri != null && context != null) {
            Cursor returnCursor =
                    context.getContentResolver().query(uri, null, null, null, null);
            if (returnCursor != null) {
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);

                returnCursor.moveToFirst();
                if (nameIndex >= 0 && sizeIndex >= 0) {
                    Log.e("File Name : ", "" + returnCursor.getString(nameIndex));
                    Log.e("File Size : ", "" + Long.toString(returnCursor.getLong(sizeIndex)));
                    return returnCursor.getString(nameIndex);
                }
            }
        }

        return null;
    }


    public static String getPath(Context context, Uri uri) {

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static void copyFileStream(File dest, Uri uri, Context context) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mediaScanner(Context context, String str, String str2, String str3) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{str + new File(str2).getName()}, new String[]{str3}, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.example.jdjd.activity.GL_Utils.1
                @Override
                public void onMediaScannerConnected() {
                }

                @Override
                public void onScanCompleted(String str4, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startViewImage() {
        try {
            strings = (ArrayList<FileModel>) ObjectSerializer.deserialize(PowerPreference.getDefaultFile().getString("wastatus"));
            dellist = (HashMap<Integer, String>) ObjectSerializer.deserialize(PowerPreference.getDefaultFile().getString("wastatus2"));
            VPimageAdapter = new VPimageAdapter(this, strings);
            gbvViewImageBinding.vpImages.setAdapter(VPimageAdapter);
            gbvViewImageBinding.vpImages.setCurrentItem(PowerPreference.getDefaultFile().getInt("position", 0), false);
        } catch (Exception e) {
            Constant.showToast("Something went wrong");
        }

    }

    public void deleteStatus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete....");
        builder.setMessage("Are you sure, You Want To Delete This Status?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            Constant.showLoader(ViewImageActivity.this,"Deleting Please Wait...");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath.contains("content")) {
                        DocumentFile file = DocumentFile.fromSingleUri(ViewImageActivity.this, Uri.parse(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath));
                        try {
                            DocumentsContract.deleteDocument(ViewImageActivity.this.getContentResolver(), file.getUri());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        File file = new File(strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                        if (file.exists()) {
                            deleteFile(ViewImageActivity.this, strings.get(gbvViewImageBinding.vpImages.getCurrentItem()).filePath);
                        }
                    }

                    isDelete = true;
                    PowerPreference.getDefaultFile().putBoolean(Constant.isDelete, true);


                    final int[] oldPos = {gbvViewImageBinding.vpImages.getCurrentItem()};
                    if (dellist.containsKey(oldPos[0]))
                        dellist.remove(oldPos[0]);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            strings.remove(oldPos[0]);
                            VPimageAdapter.notifyDataSetChanged();

                            if (oldPos[0] != 0) {
                                oldPos[0]--;
                                gbvViewImageBinding.vpImages.setCurrentItem(oldPos[0], false);
                            }

                            try {
                                PowerPreference.getDefaultFile().putString("wastatus", ObjectSerializer.serialize(strings));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                PowerPreference.getDefaultFile().putString("wastatus2", ObjectSerializer.serialize(dellist));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Constant.dismissLoader();
                            if (VPimageAdapter.getItemCount() <= 0) {
                                onBackPressed();
                            }
                        }
                    });
                }
            }).start();


        });
        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();
    }


    public void deleteFile(Context context, String path) {
        new File(path).delete();

        int id = getMediaIdFromPath(path, context);
        Uri uri = Uri.withAppendedPath(MediaStore.Files.getContentUri("external"), String.valueOf(id));
        context.getContentResolver().delete(
                uri,
                null, null);
    }

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