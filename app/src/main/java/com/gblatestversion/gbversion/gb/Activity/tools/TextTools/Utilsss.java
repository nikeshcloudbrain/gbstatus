package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gblatestversion.gbversion.gb.R;


public class Utilsss {
    Context context;
    private SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    private SharedPreferences prefs;

    public Utilsss(Context context2) {
        context = context2;
        editor = context2.getSharedPreferences("myPrefs", 0).edit();
        prefs = context2.getSharedPreferences("myPrefs", 0);
        progressDialog = new ProgressDialog(context2);
    }

    public void changeAdShow() {
        editor.putBoolean(getStringFromResource(R.string.button_count), !getAdShow()).commit();
        editor.commit();
    }

    public boolean getAdShow() {
        return this.prefs.getBoolean(getStringFromResource(R.string.button_count), true);
    }

    public void hideSoftKeyboard(View view) {
        ((InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showToastFromResource(int i) {
        Context context2 = this.context;
        Toast.makeText(context2, context2.getResources().getString(i), Toast.LENGTH_SHORT).show();
    }

    public String getStringFromResource(int i) {
        return this.context.getResources().getString(i);
    }

    public void SwitchActivity(Class cls) {
        context.startActivity(new Intent(this.context, cls));
    }

    public int getIntegerFromResource(int i) {
        return this.context.getResources().getInteger(i);
    }

    public void showPd(String str) {
        progressDialog.setMessage(str);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hidePd() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
