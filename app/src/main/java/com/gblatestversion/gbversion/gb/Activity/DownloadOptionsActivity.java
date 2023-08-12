package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gblatestversion.gbversion.gb.Activity.tools.RecentStatusActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.StatusDownloadActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityDownloadOptionsBinding;

public class DownloadOptionsActivity extends AppCompatActivity {
ActivityDownloadOptionsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDownloadOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,binding.frameViewAdsMain);

        binding.btnRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(DownloadOptionsActivity.this, () -> startActivity(new Intent(DownloadOptionsActivity.this, RecentStatusActivity.class)));
            }
        });

        binding.btnSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(DownloadOptionsActivity.this, () -> startActivity(new Intent(DownloadOptionsActivity.this, StatusDownloadActivity.class)));

            }
        });

        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Story Saver Options");
    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

}