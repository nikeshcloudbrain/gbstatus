package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityTextRepeaterBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;

public class TextRepeaterActivity extends AppCompatActivity {

    ActivityTextRepeaterBinding gbvTextRepeaterBinding;
    boolean checked = false;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvTextRepeaterBinding = ActivityTextRepeaterBinding.inflate(getLayoutInflater());
        setContentView(gbvTextRepeaterBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvTextRepeaterBinding.frameViewAdsMain);

        setToolbar();
        startRepeater();
    }


    public void setToolbar() {
        gbvTextRepeaterBinding.includedToolbar.title.setText("Text Repeater");
        gbvTextRepeaterBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startRepeater() {

        gbvTextRepeaterBinding.btnDelete.setOnClickListener(view -> {
            gbvTextRepeaterBinding.editMsg.setText("");
            gbvTextRepeaterBinding.editRepeat.setText("");
            gbvTextRepeaterBinding.txtMsg.setText("");
        });

        gbvTextRepeaterBinding.btnChat.setOnClickListener(view -> {
            if (!gbvTextRepeaterBinding.txtMsg.getText().toString().equalsIgnoreCase("")) {
                PackageManager packageManager = getPackageManager();
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//                    intent.setPackage("com.whatsapp");
                    intent.putExtra("android.intent.extra.TEXT", gbvTextRepeaterBinding.txtMsg.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share with"));
                } catch (PackageManager.NameNotFoundException unused) {
                    Constant.showToast("WhatsApp not Installed");
                }
            } else {
                Constant.showToast("Enter Something");
            }
        });

        gbvTextRepeaterBinding.txtRepeat.setOnClickListener(view -> {
            if (gbvTextRepeaterBinding.editMsg.getText().toString().length() > 0 && gbvTextRepeaterBinding.editRepeat.getText().toString().length() > 0) {
                repeat(gbvTextRepeaterBinding.editMsg, gbvTextRepeaterBinding.editRepeat, checked);
            } else {
                Toast.makeText(this, "Plz Enter Something", Toast.LENGTH_SHORT).show();
            }
        });

        gbvTextRepeaterBinding.toggle.setOnToggleChanged(on -> {
            checked = on;
            if (gbvTextRepeaterBinding.editMsg.getText().toString().length() > 0) {
                repeat(gbvTextRepeaterBinding.editMsg, gbvTextRepeaterBinding.editRepeat, on);
            }
        });
    }

    public void repeat(EditText text, EditText number, boolean checked) {
        if (!text.getText().toString().equalsIgnoreCase("") && !number.getText().toString().equalsIgnoreCase("")) {
            String data = "";
            for (int i = 0; i < Integer.valueOf(number.getText().toString()); i++) {
                if (checked) {
                    data = data + text.getText().toString() + "\n";
                } else {
                    data = data + text.getText().toString();
                    data = data + " ";
                }
            }
            data = data.trim();
            gbvTextRepeaterBinding.txtMsg.setText(data);
        } else {
            gbvTextRepeaterBinding.txtMsg.setText("");
        }
    }
}