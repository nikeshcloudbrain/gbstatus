package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ActivityTextEmojiBinding;
import com.gblatestversion.gbversion.gb.module.EmojiGenrator;
import com.gblatestversion.gbversion.gb.utils.Constant;

public class TextEmojiActivity extends AppCompatActivity {

    ActivityTextEmojiBinding gbvTextEmojiBinding;

     @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gbvTextEmojiBinding = ActivityTextEmojiBinding.inflate(getLayoutInflater());
        setContentView(gbvTextEmojiBinding.getRoot());
WFSAppLoadAds.getInstance().showNativeMediaFix(this,gbvTextEmojiBinding.frameViewAdsMain);
        setToolbar();
        startTextEmoji();
    }


    public void setToolbar() {
        gbvTextEmojiBinding.includedToolbar.title.setText("Text To Emoji");
        gbvTextEmojiBinding.includedToolbar.icBack.setOnClickListener(view -> onBackPressed());

    }

    public void startTextEmoji() {
        inputTextlistner();
        emojeeTextlistner();

        gbvTextEmojiBinding.btnDelete.setOnClickListener(view -> {
            gbvTextEmojiBinding.txtMsg.setText("");
            gbvTextEmojiBinding.editMesg.setText("");
            gbvTextEmojiBinding.editEmoji.setText("");
        });

        gbvTextEmojiBinding.btnChat.setOnClickListener(view -> {
            if (!gbvTextEmojiBinding.txtMsg.getText().toString().equalsIgnoreCase("")) {
                PackageManager packageManager = getPackageManager();
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//                    intent.setPackage("com.whatsapp");
                    intent.putExtra("android.intent.extra.TEXT", gbvTextEmojiBinding.txtMsg.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share with"));
                } catch (PackageManager.NameNotFoundException unused) {
                    Constant.showToast("WhatsApp not Installed");
                }
            } else {
                Constant.showToast("Enter Something");
            }
        });
    }

    private void inputTextlistner() {
        gbvTextEmojiBinding.editEmoji.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() != 0) {
                    paste(gbvTextEmojiBinding.editMesg, gbvTextEmojiBinding.txtMsg, gbvTextEmojiBinding.editEmoji);
                } else {
                    gbvTextEmojiBinding.txtMsg.setText("");
                }
            }
        });
    }

    private void emojeeTextlistner() {
        gbvTextEmojiBinding.editMesg.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() != 0) {
                    paste(gbvTextEmojiBinding.editMesg, gbvTextEmojiBinding.txtMsg, gbvTextEmojiBinding.editEmoji);
                } else {
                    gbvTextEmojiBinding.txtMsg.setText("");
                }
            }
        });
    }

    public void paste(EditText editText, TextView textView, EditText editText2) {
        String obj = editText.getText().toString();
        if (!editText2.getText().toString().isEmpty()) {
            textView.setText(new EmojiGenrator().generate(obj, editText2.getText().toString()));
        }
    }

}