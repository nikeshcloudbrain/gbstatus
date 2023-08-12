package com.gblatestversion.gbversion.gb.Activity.tools;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityAutoReplyBinding;

public class AutoReplyActivity extends AppCompatActivity {
    LinearLayout copy_message_0;
    LinearLayout copy_message_1;
    LinearLayout copy_message_2;
    LinearLayout copy_message_3;
    LinearLayout copy_message_4;
    LinearLayout copy_message_5;
    LinearLayout copy_message_6;
    LinearLayout copy_message_7;
    EditText edit_message_0;
    EditText edit_message_1;
    EditText edit_message_2;
    EditText edit_message_3;
    EditText edit_message_4;
    EditText edit_message_5;
    EditText edit_message_6;
    EditText edit_message_7;
    LinearLayout share_message_0;
    LinearLayout share_message_1;
    LinearLayout share_message_2;
    LinearLayout share_message_3;
    LinearLayout share_message_4;
    LinearLayout share_message_5;
    LinearLayout share_message_6;
    LinearLayout share_message_7;

    ActivityAutoReplyBinding binding;

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAutoReplyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WFSAppLoadAds.getInstance().showNativeMediaFix(this,binding.frameViewAdsMain);

binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});
binding.tool.title.setText("Auto Reply");
        edit_message_0 = findViewById(R.id.gb_edit_message_0_edt);
        edit_message_1 = findViewById(R.id.gb_edit_message_2_edt);
        edit_message_2 = findViewById(R.id.gb_edit_message_3_edt);
        edit_message_3 = findViewById(R.id.gb_edit_message_4_edt);
        edit_message_4 = findViewById(R.id.gb_edit_message_5_edt);
        edit_message_5 = findViewById(R.id.gb_edit_message_6_edt);
        edit_message_6 = findViewById(R.id.gb_edit_message_7_edt);
        edit_message_7 = findViewById(R.id.gb_edit_message_8_edt);
        copy_message_0 = findViewById(R.id.gb_copy_0_ll);
        copy_message_1 = findViewById(R.id.gb_copy_2_ll);
        copy_message_2 = findViewById(R.id.gb_copy_3_ll);
        copy_message_3 = findViewById(R.id.gb_copy_4_ll);
        copy_message_4 = findViewById(R.id.gb_copy_5_ll);
        copy_message_5 = findViewById(R.id.gb_copy_6_ll);
        copy_message_6 = findViewById(R.id.gb_copy_7_ll);
        copy_message_7 = findViewById(R.id.gb_copy_8_ll);

        edit_message_0.setText(R.string.good_morning);
        edit_message_1.setText(R.string.good_afternoon);
        edit_message_2.setText(R.string.good_night);
        edit_message_3.setText(R.string.good_evening);
        edit_message_4.setText(R.string.how_are_you);
        edit_message_5.setText(R.string.where_are_you);
        edit_message_6.setText(R.string.happy_birthday);
        edit_message_7.setText(R.string.hows_your_day);

        share_message_0 = findViewById(R.id.gb_share_0_ll);
        share_message_1 = findViewById(R.id.gb_share_2_ll);
        share_message_2 = findViewById(R.id.gb_share_3_ll);
        share_message_3 = findViewById(R.id.gb_share_4_ll);
        share_message_4 = findViewById(R.id.gb_share_5_ll);
        share_message_5 = findViewById(R.id.gb_share_6_ll);
        share_message_6 = findViewById(R.id.gb_share_7_ll);
        share_message_7 = findViewById(R.id.gb_share_8_ll);

        copy_message_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_0.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_0.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();

            }
        });
        share_message_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_0.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_0.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_1.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_1.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();

            }
        });
        share_message_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_1.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_1.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_2.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_2.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();
            }
        });
        share_message_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_2.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_2.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_3.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_3.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();

            }
        });
        share_message_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_3.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_3.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_4.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_4.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();

            }
        });
        share_message_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_4.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_4.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_5.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this,getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_5.getText().toString()));
                Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();

            }
        });
        share_message_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_5.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_5.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_6.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_6.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();
            }
        });
        share_message_6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_6.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_6.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy_message_7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_7.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.nothing_to_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) AutoReplyActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("copy", AutoReplyActivity.this.edit_message_7.getText().toString()));
                Toast.makeText(AutoReplyActivity.this,  getResources().getString(R.string.message_copied), Toast.LENGTH_SHORT).show();

            }
        });
        share_message_7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (edit_message_7.getText().toString().isEmpty()) {
                    Toast.makeText(AutoReplyActivity.this, getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", AutoReplyActivity.this.edit_message_7.getText().toString());
                    intent.setPackage("com.whatsapp");
                    intent.setType("text/plain");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),  getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}