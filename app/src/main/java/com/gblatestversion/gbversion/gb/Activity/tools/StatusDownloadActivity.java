package com.gblatestversion.gbversion.gb.Activity.tools;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.Fragments.FileFragment;
import com.gblatestversion.gbversion.gb.databinding.ActivityStatusDownloadBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FileUtils;
import com.google.android.material.tabs.TabLayoutMediator;
import com.preference.PowerPreference;

public class StatusDownloadActivity extends AppCompatActivity {

    ActivityStatusDownloadBinding gbvStatusDownloadBinding;
    VPFragmentAdapter VPFragmentAdapter;
    public boolean isFirst = true;

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
        gbvStatusDownloadBinding = ActivityStatusDownloadBinding.inflate(getLayoutInflater());
        setContentView(gbvStatusDownloadBinding.getRoot());
        PowerPreference.getDefaultFile().putBoolean(Constant.OPTIONS1, false);
        PowerPreference.getDefaultFile().putBoolean(Constant.OPTIONS2, false);

        setToolbar();
        startRecent();
    }


    public void setToolbar() {
        gbvStatusDownloadBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());
    }

    public void startRecent() {
        VPFragmentAdapter = new VPFragmentAdapter(getSupportFragmentManager(), getLifecycle());

        VPFragmentAdapter.addFragment(FileFragment.newInstance(this, FileUtils.IMAGE, getSavedImagesFolder(), Constant.OPTIONS1), "Photos");
        VPFragmentAdapter.addFragment(FileFragment.newInstance(this, FileUtils.VIDEO, getSavedVideosFolder(), Constant.OPTIONS2), "Videos");

        gbvStatusDownloadBinding.vpRS.setAdapter(VPFragmentAdapter);
        gbvStatusDownloadBinding.vpRS.setOffscreenPageLimit(2);

        new TabLayoutMediator(gbvStatusDownloadBinding.tabRS, gbvStatusDownloadBinding.vpRS, (tab, position) -> tab.setText(VPFragmentAdapter.StringArrayList.get(position))).attach();
    }

    public String getSavedImagesFolder() {
        return Constant.getImagesFolder();
    }

    public String getSavedVideosFolder() {
        return Constant.getVideosFolder();
    }


}