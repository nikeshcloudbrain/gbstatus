package com.gblatestversion.gbversion.gb.Activity.tools;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityDirectChatBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.uiController;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.net.URLEncoder;

public class DirectChatActivity extends AppCompatActivity {

    ActivityDirectChatBinding gbvDirectChatBinding;
    PhoneNumberUtil util;

    @Override
    public void onBackPressed() {
        uiController.onBackPressed(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvDirectChatBinding = ActivityDirectChatBinding.inflate(getLayoutInflater());
        setContentView(gbvDirectChatBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvDirectChatBinding.frameViewAdsMain);

        util = PhoneNumberUtil.getInstance(this);
        setToolbar();
        startChat();
    }


    public void setToolbar() {
        gbvDirectChatBinding.includedToolbar.title.setText("Direct Chat");
        gbvDirectChatBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startChat() {

        gbvDirectChatBinding.btnDone.setOnClickListener(view -> {
            if (gbvDirectChatBinding.editNumber.getText().toString().equalsIgnoreCase("")) {
                Constant.showToast("Enter Number ");
            } else if (gbvDirectChatBinding.editMessage.getText().toString().equalsIgnoreCase("")) {
                Constant.showToast("Enter Message");
            } else {
                try {
                    Phonenumber.PhoneNumber number = util.parse(gbvDirectChatBinding.editNumber.getText(), gbvDirectChatBinding.editCode.getSelectedCountryNameCode());
                    boolean isValid = util.isValidNumber(number);
                    if (isValid) {
                        sendChat(number);
                    } else {
                        Constant.showToast("Plz Enter Valid Number");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendChat(Phonenumber.PhoneNumber phonenumber) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        try {
            String url = "https://api.whatsapp.com/send?phone=" + phonenumber.toString().replaceAll(" ","") + "&text=" + URLEncoder.encode(gbvDirectChatBinding.editMessage.getText().toString(), "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (Exception unused) {
            unused.printStackTrace();
            Constant.showToast("WhatsApp not Installed");
        }

    }
}