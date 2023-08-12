package com.gblatestversion.gbversion.gb.Activity.tools.PrankTools;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.gblatestversion.gbversion.gb.Activity.tools.StoryActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityFakeStoryBinding;
import com.github.florent37.inlineactivityresult.InlineActivityResult;
import com.github.florent37.inlineactivityresult.Result;
import com.github.florent37.inlineactivityresult.callbacks.SuccessCallback;

public class FakeStoryActivity extends AppCompatActivity {

    ActivityFakeStoryBinding gbvFakeStoryBinding;
    private String profileimage = "";

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvFakeStoryBinding = ActivityFakeStoryBinding.inflate(getLayoutInflater());
        setContentView(gbvFakeStoryBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,gbvFakeStoryBinding.frameViewAds);

        setToolbar();
        startStory();
    }


    public void setToolbar() {
         gbvFakeStoryBinding.includedToolbar.title.setText("Fake Story");
        gbvFakeStoryBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startStory() {

        gbvFakeStoryBinding.txtProfile.setOnClickListener(view -> {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            new InlineActivityResult(this)
                    .startForResult(intent)
                    .onSuccess(new SuccessCallback() {
                        @Override
                        public void onSuccess(Result result) {
                            if (result.getData() != null && result.getData().getData() != null) {
                                profileimage = result.getData().getData().toString();
                                Glide.with(FakeStoryActivity.this).load(profileimage)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(gbvFakeStoryBinding.ivProfile);
                            }
                        }
                    });
        });

        gbvFakeStoryBinding.ivStory.setOnClickListener(view -> {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            new InlineActivityResult(this)
                    .startForResult(intent)
                    .onSuccess(new SuccessCallback() {
                        @Override
                        public void onSuccess(Result result) {
                            if (result.getData() != null && result.getData().getData() != null) {
                                WFSAppLoadAds.getInstance().showInterstitial(FakeStoryActivity.this, () -> {
                                    Uri selectedImage = result.getData().getData();
                                    Intent intent = new Intent(FakeStoryActivity.this, StoryActivity.class);
                                    intent.putExtra("name", gbvFakeStoryBinding.editName.getText().toString());
                                    intent.putExtra("seen", gbvFakeStoryBinding.editLastSeen.getText().toString());
                                    intent.putExtra("caption", gbvFakeStoryBinding.editCaption.getText().toString());
                                    intent.putExtra("profile", profileimage);
                                    intent.putExtra("image", selectedImage.toString());
                                    startActivity(new Intent(intent));
                                });
                            }
                        }
                    });
        });
    }
}