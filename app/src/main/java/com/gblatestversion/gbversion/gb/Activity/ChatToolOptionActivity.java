package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.gblatestversion.gbversion.gb.Activity.tools.AutoReplyActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.BlankMsgActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.DirectChatActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.EmoticonActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.WhatWebActivity;
import com.gblatestversion.gbversion.gb.WhatsappSticker.StickerPack;
import com.gblatestversion.gbversion.gb.WhatsappSticker.StickerPackDetailsActivity;
import com.gblatestversion.gbversion.gb.WhatsappSticker.StickerPackListActivity;
import com.gblatestversion.gbversion.gb.WhatsappSticker.StickerPackLoader;
import com.gblatestversion.gbversion.gb.WhatsappSticker.StickerPackValidator;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityChatToolOptionBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ChatToolOptionActivity extends AppCompatActivity {
    ActivityChatToolOptionBinding binding;
    public LoadListAsyncTask loadListAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatToolOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,binding.frameViewAdsMain);

        binding.emotionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ChatToolOptionActivity.this, () -> startActivity(new Intent(ChatToolOptionActivity.this, EmoticonActivity.class)));
            }
        });
        binding.directChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ChatToolOptionActivity.this, () ->  startActivity(new Intent(ChatToolOptionActivity.this, DirectChatActivity.class)));
            }
        });
        binding.autoRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ChatToolOptionActivity.this, () -> startActivity(new Intent(ChatToolOptionActivity.this, AutoReplyActivity.class)));

            }
        });
        binding.blankMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ChatToolOptionActivity.this, () -> startActivity(new Intent(ChatToolOptionActivity.this, BlankMsgActivity.class)));
            }
        });
        binding.linkDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ChatToolOptionActivity.this, () -> startActivity(new Intent(ChatToolOptionActivity.this, WhatWebActivity.class)));
            }
        });
        binding.stickerPck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.showLoader(ChatToolOptionActivity.this, "Loading Stickers Please Wait...");
                loadListAsyncTask = new LoadListAsyncTask(ChatToolOptionActivity.this);
                loadListAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Chat Tool Options");
    }


    public class LoadListAsyncTask extends AsyncTask<Void, Void, Pair<String, ArrayList<StickerPack>>> {

        private final WeakReference<Activity> contextWeakReference;

        LoadListAsyncTask(Activity activity) {
            this.contextWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected Pair<String, ArrayList<StickerPack>> doInBackground(Void... voids) {
            ArrayList<StickerPack> stickerPackList;
            try {
                stickerPackList = StickerPackLoader.fetchStickerPacks(ChatToolOptionActivity.this);
                if (stickerPackList.size() == 0) {
                    return new Pair<>("could not find any packs", null);
                }
                for (StickerPack stickerPack : stickerPackList) {
                    StickerPackValidator.verifyStickerPackValidity(ChatToolOptionActivity.this, stickerPack);
                }
                return new Pair<>(null, stickerPackList);
            } catch (Exception e) {
                Log.e("EntryActivity", "error fetching sticker packs", e);
                return new Pair<>(e.getMessage(), null);
            }
        }

        @Override
        protected void onPostExecute(Pair<String, ArrayList<StickerPack>> stringListPair) {

            final Activity entryActivity = contextWeakReference.get();
            if (entryActivity != null) {
                if (stringListPair.first != null) {
                    showErrorMessage(stringListPair.first);
                } else {
                    showStickerPack(stringListPair.second);
                }
            }
        }
    }

    public void showStickerPack(ArrayList<StickerPack> arrayList) {
        Constant.dismissLoader();
        WFSAppLoadAds.getInstance().showInterstitial(ChatToolOptionActivity.this, () -> {
            if (arrayList.size() > 1) {
                Intent intent = new Intent(this, StickerPackListActivity.class);
                intent.putParcelableArrayListExtra(StickerPackListActivity.EXTRA_STICKER_PACK_LIST_DATA, arrayList);
                startActivity(intent);
            } else {
                Intent intent2 = new Intent(this, StickerPackDetailsActivity.class);
                intent2.putExtra(StickerPackDetailsActivity.EXTRA_SHOW_UP_BUTTON, false);
                intent2.putExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_DATA, arrayList.get(0));
                startActivity(intent2);
            }
        });
    }

    public void showErrorMessage(String str) {
        Constant.dismissLoader();
        Log.e("Main activity", "error fetching sticker packs, " + str);
        Toast.makeText(this, "" + str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }


}