package com.gblatestversion.gbversion.gb.Activity.tools;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityStoryBinding;


public class StoryActivity extends AppCompatActivity {

    ActivityStoryBinding gbvStoryBinding;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvStoryBinding = ActivityStoryBinding.inflate(getLayoutInflater());
        setContentView(gbvStoryBinding.getRoot());
        setToolbar();
        StartStory();
    }

    public void setToolbar() {
        gbvStoryBinding.ivBack.setOnClickListener(view -> onBackPressed());
    }

    public void StartStory() {

        if (getIntent() != null) {
            if (getIntent().hasExtra("name")) {
                if (!getIntent().getStringExtra("name").equalsIgnoreCase(""))
                    gbvStoryBinding.tvName.setText(getIntent().getStringExtra("name") + "");
            }
            if (getIntent().hasExtra("seen")) {
                if (!getIntent().getStringExtra("seen").equalsIgnoreCase(""))
                    gbvStoryBinding.tvLastSeen.setText(getIntent().getStringExtra("seen") + "");
            }

            if (getIntent().hasExtra("caption")) {
                if (!getIntent().getStringExtra("caption").equalsIgnoreCase(""))
                    gbvStoryBinding.tvCaption.setText(getIntent().getStringExtra("caption") + "");
            }

            if (getIntent().hasExtra("profile")) {
                if (!getIntent().getStringExtra("profile").equalsIgnoreCase(""))
                    Glide.with(this).load(getIntent().getStringExtra("profile"))
                            .into(gbvStoryBinding.ivProfile);
            }

            if (getIntent().hasExtra("image")) {
                if (!getIntent().getStringExtra("image").equalsIgnoreCase(""))
                    Glide.with(this).load(getIntent().getStringExtra("image"))
                            .into(gbvStoryBinding.ivStory);
            }

        }
    }
}