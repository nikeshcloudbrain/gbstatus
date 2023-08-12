package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gblatestversion.gbversion.gb.R;


public class ItemClickSupport {
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener = new RecyclerView.OnChildAttachStateChangeListener() {
        public void onChildViewDetachedFromWindow(View view) {
        }

        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(ItemClickSupport.this.mOnClickListener);
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(ItemClickSupport.this.mOnLongClickListener);
            }
        }
    };

    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClicked(mRecyclerView, mRecyclerView.getChildViewHolder(view).getAdapterPosition(), view);
            }
        }
    };

    public OnItemClickListener mOnItemClickListener;

    public OnItemLongClickListener mOnItemLongClickListener;

    public View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        public boolean onLongClick(View view) {
            if (mOnItemLongClickListener == null) {
                return false;
            }
            return ItemClickSupport.this.mOnItemLongClickListener.onItemLongClicked(mRecyclerView, mRecyclerView.getChildViewHolder(view).getAdapterPosition(), view);
        }
    };

    public final RecyclerView mRecyclerView;

    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int i, View view);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int i, View view);
    }

    private ItemClickSupport(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        recyclerView.setTag(R.id.item_click_support, this);
        recyclerView.addOnChildAttachStateChangeListener(this.mAttachListener);
    }

    public static ItemClickSupport addTo(RecyclerView recyclerView) {
        ItemClickSupport itemClickSupport = (ItemClickSupport) recyclerView.getTag(R.id.item_click_support);
        return itemClickSupport == null ? new ItemClickSupport(recyclerView) : itemClickSupport;
    }

    public static ItemClickSupport removeFrom(RecyclerView recyclerView) {
        ItemClickSupport itemClickSupport = (ItemClickSupport) recyclerView.getTag(R.id.item_click_support);
        if (itemClickSupport != null) {
            itemClickSupport.detach(recyclerView);
        }
        return itemClickSupport;
    }

    public ItemClickSupport setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
        return this;
    }

    private void detach(RecyclerView recyclerView) {
        recyclerView.removeOnChildAttachStateChangeListener(this.mAttachListener);
        recyclerView.setTag(R.id.item_click_support, (Object) null);
    }
}
