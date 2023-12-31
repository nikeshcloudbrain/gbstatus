package com.gblatestversion.gbversion.gb.WhatsappSticker;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.R;

import java.io.FileNotFoundException;

public class StickerPackInfoActivity extends AppCompatActivity {
    private static final String TAG = "StickerPackInfoActivity";


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_sticker_pack_info);
        String stringExtra = getIntent().getStringExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_TRAY_ICON);
        final String stringExtra2 = getIntent().getStringExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_WEBSITE);
        final String stringExtra3 = getIntent().getStringExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_EMAIL);
        final String stringExtra4 = getIntent().getStringExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_PRIVACY_POLICY);
        TextView textView = (TextView) findViewById(R.id.tray_icon);
        try {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), getContentResolver().openInputStream(Uri.parse(stringExtra)));
            Drawable drawableForAllAPIs = getDrawableForAllAPIs(R.drawable.ic_baseline_email_24);
            bitmapDrawable.setBounds(new Rect(0, 0, drawableForAllAPIs.getIntrinsicWidth(), drawableForAllAPIs.getIntrinsicHeight()));
            textView.setCompoundDrawables(bitmapDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } catch (FileNotFoundException unused) {
            Log.e(TAG, "could not find the uri for the tray image:" + stringExtra);
        }
        TextView textView2 = (TextView) findViewById(R.id.view_webpage);
        if (TextUtils.isEmpty(stringExtra2)) {
            textView2.setVisibility(View.GONE);
        } else {
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchWebpage(stringExtra2);
                }
            });
        }
        TextView textView3 = (TextView) findViewById(R.id.send_email);
        if (TextUtils.isEmpty(stringExtra3)) {
            textView3.setVisibility(View.GONE);
        } else {
            textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchEmailClient(stringExtra3);
                }
            });
        }

    }

    private void launchEmailClient(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", str, (String) null));
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.info_send_email_to_prompt)));
    }

    private void launchWebpage(String str) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    private Drawable getDrawableForAllAPIs(@DrawableRes int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getDrawable(i);
        }
        return getResources().getDrawable(i);
    }
}
