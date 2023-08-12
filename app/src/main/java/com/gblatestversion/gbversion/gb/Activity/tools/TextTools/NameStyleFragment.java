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


public class NameStyleFragment extends Fragment {
    private int ad_id;
    private ImageView setting;

    private class Holder {
        ImageView copyStyle,moreShareStyle,whatsAppStyle;
      TabView txtNameStyle;

        public Holder(View view) {
            txtNameStyle = view.findViewById(R.id.txtNameStyleId);
            copyStyle = view.findViewById(R.id.copyStyleId);
            whatsAppStyle = view.findViewById(R.id.whatsAppStyleId);
            moreShareStyle = view.findViewById(R.id.moreShareStyleId);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_namestyle, viewGroup, false);
        Holder holder = new Holder(inflate);
        inflate.setTag(holder);
        String string = getArguments().getString("text");
        String string2 = getArguments().getString("style");

        final StringBuilder sb = new StringBuilder(string2);
        final String str = string2 + string + sb.reverse().toString();

        holder.txtNameStyle.setText(str);
        holder.copyStyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clickOnCopy(str);
            }
        });
        holder.whatsAppStyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                shareOnly(true, str);
            }
        });
        holder.moreShareStyle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              //  shareOnly(false, sb.toString());
                shareOnly(false, str.toString());
            }
        });
        return inflate;
    }

    public static NameStyleFragment content(String str, String str2) {
        NameStyleFragment nameStyleFragment = new NameStyleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", str);
        bundle.putString("style", str2);
        nameStyleFragment.setArguments(bundle);
        return nameStyleFragment;
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
                Toast.makeText(getActivity(), getResources().getString(R.string.ws_not_installed), 0).show();
            }
        } else {
            getActivity().startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_via)));
        }
    }
}
