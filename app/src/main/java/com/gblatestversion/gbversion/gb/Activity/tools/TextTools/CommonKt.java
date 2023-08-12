package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;


import com.gblatestversion.gbversion.gb.R;

import java.util.Objects;

import kotlin.jvm.internal.Intrinsics;

public class CommonKt {
    public static final boolean copyText(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, "copyText");
        Intrinsics.checkNotNullParameter(context, "context");
        if (str.length() <= 0) {
            return false;
        }
        Object systemService = context.getSystemService(Context.CLIPBOARD_SERVICE);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(context.getResources().getString(R.string.copied_text), str));
        return true;
    }

    public static final boolean shareText(String str, Context context) {
        Intrinsics.checkNotNullParameter(str, "shareText");
        Intrinsics.checkNotNullParameter(context, "context");
        if (str.length() <= 0) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/*");
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share_title)));
        return true;
    }
}
