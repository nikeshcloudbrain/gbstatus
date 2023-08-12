package com.gblatestversion.gbversion.gb.Activity.tools.QRTools;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityQrGeneratorBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

public class QrGeneratorActivity extends AppCompatActivity {

    ActivityQrGeneratorBinding gbvQrGeneratorBinding;
    boolean isGenerated = false;
    Bitmap bitmap = null;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvQrGeneratorBinding = ActivityQrGeneratorBinding.inflate(getLayoutInflater());
        setContentView(gbvQrGeneratorBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvQrGeneratorBinding.frameViewAdsMain);

        setToolbar();
        startApp();
        gbvQrGeneratorBinding.ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gbvQrGeneratorBinding.editMessage.setText("");
            }
        });
    }

    public void setToolbar() {
        gbvQrGeneratorBinding.includedToolbar.title.setText("QR Generator");
        gbvQrGeneratorBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startApp() {
        gbvQrGeneratorBinding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gbvQrGeneratorBinding.editMessage.setText("");
                gbvQrGeneratorBinding.ivPreview.setImageDrawable(null);
                gbvQrGeneratorBinding.txtMessage.setVisibility(View.VISIBLE);
                gbvQrGeneratorBinding.ivPreview.setVisibility(View.GONE);
                isGenerated = false;
            }
        });

        gbvQrGeneratorBinding.txtGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gbvQrGeneratorBinding.editMessage.getText().toString().equalsIgnoreCase("")) {
                    Constant.showToast(Constant.emptyError);
                } else {
                    Constant.showLoader(QrGeneratorActivity.this, "Generating QR Please Wait...");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                bitmap = textToImageEncode(gbvQrGeneratorBinding.editMessage.getText().toString());
                                gbvQrGeneratorBinding.ivPreview.setImageBitmap(null);
                                gbvQrGeneratorBinding.ivPreview.setImageBitmap(bitmap);
                                isGenerated = true;

                                gbvQrGeneratorBinding.txtMessage.setVisibility(View.GONE);
                                gbvQrGeneratorBinding.ivPreview.setVisibility(View.VISIBLE);
                                Constant.dismissLoader();

                            } catch (Exception e) {
                                e.printStackTrace();
                                isGenerated = false;
                            }
                        }
                    }, 1000);


                }
            }
        });
        gbvQrGeneratorBinding.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGenerated) {
                    Constant.showLoader(QrGeneratorActivity.this, "Sharing Please Wait...");

                   /* new ViewToImage(CguQrGeneratorActivity.this, gbvQrGeneratorBinding.framePreview, Utils.getQRFolder(), new ActionListeners() {
                        @Override
                        public void convertedWithSuccess(Bitmap var1, String var2, File file) {
                            if (bitmap != null) {
                                gbvQrGeneratorBinding.ivPreview.setImageBitmap(null);
                                gbvQrGeneratorBinding.ivPreview.setImageBitmap(bitmap);
                            }
                            Utils.dismissLoader();
                            share(file);
                        }

                        @Override
                        public void convertedWithError(String var1) {
                            if (bitmap != null) {
                                gbvQrGeneratorBinding.ivPreview.setImageBitmap(null);
                                gbvQrGeneratorBinding.ivPreview.setImageBitmap(bitmap);
                            }
                            Utils.dismissLoader();
                            Utils.showToast("Something went wrong");
                        }
                    });*/

                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("image/jpeg");

                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "title");
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);


                    OutputStream outstream;
                    try {
                        outstream = getContentResolver().openOutputStream(uri);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                        outstream.close();
                    } catch (Exception e) {
                        System.err.println(e.toString());
                    }

                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(share, "Share Image"));
                    Constant.dismissLoader();
                } else {
                    Constant.showToast(Constant.emptyError);
                }
            }
        });
    }

    public void share(File file) {
        Uri uri;
        uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);

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
    }

    public Bitmap textToImageEncode(String str) {
        int i;
        Resources resources;
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix encode = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 500, 500, (Map<EncodeHintType, ?>) null);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[(width * height)];
            for (int i2 = 0; i2 < height; i2++) {
                int i3 = i2 * width;
                for (int i4 = 0; i4 < width; i4++) {
                    int i5 = i3 + i4;
                    if (encode.get(i4, i2)) {
                        resources = getResources();
                        i = android.R.color.black;
                    } else {
                        resources = getResources();
                        i = android.R.color.white;
                    }
                    iArr[i5] = resources.getColor(i);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
            createBitmap.setPixels(iArr, 0, 500, 0, 0, width, height);
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }
}