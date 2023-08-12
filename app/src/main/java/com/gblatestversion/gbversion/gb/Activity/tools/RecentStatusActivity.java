package com.gblatestversion.gbversion.gb.Activity.tools;


import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.Fragments.FileFragment;
import com.gblatestversion.gbversion.gb.databinding.ActivityRecentStatusBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FileUtils;
import com.google.android.material.tabs.TabLayoutMediator;
import com.preference.PowerPreference;

import java.io.File;

public class RecentStatusActivity extends AppCompatActivity {

    ActivityRecentStatusBinding gbvRecentStatusBinding;
    com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter VPFragmentAdapter;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onResume() {
        super.onResume();

        PowerPreference.getDefaultFile().getBoolean(Constant.isDelete, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvRecentStatusBinding = ActivityRecentStatusBinding.inflate(getLayoutInflater());
        setContentView(gbvRecentStatusBinding.getRoot());
        PowerPreference.getDefaultFile().putBoolean(Constant.OPTIONS1, false);
        PowerPreference.getDefaultFile().putBoolean(Constant.OPTIONS2, false);

        setToolbar();
        startRecent();
    }

    public void setToolbar() {
        gbvRecentStatusBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startRecent() {
        VPFragmentAdapter = new VPFragmentAdapter(getSupportFragmentManager(), getLifecycle());

        VPFragmentAdapter.addFragment(FileFragment.newInstance(this, FileUtils.IMAGE, getWhatsupStatusFolder(), Constant.OPTIONS1), "Whatsapp");
        VPFragmentAdapter.addFragment(FileFragment.newInstance(this, FileUtils.IMAGE, getWhatsupBusinessFolder(), Constant.OPTIONS2), "WA Business");

        gbvRecentStatusBinding.vpRS.setAdapter(VPFragmentAdapter);
        gbvRecentStatusBinding.vpRS.setOffscreenPageLimit(2);

        new TabLayoutMediator(gbvRecentStatusBinding.tabRS, gbvRecentStatusBinding.vpRS, (tab, position) -> tab.setText(VPFragmentAdapter.StringArrayList.get(position))).attach();
    }



    public String getWhatsupStatusFolder() {
        if (Build.VERSION.SDK_INT <= 30) {
            return Environment.getExternalStorageDirectory() + File.separator + "WhatsApp" + File.separator + "Media" + File.separator + ".Statuses";
        } else {
            return Environment.getExternalStorageDirectory() + File.separator + "Android/media/com.whatsapp/WhatsApp/Media/.Statuses";
        }
    }

    public String getWhatsupBusinessFolder() {
        if (Build.VERSION.SDK_INT <= 30) {
            return Environment.getExternalStorageDirectory() + File.separator + "WhatsApp Business" + File.separator + "Media" + File.separator + ".Statuses";
        } else {
            return Environment.getExternalStorageDirectory() + File.separator + "Android/media/com.whatsapp.w4b/WhatsApp Business/Media/.Statuses";
        }
    }



}