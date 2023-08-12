package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityMirriorBinding;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

public class MirriorActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean bothSelect;
    private boolean mirrorSelect;
    private boolean reverseSelect;
    private com.gblatestversion.gbversion.gb.Activity.tools.TextTools.Utilsss utils;
    ActivityMirriorBinding binding;

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding=ActivityMirriorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WFSAppLoadAds.getInstance().showNativeMediaFix(this,binding.frameViewAdsMain);

        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Mirror Text");
        doInitializer();
        setOnClickListener();
        addTextChangeListener();
    }

    private final void setBothTextUI() {
    }

    private final void setMirrorTextUI() {
    }

    private final void setReverseTextUI() {
    }

    private final void setSimpleTextUI() {
    }


    private final void doInitializer() {
        this.utils = new com.gblatestversion.gbversion.gb.Activity.tools.TextTools.Utilsss(this);
        ((EditText) findViewById(R.id.main_edit_text)).setText("");
        EditText editText = findViewById(R.id.main_edit_text);

        ((ImageView) findViewById(R.id.main_edit_text_clear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        com.gblatestversion.gbversion.gb.Activity.tools.TextTools.Utilsss utilsss = this.utils;
        if (utilsss == null) {
            Intrinsics.throwUninitializedPropertyAccessException("utils");
        }
        editText.setHint(utilsss.getStringFromResource(R.string.text_here));
        TextView textView = findViewById(R.id.repeated_text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
        TextView textView2 = findViewById(R.id.repeated_text_view);
        textView2.setTextSize(20.0f);
    }

    private final void setOnClickListener() {
        ((RelativeLayout) findViewById(R.id.iv_mirror)).setOnClickListener(this);
        ((RelativeLayout) findViewById(R.id.iv_reverse)).setOnClickListener(this);
        ((RelativeLayout) findViewById(R.id.iv_both)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.iv_copy)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.iv_share)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.iv_delete)).setOnClickListener(this);
    }

    private final void addTextChangeListener() {
        ((EditText) findViewById(R.id.main_edit_text)).addTextChangedListener(new com.gblatestversion.gbversion.gb.Activity.tools.TextTools.TextChangeListener(this));
    }

    public final void generateRequiredText() {
        if (this.bothSelect) {
            generateBothText();
        } else if (this.mirrorSelect) {
            generateMirrorText();
        } else if (this.reverseSelect) {
            generateReverseText();
        } else {
            generateSimpleText();
        }
    }

    private final void generateMirrorText() {
        EditText editText = findViewById(R.id.main_edit_text);
        writeText(convertTextInMirror(editText.getText().toString()));
    }

    private final void generateReverseText() {
        EditText editText = findViewById(R.id.main_edit_text);
        writeText(convertTextInReverse(editText.getText().toString()));
    }

    private final void generateBothText() {
        EditText editText = findViewById(R.id.main_edit_text);

        writeText(convertTextInMirror(convertTextInReverse(editText.getText().toString())));
    }

    private final void generateSimpleText() {
        EditText editText = findViewById(R.id.main_edit_text);
        writeText(editText.getText().toString());
    }

    private final String convertTextInReverse(String str) {
        String str2 = "";
        for (int length = str.length() - 1; length >= 0; length--) {
            str2 = str2 + str.charAt(length);
        }
        return str2;
    }

    private final String convertTextInMirror(String str) {
        StringBuilder sb;
        int length = str.length();
        String str2 = "";
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (ArraysKt.contains(com.gblatestversion.gbversion.gb.Activity.tools.TextTools.StoredData.INSTANCE.getSimple_small_capital_letters_and_digits(), charAt)) {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(com.gblatestversion.gbversion.gb.Activity.tools.TextTools.StoredData.INSTANCE.getMirror_small_capital_letters_and_digits()[ArraysKt.indexOf(com.gblatestversion.gbversion.gb.Activity.tools.TextTools.StoredData.INSTANCE.getSimple_small_capital_letters_and_digits(), charAt)]);
            } else {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(charAt);
            }
            str2 = sb.toString();
        }
        return str2;
    }

    private final void writeText(String str) {
        TextView textView = findViewById(R.id.repeated_text_view);

        textView.setVisibility(View.VISIBLE);
        TextView textView2 = findViewById(R.id.repeated_text_view);

        textView2.setText(str);
    }

    public final void deleteText() {
        TextView textView = findViewById(R.id.repeated_text_view);
        textView.setText("");

        TextView textView2 = findViewById(R.id.repeated_text_view);
        textView2.setVisibility(View.VISIBLE);
        textView2.getText();

        ((TextView) findViewById(R.id.repeated_text_view)).setVisibility(View.VISIBLE);

    }

    public void onClick(View view) {
        if (view != null) {
            if (Intrinsics.areEqual((Object) view, (Object) (RelativeLayout) findViewById(R.id.iv_mirror))) {
                mirrorFunction();
            } else if (Intrinsics.areEqual((Object) view, (Object) (RelativeLayout) findViewById(R.id.iv_reverse))) {
                reverseFunction();
            } else if (Intrinsics.areEqual((Object) view, (Object) (RelativeLayout) findViewById(R.id.iv_both))) {
                bothFunction();
            } else if (Intrinsics.areEqual((Object) view, (Object) (LinearLayout) findViewById(R.id.iv_share))) {
                shareText();
            } else if (Intrinsics.areEqual((Object) view, (Object) (LinearLayout) findViewById(R.id.iv_copy))) {
                copyText();
            } else if (Intrinsics.areEqual((Object) view, (Object) (LinearLayout) findViewById(R.id.iv_delete))) {
                deleteText();
            }
        }
    }

    private final void mirrorFunction() {
        mirrorSelect = !this.mirrorSelect;
        reverseSelect = false;
        bothSelect = false;
        setUI();
    }

    private final void reverseFunction() {
        reverseSelect = !this.reverseSelect;
        bothSelect = false;
        mirrorSelect = false;
        setUI();
    }

    private final void bothFunction() {
        bothSelect = true;
        mirrorSelect = true;
        reverseSelect = true;
        setUI();
    }

    private final void setUI() {
        if (bothSelect) {
            setBothTextUI();
        } else if (mirrorSelect) {
            setMirrorTextUI();
        } else if (reverseSelect) {
            setReverseTextUI();
        } else {
            setSimpleTextUI();
        }
        generateRequiredText();
    }

    private final void shareText() {
        TextView textView = findViewById(R.id.repeated_text_view);
        if (!com.gblatestversion.gbversion.gb.Activity.tools.TextTools.CommonKt.shareText(textView.getText().toString(), this)) {
            com.gblatestversion.gbversion.gb.Activity.tools.TextTools.Utilsss utilsss = utils;
            if (utilsss == null) {
                Intrinsics.throwUninitializedPropertyAccessException("utils");
            }
            utilsss.showToastFromResource(R.string.no_text_to_share);
        }
    }

    private final void copyText() {
        TextView textView = findViewById(R.id.repeated_text_view);
        if (com.gblatestversion.gbversion.gb.Activity.tools.TextTools.CommonKt.copyText(textView.getText().toString(), this)) {
            com.gblatestversion.gbversion.gb.Activity.tools.TextTools.Utilsss utilsss = utils;
            if (utilsss == null) {
                Intrinsics.throwUninitializedPropertyAccessException("utils");
            }
            utilsss.showToastFromResource(R.string.text_copied);
            return;
        }
        com.gblatestversion.gbversion.gb.Activity.tools.TextTools.Utilsss utilsss2 = this.utils;
        if (utilsss2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("utils");
        }
        utilsss2.showToastFromResource(R.string.no_text_to_copy);
    }
}