package com.gblatestversion.gbversion.gb.Activity.tools;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding gbvProfileBinding;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(gbvProfileBinding.getRoot());
        setToolbar();
        startProfile();
    }

    public void setToolbar() {
        gbvProfileBinding.ivBack.setOnClickListener(view -> onBackPressed());

    }

    public void startProfile() {

        if (getIntent() != null) {

            if (getIntent().hasExtra("profile")) {
                if (!getIntent().getStringExtra("profile").equalsIgnoreCase(""))
                    Glide.with(this).load(getIntent().getStringExtra("profile"))
                            .into(gbvProfileBinding.ivProfile);
            }

            if (getIntent().hasExtra("name")) {
                if (!getIntent().getStringExtra("name").equalsIgnoreCase(""))
                    gbvProfileBinding.tvName.setText(getIntent().getStringExtra("name") + "");
                else {
                    gbvProfileBinding.tvName.setText("TechGuy");
                }
            }

            if (getIntent().hasExtra("seen")) {
                if (!getIntent().getStringExtra("seen").equalsIgnoreCase(""))
                    gbvProfileBinding.tvSeen.setText(getIntent().getStringExtra("seen") + "");
                else {
                    gbvProfileBinding.tvSeen.setText("online");
                }
            }

            if (getIntent().hasExtra("status")) {
                if (!getIntent().getStringExtra("status").equalsIgnoreCase(""))
                    gbvProfileBinding.tvStatus.setText(getIntent().getStringExtra("status") + "");
                else
                    gbvProfileBinding.tvStatus.setText("Hey there! I am using whatsToolbox");
            }

            if (getIntent().hasExtra("date")) {
                if (!getIntent().getStringExtra("date").equalsIgnoreCase(""))
                    gbvProfileBinding.tvDate.setText(getIntent().getStringExtra("date") + "");
                else
                    gbvProfileBinding.tvDate.setText("03 Aug 2021");
            }

            if (getIntent().hasExtra("number")) {
                if (!getIntent().getStringExtra("number").equalsIgnoreCase(""))
                    gbvProfileBinding.tvNumber.setText("+91 " + getIntent().getStringExtra("number") + "");
                else
                    gbvProfileBinding.tvNumber.setText("+91 98765433210");
            }

        }
    }
}