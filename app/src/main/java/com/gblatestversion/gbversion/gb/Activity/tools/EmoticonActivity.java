package com.gblatestversion.gbversion.gb.Activity.tools;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.Fragments.EmoticonFragment;
import com.gblatestversion.gbversion.gb.databinding.ActivityEmoticonBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class EmoticonActivity extends AppCompatActivity {

    com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter VPFragmentAdapter;
    ActivityEmoticonBinding gbvEmoticonBinding;



     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvEmoticonBinding = ActivityEmoticonBinding.inflate(getLayoutInflater());
        setContentView(gbvEmoticonBinding.getRoot());
        setToolbar();
        startCon();
    }

    public void setToolbar() {
        gbvEmoticonBinding.includedToolbar.title.setText("Emoticon Icon");
        gbvEmoticonBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }


    public void startCon() {
        VPFragmentAdapter = new VPFragmentAdapter(getSupportFragmentManager(), getLifecycle());

        VPFragmentAdapter.addFragment(EmoticonFragment.newInstance(this, 1), "Happy");
        VPFragmentAdapter.addFragment(EmoticonFragment.newInstance(this, 2), "Sad");
        VPFragmentAdapter.addFragment(EmoticonFragment.newInstance(this, 3), "Love");
        VPFragmentAdapter.addFragment(EmoticonFragment.newInstance(this, 4), "Music");

        gbvEmoticonBinding.viewPager.setAdapter(VPFragmentAdapter);
        gbvEmoticonBinding.viewPager.setOffscreenPageLimit(2);

        new TabLayoutMediator(gbvEmoticonBinding.tabRS, gbvEmoticonBinding.viewPager, (tab, position) -> tab.setText(VPFragmentAdapter.StringArrayList.get(position))).attach();
    }
}