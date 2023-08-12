package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityStartBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;

public class StartActivity extends AppCompatActivity {
ActivityStartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
WFSAppLoadAds.getInstance().showNativeMediaFix(this,binding.frameViewAdsMain);
        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(StartActivity.this, " unable to find market app", Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT",getString(R.string.app_name));
                    intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\nhttps://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n");
                    startActivity(Intent.createChooser(intent, "choose one"));
                } catch (Exception unused) {
                }
            }
        });

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(StartActivity.this, () -> {
                   startActivity(new Intent(StartActivity.this, MainActivity.class));
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Constant.showRateDialog(StartActivity.this, false);
    }

}