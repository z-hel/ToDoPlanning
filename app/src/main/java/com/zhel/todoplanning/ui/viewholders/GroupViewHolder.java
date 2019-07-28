package com.zhel.todoplanning.ui.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Group;

public class GroupViewHolder extends RecyclerView.ViewHolder {
    RecyclerView groupList;
//    CardView cardGroupName;
    TextView nameGroup;
    Button addItem;

    public GroupViewHolder(@NonNull View itemView) {
        super(itemView);
        groupList = itemView.findViewById(R.id.group_item_list);
//        cardGroupName = itemView.findViewById(R.id.card_name_group);
        nameGroup = itemView.findViewById(R.id.name_group);
        addItem = itemView.findViewById(R.id.add_item);
    }

    public void bind(Group group) {
        nameGroup.setText(group.getName());
//        addItem.setOnClickListener(v -> );
    }
}
