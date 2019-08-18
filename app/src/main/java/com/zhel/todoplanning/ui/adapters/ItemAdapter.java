package com.zhel.todoplanning.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Item;
import com.zhel.todoplanning.ui.viewholders.ItemViewHolder;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;
    private Context context;
    private OnEditItemClickListener onEditItemClickListener;

    public ItemAdapter(Context context, List<Item> items, OnEditItemClickListener onEditItemClickListener) {
        this.items = items;
        this.context = context;
        this.onEditItemClickListener = onEditItemClickListener;
    }

    public void addItem(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

//    public void setAllItems(List<Item> itemList) {
//        items.clear();
//        items.addAll(itemList);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.bind(items.get(i));
        itemViewHolder.itemView.setOnClickListener(x-> onEditItemClickListener.onEditItemClick(items.get(i)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnEditItemClickListener {
        public void onEditItemClick(Item item);
    }
}
