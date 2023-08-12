package com.gblatestversion.gbversion.gb.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adaptor.IntroViewPagerAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityIntroBinding;
import com.gblatestversion.gbversion.gb.model.NextScreen;
import com.gblatestversion.gbversion.gb.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    IntroViewPagerAdapter introViewPagerAdapter;
    private ViewPager screenPager;
    int next;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this, binding.frameViewAds);
        final List<NextScreen> mList = new ArrayList<>();
        mList.add(new NextScreen("Story", " Saver", "We have introduced Story Saver, Save all the stories, videos, and reels from this app. The app is free and now we can download...", R.drawable.next1_v));
        mList.add(new NextScreen("Chat", " Tools", "Chat Tools is a messaging app with many features. It's not just for family & friends, Chat helps you to make friends and find...", R.drawable.next2_v));
        mList.add(new NextScreen("Prank", " Tools", "Introducing Sound Prank, the ultimate sound prank app designed to bring laughter and entertainment to your pranking adventures...", R.drawable.next3_v));

        screenPager = findViewById(R.id.idViewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        screenPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                Log.e("vv", "onPageSelected: " + arg0);
                if (arg0 == 2) {
                    binding.btnNext1.setVisibility(View.VISIBLE);


                    binding.tabLayout.setImageResource(R.drawable.shape3);

                    next = arg0;

                } else if (arg0 == 1) {
                    binding.btnNext1.setVisibility(View.GONE);


                    binding.tabLayout.setImageResource(R.drawable.shape2);

                    next = arg0;

                } else if (arg0 == 0) {
                    next = 0;
                    binding.tabLayout.setImageResource(R.drawable.shape);
                    binding.btnNext1.setVisibility(View.GONE);


                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int num) {


            }
        });


        binding.btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (next == 2) {
                    sharedPreferences = getSharedPreferences("intro", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putBoolean("iCheck", true);
                    myEdit.commit();
                    WFSAppLoadAds.getInstance().showInterstitial(IntroActivity.this, () -> {

                        startActivity(new Intent(IntroActivity.this, StartActivity.class));

                    });
                } else {

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Constant.showRateDialog(IntroActivity.this, false);

    }
}