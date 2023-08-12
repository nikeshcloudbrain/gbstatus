package com.gblatestversion.gbversion.gb.Activity.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityViewVideoBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.preference.PowerPreference;

public class ViewVideoActivity extends AppCompatActivity {
ActivityViewVideoBinding binding;
String video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
binding.includedToolbar.title.setText("Video View");
binding.includedToolbar.icBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});

WFSAppLoadAds.getInstance().showNativeBottomDynamic(ViewVideoActivity.this,binding.frameViewAds);
        video =PowerPreference.getDefaultFile().getString(Constant.cPath);
//        Log.e("tt",video);

        VideoView videoView = findViewById(R.id.ivVideo);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setVideoPath(video);
        videoView.start();

    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(ViewVideoActivity.this, () -> finish());
    }
}