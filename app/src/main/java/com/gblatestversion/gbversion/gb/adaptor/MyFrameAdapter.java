package com.gblatestversion.gbversion.gb.adaptor;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.gblatestversion.gbversion.gb.Activity.DPCreatorActivity;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ItemFrameBinding;
import com.gblatestversion.gbversion.gb.databinding.ItemNativeListAdBinding;
import com.gblatestversion.gbversion.gb.model.DpGen.Emage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyFrameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity con;
    private List<Emage> arrayL;
    public final int VIEW_AD = 100, VIEW_NORMAL = 101;

    public MyFrameAdapter(Activity context, List<Emage> arrayL) {
        this.con = context;
        this.arrayL = arrayL;
        setAds(true);

    }

    public void setAds(boolean isCheck) {
        arrayL.removeAll(Collections.singleton(null));
        int PARTICLE_AD_DISPLAY_COUNT = 5 * 4;

        ArrayList<Emage> tempArr = new ArrayList<>();
        for (int i = 0; i < arrayL.size(); i++) {
            if (arrayL.size() > PARTICLE_AD_DISPLAY_COUNT) {
                if (i != 0 && i % PARTICLE_AD_DISPLAY_COUNT == 0) {
                    tempArr.add(null);
                }
                tempArr.add(arrayL.get(i));
            } else {
                tempArr.add(arrayL.get(i));
            }
        }
        if (arrayL.size() > 0) {
            if (arrayL.size() % PARTICLE_AD_DISPLAY_COUNT == 0) {
                tempArr.add(null);
            }
        }

        this.arrayL = tempArr;
        if (isCheck) notifyDataSetChanged();
    }

    public static class AdHolder extends RecyclerView.ViewHolder {
        ItemNativeListAdBinding binding;

        public AdHolder(@NonNull ItemNativeListAdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFrameBinding binding;

        public ViewHolder(@NonNull ItemFrameBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == VIEW_AD)
            return new AdHolder(ItemNativeListAdBinding.inflate(LayoutInflater.from(con), viewGroup, false));
        else
            return new ViewHolder(ItemFrameBinding.inflate(LayoutInflater.from(con), viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof AdHolder) {
            WFSAppLoadAds.getInstance().displayListNativeAds(con, ((AdHolder) holder).binding.frameViewAds);
        }else {
            ViewHolder holder1 = (ViewHolder) holder;
//            Log.e("TAG", "onBindViewHolder: " + arrayL.get(i).getPreview());
            Glide.with(con).load(arrayL.get(i).getPreview()).into(holder1.binding.imgFrame);

            holder1.binding.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(con, DPCreatorActivity.class);
                    intent.putExtra("Frames", arrayL.get(i).getPreview());
                    con.startActivity(intent);

                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (arrayL.get(position) == null)
            return VIEW_AD;
        else return VIEW_NORMAL;

    }


    @Override
    public int getItemCount() {
        return arrayL.size();
    }


}
