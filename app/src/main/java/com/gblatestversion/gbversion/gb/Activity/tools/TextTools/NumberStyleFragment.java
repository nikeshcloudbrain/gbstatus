package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gblatestversion.gbversion.gb.R;


public class NumberStyleFragment extends Fragment {
    private int ad_id;

    private class Holder {
        ImageView copyStyle,moreShareStyle,whatsAppStyle;
        TabView txtNumberStyle;

        public Holder(View view) {
            txtNumberStyle = view.findViewById(R.id.txtNumberStyleId);
            copyStyle = view.findViewById(R.id.copyStyleId);
            whatsAppStyle = view.findViewById(R.id.whatsAppStyleId);
            moreShareStyle = view.findViewById(R.id.moreShareStyleId);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_numberstyle, viewGroup, false);
        Holder holder = new Holder(inflate);
        inflate.setTag(holder);
        String string = getArguments().getString("text");
        String string2 = getArguments().getString("style");
        final StringBuilder sb = new StringBuilder();
        String[] split = string2.split(" ");
        for (int i = 0; i < string.length(); i++) {
            sb.append(split[Character.digit(string.charAt(i), 10)]);
        }
        holder.txtNumberStyle.setText(sb.toString());
        holder.copyStyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickOnCopy(sb.toString());
            }
        });
        holder.whatsAppStyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                shareOnly(true, sb.toString());
            }
        });
        holder.moreShareStyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                shareOnly(false, sb.toString());
            }
        });
        return inflate;
    }

    public static NumberStyleFragment content(String str, String str2) {
        NumberStyleFragment numberStyleFragment = new NumberStyleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", str);
        bundle.putString("style", str2);
        numberStyleFragment.setArguments(bundle);
        return numberStyleFragment;
    }

    public void clickOnCopy(String str) {
        ((ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("text", str));
        Toast.makeText(getActivity(), "Copy", Toast.LENGTH_SHORT).show();
    }

    public void shareOnly(boolean z, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        if (z) {
            try {
                intent.setPackage("com.whatsapp");
                getActivity().startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(getActivity(), getResources().getString(R.string.ws_not_installed), Toast.LENGTH_SHORT).show();
            }
        } else {
            getActivity().startActivity(Intent.createChooser(intent, getString(R.string.share_via)));
        }
    }
}
