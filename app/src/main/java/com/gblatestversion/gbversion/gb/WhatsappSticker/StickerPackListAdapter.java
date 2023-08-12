/*
 * Copyright (c) WhatsApp Inc. and its affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.gblatestversion.gbversion.gb.WhatsappSticker;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class StickerPackListAdapter extends RecyclerView.Adapter<StickerPackListItemViewHolder> {
    @NonNull
    private List<StickerPack> stickerPacks;
    @NonNull
    private final OnAddButtonClickedListener onAddButtonClickedListener;
    private int maxNumberOfStickersInARow;
    private int minMarginBetweenImages;

    StickerPackListAdapter(@NonNull List<StickerPack> stickerPacks, @NonNull OnAddButtonClickedListener onAddButtonClickedListener) {
        this.stickerPacks = stickerPacks;
        this.onAddButtonClickedListener = onAddButtonClickedListener;
    }

    @NonNull
    @Override
    public StickerPackListItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        final Context context = viewGroup.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View stickerPackRow = layoutInflater.inflate(R.layout.sticker_packs_list_item, viewGroup, false);
        return new StickerPackListItemViewHolder(stickerPackRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final StickerPackListItemViewHolder viewHolder, final int index) {
        StickerPack pack = stickerPacks.get(index);
        final Context context = viewHolder.publisherView.getContext();
        viewHolder.publisherView.setText(pack.publisher);
        viewHolder.filesizeView.setText(Formatter.formatShortFileSize(context, pack.getTotalSize()));

        viewHolder.titleView.setText(pack.name);
        viewHolder.container.setOnClickListener(view -> {
            WFSAppLoadAds.getInstance().showInterstitial((StickerPackListActivity)context, () -> {
                Intent intent = new Intent(view.getContext(), StickerPackDetailsActivity.class);
                intent.putExtra(StickerPackDetailsActivity.EXTRA_SHOW_UP_BUTTON, true);
                intent.putExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_DATA, pack);
                view.getContext().startActivity(intent);
            });

        });
        viewHolder.imageRowView.removeAllViews();
        int actualNumberOfStickersToShow = Math.min(maxNumberOfStickersInARow, pack.getStickers().size());
        for (int i = 0; i < actualNumberOfStickersToShow; i++) {
            final SimpleDraweeView rowImage = (SimpleDraweeView) LayoutInflater.from(context).inflate(R.layout.sticker_pack_list_item_image, viewHolder.imageRowView, false);
            rowImage.setImageURI(StickerPackLoader.getStickerAssetUri(pack.identifier, pack.getStickers().get(i).imageFileName));
            final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rowImage.getLayoutParams();
            final int marginBetweenImages = minMarginBetweenImages - lp.leftMargin - lp.rightMargin;
            if (i != actualNumberOfStickersToShow - 1 && marginBetweenImages > 0) { //do not set the margin for the last image
                lp.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin + marginBetweenImages, lp.bottomMargin);
                rowImage.setLayoutParams(lp);
            }
            viewHolder.imageRowView.addView(rowImage);
        }

        if (pack.isWhitelisted) {
            viewHolder.addButton.setImageResource(R.drawable.ic_baseline_check_24);
        }else{
            viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAddButtonClickedListener.onAddButtonClicked(viewHolder.getAdapterPosition(),pack);
                }
            });
        }
      //  setAddButtonAppearance(viewHolder.addButton, pack);
    }

/*    private void setAddButtonAppearance(ImageView addButton, StickerPack pack) {
        if (pack.getIsWhitelisted()) {

            addButton.setClickable(false);
            addButton.setOnClickListener(null);
            setBackground(addButton, null);
        } else {
            addButton.setImageResource(R.drawable.sticker_add);
            addButton.setOnClickListener(v -> onAddButtonClickedListener.onAddButtonClicked(pack));
            TypedValue outValue = new TypedValue();
            addButton.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            addButton.setBackgroundResource(outValue.resourceId);
        }
    }*/

    private void setBackground(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    @Override
    public int getItemCount() {
        return stickerPacks.size();
    }

    void setImageRowSpec(int maxNumberOfStickersInARow, int minMarginBetweenImages) {
        this.minMarginBetweenImages = minMarginBetweenImages;
        if (this.maxNumberOfStickersInARow != maxNumberOfStickersInARow) {
            this.maxNumberOfStickersInARow = maxNumberOfStickersInARow;
            notifyDataSetChanged();
        }
    }

    void setStickerPackList(List<StickerPack> stickerPackList) {
        this.stickerPacks = stickerPackList;
    }

    public interface OnAddButtonClickedListener {
        void onAddButtonClicked(int position,StickerPack stickerPack);
    }
}
