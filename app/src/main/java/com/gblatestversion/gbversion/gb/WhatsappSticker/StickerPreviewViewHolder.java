package com.gblatestversion.gbversion.gb.WhatsappSticker;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gblatestversion.gbversion.gb.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class StickerPreviewViewHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView stickerPreviewView;

    StickerPreviewViewHolder(View view) {
        super(view);
        this.stickerPreviewView = (SimpleDraweeView) view.findViewById(R.id.sticker_preview);
    }
}
