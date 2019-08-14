package com.zhel.todoplanning.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Group;
import com.zhel.todoplanning.ui.viewholders.GroupViewHolder;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private List<Group> groups;
    private Context context;
    private OnAddItemClickListener onAddItemClickListener;
//    private Button addItem;
//    private RecyclerView itemList;

    public GroupAdapter(Context context, List<Group> groups, OnAddItemClickListener onAddItemClickListener) {
        this.groups = groups;
        this.context = context;
        this.onAddItemClickListener = onAddItemClickListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_list, viewGroup, false);

//        addItem = view.findViewById(R.id.add_item);
//        addItem.setOnClickListener(v -> onAddItemClickListener.onAddItemClick(groups.get(i)));

        return new GroupViewHolder(view);
    }

    public void addGroup(Group group) {
        groups.add(group);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        groupViewHolder.bind(groups.get(i));
        groupViewHolder.itemView.findViewById(R.id.add_item).setOnClickListener(group -> onAddItemClickListener.onAddItemClick(groups.get(i), i));
//        groupViewHolder.itemView.findViewById(R.id.item_list_rv)
//        itemList = groupViewHolder.itemView.findViewById(R.id.item_list_rv);
//        ItemAdapter itemAdapter = new ItemAdapter(this, groups.get(i).getItems());
//        if (groups.get(i).getItems().isEmpty()) {
//            itemList.setVisibility(View.GONE);
//        }
//        else {
//            itemList.setVisibility(View.VISIBLE);
//        }

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public interface OnAddItemClickListener {
        void onAddItemClick(Group group, int index);
    }
}
