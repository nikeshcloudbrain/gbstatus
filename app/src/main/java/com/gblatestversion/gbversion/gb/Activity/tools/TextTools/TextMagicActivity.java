package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.Fragments.DecorFragment;
import com.gblatestversion.gbversion.gb.Fragments.FontFragment;
import com.gblatestversion.gbversion.gb.databinding.ActivityTextMagicBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class TextMagicActivity extends AppCompatActivity {

    ActivityTextMagicBinding gbvTextMagicBinding;
    com.gblatestversion.gbversion.gb.adaptor.VPFragmentAdapter VPFragmentAdapter;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvTextMagicBinding = ActivityTextMagicBinding.inflate(getLayoutInflater());
        setContentView(gbvTextMagicBinding.getRoot());
        setToolbar();
        startMagic();
    }

    public void setToolbar() {
        gbvTextMagicBinding.includedToolbar.title.setText("Text Magic");
        gbvTextMagicBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startMagic() {
        VPFragmentAdapter = new VPFragmentAdapter(getSupportFragmentManager(), getLifecycle());

        VPFragmentAdapter.addFragment(DecorFragment.newInstance(this), "Decorative Text");
        VPFragmentAdapter.addFragment(FontFragment.newInstance(this), "Font Changer");

        gbvTextMagicBinding.viewPager.setAdapter(VPFragmentAdapter);
        gbvTextMagicBinding.viewPager.setOffscreenPageLimit(2);

        new TabLayoutMediator(gbvTextMagicBinding.tabRS, gbvTextMagicBinding.viewPager, (tab, position) -> tab.setText(VPFragmentAdapter.StringArrayList.get(position))).attach();
    }
}