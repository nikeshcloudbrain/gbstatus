package com.gblatestversion.gbversion.gb.adaptor;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.gblatestversion.gbversion.gb.databinding.ItemMagixBinding;
import com.gblatestversion.gbversion.gb.databinding.ItemNativeListAdBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FontUtils;


public class FontAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    String message = "";

    public FontAdapter(Activity activity) {
        this.activity = activity;
    }

    public void refreshText(String list) {
        this.message = list;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ItemMagixBinding binding;

        public ItemHolder(@NonNull ItemMagixBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public class AdHolder extends RecyclerView.ViewHolder {
        ItemNativeListAdBinding binding;

        AdHolder(ItemNativeListAdBinding view) {
            super(view.getRoot());
            binding = view;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(ItemMagixBinding.inflate(LayoutInflater.from(activity), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemHolder holder1 = (ItemHolder) holder;
        if (message.equalsIgnoreCase("")) {
            holder1.binding.txtMsg.setText("");
            holder1.binding.txtMsg.setHint(makeStylishOf(position, "mili"));
        } else {
            holder1.binding.txtMsg.setText(makeStylishOf(position, message));
        }

        holder1.binding.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder1.binding.txtMsg.getText().toString().equalsIgnoreCase("")) {
                    Constant.shareApp(activity, holder1.binding.txtMsg.getText().toString());
                } else {
                    Constant.showToast(Constant.emptyError);
                }
            }
        });

        holder1.binding.ivCopy.setOnClickListener(view -> {
            if (!holder1.binding.txtMsg.getText().toString().equalsIgnoreCase("")) {
                try {
                    ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", holder1.binding.txtMsg.getText().toString());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(activity, "Text Copied", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Constant.showToast(Constant.emptyError);
            }
        });

    }


    public String makeStylishOf(int position, CharSequence charSequence) {
        char[] charArray = charSequence.toString().toLowerCase().toCharArray();
        return applyStyle(charArray, FontUtils.strings[position]);
    }

    private String applyStyle(char[] cArr, String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] - 'a' < 0 || cArr[i] - 'a' > 25) {
                stringBuffer.append(cArr[i]);
            } else {
                stringBuffer.append(strArr[cArr[i] - 'a']);
            }
        }
        return stringBuffer.toString();
    }


    @Override
    public int getItemCount() {
        return 44;
    }
}
