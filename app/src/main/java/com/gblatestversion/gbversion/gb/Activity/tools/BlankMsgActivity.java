package com.gblatestversion.gbversion.gb.Activity.tools;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityBlankMsgBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;

public class BlankMsgActivity extends AppCompatActivity {

    ActivityBlankMsgBinding gbvBlankMsgBinding;
    int data = 1;
    boolean checked = false;

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvBlankMsgBinding = ActivityBlankMsgBinding.inflate(getLayoutInflater());
        setContentView(gbvBlankMsgBinding.getRoot());
        WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvBlankMsgBinding.frameViewAdsMain);

        setToolbar();
        startMsg();
        set1Space(false);

    }

    public void setToolbar() {
        gbvBlankMsgBinding.includedToolbar.title.setText("Blank Message");
        gbvBlankMsgBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void unselectAll() {
        gbvBlankMsgBinding.txt1.setBackgroundResource(R.drawable.ic_bmunselect);
        gbvBlankMsgBinding.txt2.setBackgroundResource(R.drawable.ic_bmunselect);
        gbvBlankMsgBinding.txt3.setBackgroundResource(R.drawable.ic_bmunselect);
        gbvBlankMsgBinding.txt4.setBackgroundResource(R.drawable.ic_bmunselect);
        gbvBlankMsgBinding.txt5.setBackgroundResource(R.drawable.ic_bmunselect);
        gbvBlankMsgBinding.txt1.setTextColor(Color.parseColor("#000000"));
        gbvBlankMsgBinding.txt2.setTextColor(Color.parseColor("#000000"));
        gbvBlankMsgBinding.txt3.setTextColor(Color.parseColor("#000000"));
        gbvBlankMsgBinding.txt4.setTextColor(Color.parseColor("#000000"));
        gbvBlankMsgBinding.txt5.setTextColor(Color.parseColor("#000000"));
    }

    public void selectText(int dat) {
        data = dat;
        if (dat == 1) {
            gbvBlankMsgBinding.txt1.setBackgroundResource(R.drawable.ic_bmselect);
            gbvBlankMsgBinding.txt1.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (dat == 2) {

            gbvBlankMsgBinding.txt2.setBackgroundResource(R.drawable.ic_bmselect);
            gbvBlankMsgBinding.txt2.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (dat == 3) {

            gbvBlankMsgBinding.txt3.setBackgroundResource(R.drawable.ic_bmselect);
            gbvBlankMsgBinding.txt3.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (dat == 4) {

            gbvBlankMsgBinding.txt4.setBackgroundResource(R.drawable.ic_bmselect);
            gbvBlankMsgBinding.txt4.setTextColor(Color.parseColor("#FFFFFF"));
        } else if (dat == 5) {

            gbvBlankMsgBinding.txt5.setBackgroundResource(R.drawable.ic_bmselect);
            gbvBlankMsgBinding.txt5.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    public void startMsg() {

        gbvBlankMsgBinding.toggle.setOnToggleChanged(on -> {
            checked = on;
            if (data == 1) {
                set1Space(on);
            } else if (data == 2) {
                set2Space(on);
            } else if (data == 3) {
                set3Space(on);
            } else if (data == 4) {
                set4Space(on);
            } else if (data == 5) {
                set5Space(on);
            }
        });

        gbvBlankMsgBinding.btnDelete.setOnClickListener(view -> {
            gbvBlankMsgBinding.txtMsg.setText("");
            unselectAll();
            data = 0;
        });
        gbvBlankMsgBinding.txt1.setOnClickListener(view -> {
            unselectAll();
            selectText(1);
            set1Space(checked);
        });


        gbvBlankMsgBinding.txt2.setOnClickListener(view -> {
            unselectAll();
            selectText(2);
            set2Space(checked);
        });

        gbvBlankMsgBinding.txt3.setOnClickListener(view -> {
            unselectAll();
            selectText(3);
            set3Space(checked);
        });

        gbvBlankMsgBinding.txt4.setOnClickListener(view -> {
            unselectAll();
            selectText(4);
            set4Space(checked);
        });


        gbvBlankMsgBinding.txt5.setOnClickListener(view -> {
            unselectAll();
            selectText(5);
            set5Space(checked);
        });

        gbvBlankMsgBinding.btnChat.setOnClickListener(view -> share());
    }

    public void set1Space(boolean ischecked) {
        String string = "";

        if (ischecked) {
            for (int i = 0; i < 5; i++) {
                string = string + " " + "\n";
            }
        } else {
            for (int i = 0; i < 5; i++) {
                string = string + " ";
            }
        }
        gbvBlankMsgBinding.txtMsg.setText(string);
    }

    public void set2Space(boolean ischecked) {
        String string = "";

        if (ischecked) {
            for (int i = 0; i < 10; i++) {
                string = string + " " + "\n";
            }
        } else {
            for (int i = 0; i < 10; i++) {
                string = string + " ";
            }
        }
        gbvBlankMsgBinding.txtMsg.setText(string);
    }

    public void set3Space(boolean ischecked) {
        String string = "";

        if (ischecked) {
            for (int i = 0; i < 20; i++) {
                string = string + " " + "\n";
            }
        } else {
            for (int i = 0; i < 20; i++) {
                string = string + " ";
            }
        }
        gbvBlankMsgBinding.txtMsg.setText(string);
    }


    public void set4Space(boolean ischecked) {
        String string = "";

        if (ischecked) {
            for (int i = 0; i < 50; i++) {
                string = string + " " + "\n";
            }
        } else {
            for (int i = 0; i < 50; i++) {
                string = string + " ";
            }
        }
        gbvBlankMsgBinding.txtMsg.setText(string);
    }

    public void set5Space(boolean ischecked) {
        String string = "";

        if (ischecked) {
            for (int i = 0; i < 100; i++) {
                string = string + " " + "\n";
            }
        } else {
            for (int i = 0; i < 100; i++) {
                string = string + " ";
            }
        }
        gbvBlankMsgBinding.txtMsg.setText(string);
    }


    public void share() {
        if (!gbvBlankMsgBinding.txtMsg.getText().toString().equalsIgnoreCase("")) {
            PackageManager packageManager = getPackageManager();
            try {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                intent.setPackage("com.whatsapp");
                String someValue = gbvBlankMsgBinding.txtMsg.getText().toString();
                intent.putExtra(Intent.EXTRA_TEXT, "."+someValue+".");
                startActivity(Intent.createChooser(intent, "Share with"));
            } catch (PackageManager.NameNotFoundException unused) {
                Constant.showToast("WhatsApp not Installed");
            }
        } else {
            Constant.showToast("Enter Something");
        }
    }
}