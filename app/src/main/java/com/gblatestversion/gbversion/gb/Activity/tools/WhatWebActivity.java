package com.gblatestversion.gbversion.gb.Activity.tools;


import android.os.Bundle;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityWhatWebBinding;


public class WhatWebActivity extends AppCompatActivity {

    ActivityWhatWebBinding gbvWhatWebBinding;

    @Override
    protected void onResume() {
        super.onResume();

    }
     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvWhatWebBinding = ActivityWhatWebBinding.inflate(getLayoutInflater());
        setContentView(gbvWhatWebBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,gbvWhatWebBinding.frameViewAds);
        reload();
        startWeb();
    }

    public void startWeb() {
        gbvWhatWebBinding.icRefresh.setOnClickListener(view -> reload());
        gbvWhatWebBinding.ivBackMain.setOnClickListener(view -> onBackPressed());
    }

    public void reload() {
        gbvWhatWebBinding.webview.setWebViewClient(new WebViewClient());
        gbvWhatWebBinding.webview.getSettings().setDomStorageEnabled(true);
        gbvWhatWebBinding.webview.getSettings().setJavaScriptEnabled(true);
        gbvWhatWebBinding.webview.loadUrl("https://web.whatsapp.com/");
        gbvWhatWebBinding.webview.getSettings().setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
    }
}