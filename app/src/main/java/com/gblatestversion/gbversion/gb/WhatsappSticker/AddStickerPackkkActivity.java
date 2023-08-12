package com.gblatestversion.gbversion.gb.WhatsappSticker;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.BuildConfig;
import com.gblatestversion.gbversion.gb.utils.Constant;


public abstract class AddStickerPackkkActivity extends AppCompatActivity {

    public void addStickerPackToWhatsApp(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction("com.whatsapp.intent.action.ENABLE_STICKER_PACK");
        intent.putExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_ID, str);
        intent.putExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_AUTHORITY, BuildConfig.CONTENT_PROVIDER_AUTHORITY);
        intent.putExtra("sticker_pack_name", str2);
        try {
            startActivityForResult(intent, 200);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Constant.showToast( "Whatsapp not Installed");
        }
    }
}
