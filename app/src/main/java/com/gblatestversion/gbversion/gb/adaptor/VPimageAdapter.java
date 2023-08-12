package com.gblatestversion.gbversion.gb.adaptor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gblatestversion.gbversion.gb.Activity.tools.ViewVideoActivity;
import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ItemVpimageBinding;
import com.gblatestversion.gbversion.gb.model.FileModel;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.preference.PowerPreference;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class VPimageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    public List<FileModel> stringList = new ArrayList<>();
    String finalPath = "";

    public VPimageAdapter(Activity activity, List<FileModel> list) {
        this.activity = activity;
        stringList = list;
    }

    public void refresh(List<FileModel> holder) {
        stringList = holder;
        notifyDataSetChanged();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ItemVpimageBinding binding;

        public ImageHolder(@NonNull ItemVpimageBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(ItemVpimageBinding.inflate(LayoutInflater.from(activity), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageHolder imageHolder = (ImageHolder) holder;

        if (Constant.isVideoFile(stringList.get(position).filePath)) {
            imageHolder.binding.ivPlay.setVisibility(View.VISIBLE);
        } else {
            imageHolder.binding.ivPlay.setVisibility(View.GONE);
        }

        Glide.with(activity)
                .load(stringList.get(position).filePath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageHolder.binding.ivImage);

        imageHolder.itemView.setOnClickListener(view -> {
            if (Constant.isVideoFile(stringList.get(holder.getAdapterPosition()).filePath)) {
                    PowerPreference.getDefaultFile().putString(Constant.cPath, stringList.get(holder.getAdapterPosition()).filePath);
                    WFSAppLoadAds.getInstance().showInterstitial(activity, () -> activity.startActivity(new Intent(activity, ViewVideoActivity.class)));


            }
        });
    }

    public boolean copyFileInSavedDir(String str, boolean isShared) {
        try {
            if (Constant.isImageFile(str)) {
                copyFile(Constant.getImagesFolder(), str, isShared);
                Constant.mediaScanner(activity, Constant.getImagesFolder(), str, "image/*");
                return true;
            }
            copyFile(Constant.getVideosFolder(), str, isShared);
            Constant.mediaScanner(activity, Constant.getVideosFolder(), str, "video/*");
            return true;
        } catch (Exception e) {
            Log.e("TAG", e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public boolean copyFile(String outputFile, String inputFile, boolean page) {

        try {

            if (Constant.isImageFile(inputFile)) {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), Uri.parse(inputFile));
                String filename = System.currentTimeMillis() + ".jpg";

                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
                if (page) {
                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/" + activity.getResources().getString(R.string.folder_output) + "/shared/");
                } else {
                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/" + activity.getResources().getString(R.string.folder_output) + "/");
                }
                Uri uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                OutputStream outputStream = activity.getContentResolver().openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                finalPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + activity.getResources().getString(R.string.folder_output) + "/" + filename;
                Constant.mediaScanner(activity, Constant.getImagesFolder(), uri.toString(), "image/*");

            } else {
                InputStream inputStream = activity.getContentResolver().openInputStream(Uri.parse(inputFile));
                String filename = System.currentTimeMillis() + ".mp4";
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "Video/mp4");
                if (page) {
                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/" + activity.getResources().getString(R.string.folder_output) + "/shared/");
                } else {
                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/" + activity.getResources().getString(R.string.folder_output) + "/");
                }
                Uri uri = activity.getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);

                OutputStream stream = activity.getContentResolver().openOutputStream(uri);
                if (inputStream != null) {
                    int read;
                    byte[] bytes = new byte[1024];

                    while ((read = inputStream.read(bytes)) != -1) {
                        stream.write(bytes, 0, read);
                    }
                    stream.close();
                }
                finalPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + activity.getResources().getString(R.string.folder_output) + "/" + filename;
                Constant.mediaScanner(activity, outputFile, uri.toString(), "video/*");
            }
            return true;
        } catch (Exception fnfe1) {
            fnfe1.printStackTrace();
            Log.e("TAG", fnfe1.toString());

        }
        return false;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}

