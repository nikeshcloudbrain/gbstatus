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



import com.gblatestversion.gbversion.gb.Activity.tools.EmoticonActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ItemMagixBinding;
import com.gblatestversion.gbversion.gb.databinding.ItemNativeListAdBinding;
import com.gblatestversion.gbversion.gb.utils.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    List<String> list = new ArrayList<>();
    String message = "";


    boolean isAds = false;

    public final int VIEW_AD = 100, VIEW_NORMAL = 101;





    public DecorAdapter(Activity activity, List<String> list, boolean isAds) {
        this.activity = activity;
        this.list = list;
        this.isAds = isAds;

        setAds(isAds);

    }
    public void setAds(boolean isCheck) {
        list.removeAll(Collections.singleton(null));
        int PARTICLE_AD_DISPLAY_COUNT = 5;

        ArrayList<String> tempArr = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.size() > PARTICLE_AD_DISPLAY_COUNT) {
                if (i != 0 && i % PARTICLE_AD_DISPLAY_COUNT == 0) {
                    tempArr.add(null);
                }
                tempArr.add(list.get(i));
            } else {
                tempArr.add(list.get(i));
            }
        }
        if (list.size() > 0) {
            if (list.size() % PARTICLE_AD_DISPLAY_COUNT == 0) {
                tempArr.add(null);
            }
        }

        this.list = tempArr;
        if (isCheck) notifyDataSetChanged();
    }
    public void refresh(List<String> list) {
        if (isAds) {
            this.list = list;
            setAds(isAds);
        } else {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    public void refreshText(String list) {

        this.message = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) == null)
            return VIEW_AD;
        else return VIEW_NORMAL;
    }


    public class AdHolder extends RecyclerView.ViewHolder {
        ItemNativeListAdBinding binding;

        AdHolder(ItemNativeListAdBinding view) {
            super(view.getRoot());
            binding = view;
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ItemMagixBinding binding;

        public ItemHolder(@NonNull ItemMagixBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_AD)
            return new AdHolder(ItemNativeListAdBinding.inflate(LayoutInflater.from(activity), parent, false));
        else
            return new ItemHolder(ItemMagixBinding.inflate(LayoutInflater.from(activity), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AdHolder) {
            WFSAppLoadAds.getInstance().displayListNativeAds(activity, ((AdHolder) holder).binding.frameViewAds);
        }else {
            ItemHolder holder1 = (ItemHolder) holder;
            if (message.equalsIgnoreCase("")) {
                holder1.binding.txtMsg.setText("");
                holder1.binding.txtMsg.setText(list.get(position).toString().replace("obj$ect", "mili"));
            } else {
                holder1.binding.txtMsg.setText(list.get(position).replace("obj$ect", message));
            }

            if (activity instanceof EmoticonActivity) {
                holder1.binding.txtMsg.setTextSize(18f);
            }

            holder1.binding.ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!holder1.binding.txtMsg.getText().toString().equalsIgnoreCase("") ||  activity instanceof EmoticonActivity) {
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
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
