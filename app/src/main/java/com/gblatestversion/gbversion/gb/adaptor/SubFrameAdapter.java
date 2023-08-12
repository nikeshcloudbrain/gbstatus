package com.gblatestversion.gbversion.gb.adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gblatestversion.gbversion.gb.utils.OnItemClickListener;
import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.databinding.ItemSubFrameBinding;
import com.gblatestversion.gbversion.gb.model.DpGen.Emage;

import java.util.List;

public class SubFrameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity con;
    private List<Emage> arrayL;
    private final OnItemClickListener listener;
    int selectedPosition = -1;
    int lastSelectedPosition = -1;

    public SubFrameAdapter(Activity context, List<Emage> arrayL, OnItemClickListener listener) {
        this.con = context;
        this.arrayL = arrayL;
        this.listener = listener;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemSubFrameBinding binding;

        public ViewHolder(@NonNull ItemSubFrameBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(ItemSubFrameBinding.inflate(LayoutInflater.from(con), viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {


        ViewHolder myViewHolder = (ViewHolder) holder;
//        Log.e("TAG", "onBindViewHolder: " + arrayL.get(i).getPreview());
        Glide.with(con).load(arrayL.get(i).getPreview()).into(myViewHolder.binding.imgFrame);

        myViewHolder.binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lastSelectedPosition = selectedPosition;
                selectedPosition = i;
                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);



                listener.onItemClick(arrayL.get(i).getPreview());
                notifyDataSetChanged();
            }
        });

        if (selectedPosition == i) {
            myViewHolder.binding.click.setBackgroundResource( R.drawable.sel_frame_bg);
        } else {
            myViewHolder.binding.click.setBackgroundResource( R.drawable.unsel_frame_bg);
        }


    }


    @Override
    public int getItemCount() {
        return arrayL.size();
    }


}
