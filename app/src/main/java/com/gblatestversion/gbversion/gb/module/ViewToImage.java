package com.gblatestversion.gbversion.gb.module;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class ViewToImage {

    Context context;
    ActionListeners listeners;
    String folderName = "App";
    String fileName = "myFile";
    View view;
    Bitmap bitmap = null;
    String filePath = null;

    public ViewToImage(Context context, View view, String folderName,  ActionListeners listeners) {
        this.context = context;
        this.listeners = listeners;
        this.folderName = folderName;
        this.view = view;
        this.convert();
    }

    private void convert() {
        if (view != null && view.getHeight() > 0 && view.getWidth() > 0) {
            Bitmap bitmap = this.getBitmapFromView(this.view, this.view.getWidth(), this.view.getHeight());
            this.saveTheImage(bitmap);
        } else {
            if (this.listeners != null) {
                this.listeners.convertedWithError("Something went wrong");
            }
        }
    }

    private Bitmap getBitmapFromView(View view, int width, int height) {
        this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(this.bitmap);
        view.layout(0, 0, view.getLayoutParams().width, view.getLayoutParams().height);
        view.draw(mCanvas);
        return this.bitmap;
    }

    private void saveTheImage(Bitmap finalBitmap) {

        fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(folderName, fileName);

        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            this.filePath =folderName + File.separator + fileName;

            if (this.listeners != null) {
                this.listeners.convertedWithSuccess(this.bitmap, this.filePath, file);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            if (this.listeners != null) {
                this.listeners.convertedWithError(var9.getMessage());
            }
        }

    }
}