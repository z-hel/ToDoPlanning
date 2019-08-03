package com.zhel.todoplanning.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Group;
import com.zhel.todoplanning.ui.viewholders.GroupViewHolder;

import java.util.List;
import java.util.zip.Inflater;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private List<Group> groups;
    private Context context;
    private OnAddClickListener onAddClickListener;

    public GroupAdapter(Context context, List<Group> groups) {
        this.groups = groups;
        this.context = context;
//        this.onAddClickListener = onAddClickListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_list, viewGroup, false);
//        view.findViewById(R.id.add_item).setOnClickListener(v -> onAddClickListener.onAddClick(groups.get(i)));
        return new GroupViewHolder(view);
    }

    public void addGroup(Group group) {
        groups.add(group);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        groupViewHolder.bind(groups.get(i));
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public interface OnAddClickListener {
        void onAddClick(Group group);
    }
}
