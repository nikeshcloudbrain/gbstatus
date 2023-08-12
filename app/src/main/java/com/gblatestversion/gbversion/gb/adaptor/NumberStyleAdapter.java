package com.gblatestversion.gbversion.gb.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gblatestversion.gbversion.gb.Activity.tools.TextTools.NumericListActivity;
import com.gblatestversion.gbversion.gb.R;


public class NumberStyleAdapter extends RecyclerView.Adapter<NumberStyleAdapter.ViewHolder> {
    Context context;
    String[] list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItemNumber;
        TextView txtItemNumber;

        public ViewHolder(View view) {
            super(view);
            llItemNumber = view.findViewById(R.id.llItemNumberId);
            txtItemNumber = view.findViewById(R.id.txtItemNumberId);
        }
    }

    public NumberStyleAdapter(Context context2, String[] strArr) {
        this.context = context2;
        this.list = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_number, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        StringBuilder sb = new StringBuilder();
        String[] split = this.list[i].split(" ");
        for (int i2 = 0; i2 < NumericListActivity.text.length(); i2++) {
            sb.append(split[Character.digit(NumericListActivity.text.charAt(i2), 10)]);
        }
        viewHolder.txtItemNumber.setText(sb.toString());
    }

    public int getItemCount() {
        return this.list.length;
    }

    public void filter(String str) {
        NumericListActivity.text = "";
        if (str.length() == 0) {
            NumericListActivity.text = "12345";
        } else {
            NumericListActivity.text = str;
        }
        notifyDataSetChanged();
    }
}
