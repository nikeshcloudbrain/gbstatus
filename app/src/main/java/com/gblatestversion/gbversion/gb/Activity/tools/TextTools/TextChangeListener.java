package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.text.Editable;
import android.text.TextWatcher;

public class TextChangeListener implements TextWatcher {
    final MirriorActivity mirriorActivity;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    TextChangeListener(MirriorActivity mirrorActivity) {
        this.mirriorActivity = mirrorActivity;
    }

    public void afterTextChanged(Editable editable) {
        if (editable == null) {
            return;
        }
        if (editable.toString().length() > 0) {
            mirriorActivity.generateRequiredText();
        } else {
            mirriorActivity.deleteText();
        }
    }
}
