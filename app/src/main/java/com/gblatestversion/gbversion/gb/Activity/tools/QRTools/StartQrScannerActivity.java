package com.gblatestversion.gbversion.gb.Activity.tools.QRTools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class StartQrScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mScannerView = new ZXingScannerView(this);
        setContentView((View) this.mScannerView);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();


        this.mScannerView.setResultHandler(this);
        this.mScannerView.startCamera();
    }

    public void handleResult(Result result) {
        Intent intent = getIntent();
        intent.putExtra("data", result.getText());
        setResult(RESULT_OK, getIntent());
        finish();
    }


    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, () -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
