package com.gblatestversion.gbversion.gb.Activity.tools.QRTools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ActivityQrScannerBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.github.florent37.inlineactivityresult.InlineActivityResult;
import com.github.florent37.inlineactivityresult.Result;
import com.github.florent37.inlineactivityresult.callbacks.SuccessCallback;

public class QrScannerActivity extends AppCompatActivity {

    ActivityQrScannerBinding gbvQrScannerBinding;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvQrScannerBinding = ActivityQrScannerBinding.inflate(getLayoutInflater());
        setContentView(gbvQrScannerBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvQrScannerBinding.frameViewAdsMain);

        setToolbar();
        startApp();
    }

    public void setToolbar() {
        gbvQrScannerBinding.includedToolbar.title.setText("QR Scanner");
        gbvQrScannerBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startApp() {
        gbvQrScannerBinding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gbvQrScannerBinding.txtMessage.setText("");
            }
        });

        gbvQrScannerBinding.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gbvQrScannerBinding.txtMessage.getText().toString().equalsIgnoreCase("")) {
                    Constant.showToast("Please Scan QR Code First");
                } else {
                    Constant.shareApp(QrScannerActivity.this, gbvQrScannerBinding.txtMessage.getText().toString());
                }
            }
        });

        gbvQrScannerBinding.txtGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });
    }

    public void startScan() {
        new InlineActivityResult(this)
                .startForResult(new Intent(this, StartQrScannerActivity.class))
                .onSuccess(new SuccessCallback() {
                    @Override
                    public void onSuccess(Result result) {
                        if (result != null && result.getData() != null && result.getData().getStringExtra("data") != null) {
                            gbvQrScannerBinding.txtMessage.setText(result.getData().getStringExtra("data") + "");
                        }
                    }
                });

    }
}