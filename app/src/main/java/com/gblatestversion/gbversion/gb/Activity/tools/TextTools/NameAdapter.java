package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gblatestversion.gbversion.gb.R;


public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {
    Context context;
    String[] list;

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llItemName;
        TextView txtItemName;

        public ViewHolder(View view) {
            super(view);
            llItemName =  view.findViewById(R.id.llItemNameId);
            txtItemName =  view.findViewById(R.id.txtItemNameId);
        }
    }

    public NameAdapter(Context context2, String[] strArr) {
        this.context = context2;
        this.list = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_name, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String str = this.list[i];
        StringBuilder sb = new StringBuilder(str);
        viewHolder.txtItemName.setText(str + NameTextListActivity.text + sb.reverse().toString());
    }

    public int getItemCount() {
        return this.list.length;
    }

    public void filter(String str) {
        NameTextListActivity.text = "";
        if (str.length() == 0) {
                NameTextListActivity.text = context.getResources().getString(R.string.preview);
        } else {
            NameTextListActivity.text = str;
        }
        notifyDataSetChanged();
    }
}
