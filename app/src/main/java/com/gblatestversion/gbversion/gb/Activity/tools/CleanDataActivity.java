package com.gblatestversion.gbversion.gb.Activity.tools;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.Fragments.FileFragment;
import com.gblatestversion.gbversion.gb.databinding.ActivityCleanDataBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.google.android.material.tabs.TabLayoutMediator;

public class CleanDataActivity extends AppCompatActivity {

    String category;
    String path, sPath;

    ActivityCleanDataBinding gbvCleanDataBinding;

    VPFragmentAdapter adapter;

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvCleanDataBinding = ActivityCleanDataBinding.inflate(getLayoutInflater());
        setContentView(gbvCleanDataBinding.getRoot());

        if (getIntent() != null && getIntent().hasExtra(Constant.CATEGORY)) {
            category = getIntent().getExtras().getString(Constant.CATEGORY);
            path = getIntent().getExtras().getString("folderPath");
            sPath = getIntent().getExtras().getString("folderPath2");
        }

        setToolbar();
        startWall();

    }


    public void setToolbar() {
        gbvCleanDataBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startWall() {
        adapter = new VPFragmentAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFragment(FileFragment.newInstance(this, category, path, Constant.OPTIONS1), "Received File");
        adapter.addFragment(FileFragment.newInstance(this, category, sPath, Constant.OPTIONS2), "Sent File");

        gbvCleanDataBinding.vpRS.setAdapter(adapter);
        gbvCleanDataBinding.vpRS.setOffscreenPageLimit(2);

        new TabLayoutMediator(gbvCleanDataBinding.tabRS, gbvCleanDataBinding.vpRS, (tab, position) -> tab.setText(adapter.StringArrayList.get(position))).attach();

    }
}