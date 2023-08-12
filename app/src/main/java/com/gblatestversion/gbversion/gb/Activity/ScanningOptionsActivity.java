package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gblatestversion.gbversion.gb.Activity.tools.QRTools.QrGeneratorActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.QRTools.QrScannerActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityScanningOptionsBinding;

public class ScanningOptionsActivity extends AppCompatActivity {
    ActivityScanningOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScanningOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this, binding.frameViewAdsMain);

        binding.QrScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ScanningOptionsActivity.this, () -> startActivity(new Intent(ScanningOptionsActivity.this, QrScannerActivity.class)));
            }
        });
        binding.QrGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(ScanningOptionsActivity.this, () ->startActivity(new Intent(ScanningOptionsActivity.this, QrGeneratorActivity.class)));
            }
        });

        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Scanning Tool Options");
    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }
}