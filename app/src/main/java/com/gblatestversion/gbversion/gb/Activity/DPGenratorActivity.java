package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.gblatestversion.gbversion.gb.adaptor.MyPagerAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityDpgenratorBinding;

public class DPGenratorActivity extends AppCompatActivity {
ActivityDpgenratorBinding binding;
    MyPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDpgenratorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        binding.viewpager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);

        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.tool.title.setText("Dp Generator");



    }




    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }


}