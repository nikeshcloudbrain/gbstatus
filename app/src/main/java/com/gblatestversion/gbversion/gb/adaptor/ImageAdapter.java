package com.gblatestversion.gbversion.gb.adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;

import com.gblatestversion.gbversion.gb.databinding.ItemImageBinding;
import com.gblatestversion.gbversion.gb.databinding.ItemNativeListAdBinding;
import com.gblatestversion.gbversion.gb.databinding.ItemVideoBinding;
import com.gblatestversion.gbversion.gb.databinding.ItemVoiceBinding;
import com.gblatestversion.gbversion.gb.model.FileModel;
import com.gblatestversion.gbversion.gb.utils.Constant;
import com.gblatestversion.gbversion.gb.utils.FileUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity activity;
    public List<FileModel> stringList = new ArrayList<>();
    onItemClickListener listener;
    String options;

    public final int VIEW_AD = 100, VIEW_NORMAL = 101;


    public void setAds(boolean isCheck) {
        stringList.removeAll(Collections.singleton(null));
        int PARTICLE_AD_DISPLAY_COUNT;
        if (options.equalsIgnoreCase(FileUtils.VOICE) || options.equalsIgnoreCase(FileUtils.DOCUMENT) || options.equalsIgnoreCase(FileUtils.AUDIO)|| options.equalsIgnoreCase(FileUtils.IMAGE)) {
            PARTICLE_AD_DISPLAY_COUNT = 5*3;

        }else {
            PARTICLE_AD_DISPLAY_COUNT = 5;

        }

        ArrayList<FileModel> tempArr = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.size() > PARTICLE_AD_DISPLAY_COUNT) {
                if (i != 0 && i % PARTICLE_AD_DISPLAY_COUNT == 0) {
                    tempArr.add(null);
                }
                tempArr.add(stringList.get(i));
            } else {
                tempArr.add(stringList.get(i));
            }
        }
        if (stringList.size() > 0) {
            if (stringList.size() % PARTICLE_AD_DISPLAY_COUNT == 0) {
                tempArr.add(null);
            }
        }

        this.stringList = tempArr;
        if (isCheck) notifyDataSetChanged();
    }
    public ImageAdapter(Activity activity, String options, onItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.options = options;
        setAds(true);
    }

    public void refresh(List<FileModel> list) {
        stringList = list;
        setAds(true);
    }

    public interface onItemClickListener {
        public void onItemClick(int position, FileModel value);

        public void onCheckClick(int position, FileModel value);
    }

    public class AdHolder extends RecyclerView.ViewHolder {
        ItemNativeListAdBinding binding;

        AdHolder(ItemNativeListAdBinding view) {
            super(view.getRoot());
            binding = view;
        }
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ItemImageBinding binding;

        public ImageHolder(@NonNull ItemImageBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        ItemVideoBinding binding;

        public VideoHolder(@NonNull ItemVideoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


    public class VoiceHolder extends RecyclerView.ViewHolder {
        ItemVoiceBinding binding;

        public VoiceHolder(@NonNull ItemVoiceBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_AD){
            return new AdHolder(ItemNativeListAdBinding.inflate(LayoutInflater.from(activity), parent, false));

        }else{
            if (options.equalsIgnoreCase(FileUtils.VOICE) || options.equalsIgnoreCase(FileUtils.DOCUMENT) || options.equalsIgnoreCase(FileUtils.AUDIO)) {
                return new VoiceHolder(ItemVoiceBinding.inflate(LayoutInflater.from(activity), parent, false));
            } else if (options.equalsIgnoreCase(FileUtils.VIDEO)) {
                return new VideoHolder(ItemVideoBinding.inflate(LayoutInflater.from(activity), parent, false));
            } else
                return new ImageHolder(ItemImageBinding.inflate(LayoutInflater.from(activity), parent, false));
        }



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdHolder) {
            WFSAppLoadAds.getInstance().displayListNativeAds(activity, ((AdHolder) holder).binding.frameViewAds);
        }else {
            if (holder instanceof VideoHolder) {
                VideoHolder videoHolder = (VideoHolder) holder;

                if (stringList.get(position).isSelected)
                    Glide.with(activity).load(R.drawable.ic_rcselect_shape).diskCacheStrategy(DiskCacheStrategy.ALL).into(videoHolder.binding.ivCheck);
                else
                    Glide.with(activity).load(R.drawable.ic_rcellipse).diskCacheStrategy(DiskCacheStrategy.ALL).into(videoHolder.binding.ivCheck);

                Glide.with(activity)
                        .load(stringList.get(position).filePath)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(videoHolder.binding.ivImage);

                videoHolder.binding.txtName.setText(stringList.get(position).fileName);
                videoHolder.binding.txtSize.setText(stringList.get(position).fileSize);

                videoHolder.binding.ivCheck.setOnClickListener(view -> listener.onCheckClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition())));
                videoHolder.itemView.setOnClickListener(view -> listener.onItemClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition())));

            } else if (holder instanceof ImageHolder) {
                ImageHolder imageHolder = (ImageHolder) holder;

                if (stringList.get(position).isSelected)
                    Glide.with(activity).load(R.drawable.ic_rcselect_shape).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageHolder.binding.ivCheck);
                else
                    Glide.with(activity).load(R.drawable.ic_rcellipse).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageHolder.binding.ivCheck);

                if (Constant.isVideoFile(stringList.get(position).filePath)) {
                    imageHolder.binding.ivPlay.setVisibility(View.VISIBLE);
                } else {
                    imageHolder.binding.ivPlay.setVisibility(View.GONE);
                }

                Glide.with(activity)
                        .load(stringList.get(position).filePath)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageHolder.binding.ivImage);

                imageHolder.binding.ivCheck.setOnClickListener(view -> listener.onCheckClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition())));
                imageHolder.itemView.setOnClickListener(view -> listener.onItemClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition())));

            } else {

                VoiceHolder voiceHolder = (VoiceHolder) holder;
                if (stringList.get(position).isSelected)
                    Glide.with(activity).load(R.drawable.ic_rcselect_shape).diskCacheStrategy(DiskCacheStrategy.ALL).into(voiceHolder.binding.ivCheck);
                else
                    Glide.with(activity).load(R.drawable.ic_rcellipse).diskCacheStrategy(DiskCacheStrategy.ALL).into(voiceHolder.binding.ivCheck);

                if (options.equalsIgnoreCase(FileUtils.DOCUMENT)) {
                    Glide.with(activity).load(R.drawable.wp_doc).diskCacheStrategy(DiskCacheStrategy.ALL).into(voiceHolder.binding.imageView);
                } else {
                    Glide.with(activity).load(R.drawable.wp_audio).diskCacheStrategy(DiskCacheStrategy.ALL).into(voiceHolder.binding.imageView);
                }

                voiceHolder.binding.size.setText(stringList.get(position).fileSize);
                voiceHolder.binding.name.setText(stringList.get(position).fileName);

                voiceHolder.binding.ivCheck.setOnClickListener(view -> listener.onCheckClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition())));
                voiceHolder.binding.ivImage.setOnClickListener(view -> listener.onItemClick(holder.getAdapterPosition(), stringList.get(holder.getAdapterPosition())));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (stringList.get(position) == null)
            return VIEW_AD;
        else return VIEW_NORMAL;
    }
    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
