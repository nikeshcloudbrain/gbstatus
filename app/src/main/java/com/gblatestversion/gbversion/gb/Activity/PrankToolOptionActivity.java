package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gblatestversion.gbversion.gb.Activity.tools.PrankTools.FakeProfileActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.PrankTools.FakeStoryActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityPrankToolOptionBinding;

public class PrankToolOptionActivity extends AppCompatActivity {
    ActivityPrankToolOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrankToolOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this, binding.frameViewAdsMain);

        binding.fakeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(PrankToolOptionActivity.this, () -> startActivity(new Intent(PrankToolOptionActivity.this, FakeProfileActivity.class)));
            }
        });
        binding.fakeStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(PrankToolOptionActivity.this, () -> startActivity(new Intent(PrankToolOptionActivity.this, FakeStoryActivity.class)));
            }
        });

        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Prank Tool Options");

    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }
}