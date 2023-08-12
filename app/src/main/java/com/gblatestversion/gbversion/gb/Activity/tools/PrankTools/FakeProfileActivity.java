package com.gblatestversion.gbversion.gb.Activity.tools.PrankTools;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.gblatestversion.gbversion.gb.Activity.tools.ProfileActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityFakeProfileBinding;
import com.github.florent37.inlineactivityresult.InlineActivityResult;
import com.github.florent37.inlineactivityresult.Result;
import com.github.florent37.inlineactivityresult.callbacks.SuccessCallback;

public class FakeProfileActivity extends AppCompatActivity {

    ActivityFakeProfileBinding gbvFakeProfileBinding;
    String profileimage = "";

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvFakeProfileBinding = ActivityFakeProfileBinding.inflate(getLayoutInflater());
        setContentView(gbvFakeProfileBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,gbvFakeProfileBinding.frameViewAds);

        setToolbar();
        startPRofile();
    }

    public void setToolbar() {
         gbvFakeProfileBinding.includedToolbar.title.setText("Fake Profile");
        gbvFakeProfileBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startPRofile() {

        gbvFakeProfileBinding.txtProfile.setOnClickListener(view -> {
            Intent intent = new Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");

            new InlineActivityResult(this)
                    .startForResult(intent)
                    .onSuccess(new SuccessCallback() {
                        @Override
                        public void onSuccess(Result result) {
                            if (result.getData() != null && result.getData().getData() != null) {
                                profileimage = result.getData().getData().toString();
                                Glide.with(FakeProfileActivity.this).load(profileimage)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(gbvFakeProfileBinding.ivProfile);
                            }
                        }
                    });
        });

        gbvFakeProfileBinding.txtDone.setOnClickListener(view ->
                WFSAppLoadAds.getInstance().showInterstitial(this, () -> {
                    Intent intent = new Intent(this, ProfileActivity.class);
                    intent.putExtra("name", gbvFakeProfileBinding.editName.getText().toString());
                    intent.putExtra("seen", gbvFakeProfileBinding.editseen.getText().toString());
                    intent.putExtra("status", gbvFakeProfileBinding.editStatus.getText().toString());
                    intent.putExtra("profile", profileimage);
                    intent.putExtra("date", gbvFakeProfileBinding.editTime.getText().toString());
                    intent.putExtra("number", gbvFakeProfileBinding.editNum.getText().toString());
                    startActivity(new Intent(intent));
                }));
    }
}