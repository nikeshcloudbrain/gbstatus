package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gblatestversion.gbversion.gb.Activity.tools.SpeechToTextActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.TextEmojiActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.TextMagicActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.TextRepeaterActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.MirriorActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.NameTextListActivity;
import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.NumericListActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityTextEffectOptionBinding;

public class TextEffectOptionActivity extends AppCompatActivity {
    ActivityTextEffectOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextEffectOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this, binding.frameViewAdsMain);

        binding.txtMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, TextMagicActivity.class)));
            }
        });
        binding.txtRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, TextRepeaterActivity.class)));
            }
        });
        binding.txtToEmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, TextEmojiActivity.class)));
            }
        });
        binding.MirrorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, MirriorActivity.class)));

            }
        });
        binding.NameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, NameTextListActivity.class)));

            }
        });
        binding.NumTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, NumericListActivity.class)));

            }
        });
        binding.txtStT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WFSAppLoadAds.getInstance().showInterstitial(TextEffectOptionActivity.this, () ->startActivity(new Intent(TextEffectOptionActivity.this, SpeechToTextActivity.class)));

            }
        });
        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Text Effect Options");
    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }
}