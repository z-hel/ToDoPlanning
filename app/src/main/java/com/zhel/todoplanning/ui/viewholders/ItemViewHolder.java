package com.zhel.todoplanning.ui.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView itemText;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        itemText = itemView.findViewById(R.id.item);
    }

    public void bind(Item item) {
        itemText.setText(item.getItemName());
    }
}
