package com.gblatestversion.gbversion.gb.WhatsappSticker;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ActivityStickerPackListBinding;
import com.gblatestversion.gbversion.gb.utils.uiController;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StickerPackListActivity extends AddStickerPackkkActivity {
    public static final String EXTRA_STICKER_PACK_LIST_DATA = "sticker_pack_list";
    private static final int STICKER_PREVIEW_DISPLAY_LIMIT = 5;
    private LinearLayoutManager packLayoutManager;
    private RecyclerView packRecyclerView;
    private StickerPackListAdapter allStickerPacksListAdapter;
    private WhiteListCheckAsyncTask whiteListCheckAsyncTask;
    private ArrayList<StickerPack> stickerPackList;

    ImageView ivBack;

    @Override
    public void onBackPressed() {
        uiController.onBackPressed(this);
    }

    @Override
    protected void onResume() {
        super.onResume();


        whiteListCheckAsyncTask = new WhiteListCheckAsyncTask(this);
        whiteListCheckAsyncTask.execute(stickerPackList.toArray(new StickerPack[0]));

    }

    ActivityStickerPackListBinding binding;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStickerPackListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(StickerPackListActivity.this,binding.frameViewAds);
        packRecyclerView = findViewById(R.id.sticker_pack_list);
        stickerPackList = getIntent().getParcelableArrayListExtra(EXTRA_STICKER_PACK_LIST_DATA);
        showStickerPackList(stickerPackList);
        TextView textView = findViewById(R.id.title);
        textView.setText("Sticker Packs");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getQuantityString(R.string.title_activity_sticker_packs_list, stickerPackList.size()));
        }

        ivBack = findViewById(R.id.icBack);

        setToolbar();
    }


    public void setToolbar() {
        ivBack.setOnClickListener(view -> onBackPressed());
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (whiteListCheckAsyncTask != null && !whiteListCheckAsyncTask.isCancelled()) {
            whiteListCheckAsyncTask.cancel(true);
        }
    }

    private void showStickerPackList(List<StickerPack> stickerPackList) {
        allStickerPacksListAdapter = new StickerPackListAdapter(stickerPackList, new StickerPackListAdapter.OnAddButtonClickedListener() {
            @Override
            public void onAddButtonClicked(int position, StickerPack stickerPack) {
                addStickerPackToWhatsApp(stickerPack.identifier, stickerPack.name);
            }
        });
        packRecyclerView.setAdapter(allStickerPacksListAdapter);
        packLayoutManager = new LinearLayoutManager(this);
        packLayoutManager.setOrientation(RecyclerView.VERTICAL);
        packRecyclerView.setLayoutManager(packLayoutManager);
        packRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(this::recalculateColumnCount);
    }


    private void recalculateColumnCount() {
        final int previewSize = getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._40sdp);
        int firstVisibleItemPosition = packLayoutManager.findFirstVisibleItemPosition();
        StickerPackListItemViewHolder viewHolder = (StickerPackListItemViewHolder) packRecyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition);
        if (viewHolder != null) {
            final int widthOfImageRow = viewHolder.imageRowView.getMeasuredWidth();
            final int max = Math.max(widthOfImageRow / previewSize, 1);
            int maxNumberOfImagesInARow = Math.min(STICKER_PREVIEW_DISPLAY_LIMIT, max);
            int minMarginBetweenImages = (widthOfImageRow - maxNumberOfImagesInARow * previewSize) / (maxNumberOfImagesInARow - 1);
            allStickerPacksListAdapter.setImageRowSpec(maxNumberOfImagesInARow, minMarginBetweenImages);
        }
    }


    static class WhiteListCheckAsyncTask extends AsyncTask<StickerPack, Void, List<StickerPack>> {
        private final WeakReference<StickerPackListActivity> stickerPackListActivityWeakReference;

        WhiteListCheckAsyncTask(StickerPackListActivity stickerPackListActivity) {
            this.stickerPackListActivityWeakReference = new WeakReference<>(stickerPackListActivity);
        }

        @Override
        protected final List<StickerPack> doInBackground(StickerPack... stickerPackArray) {
            final StickerPackListActivity stickerPackListActivity = stickerPackListActivityWeakReference.get();
            if (stickerPackListActivity == null) {
                return Arrays.asList(stickerPackArray);
            }
            for (StickerPack stickerPack : stickerPackArray) {
                stickerPack.setIsWhitelisted(WhitelistCheck.isWhitelisted(stickerPackListActivity, stickerPack.identifier));
            }
            return Arrays.asList(stickerPackArray);
        }

        @Override
        protected void onPostExecute(List<StickerPack> stickerPackList) {
            final StickerPackListActivity stickerPackListActivity = stickerPackListActivityWeakReference.get();
            if (stickerPackListActivity != null) {
                stickerPackListActivity.allStickerPacksListAdapter.setStickerPackList(stickerPackList);
                stickerPackListActivity.allStickerPacksListAdapter.notifyDataSetChanged();
            }
        }
    }
}
