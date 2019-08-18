package com.zhel.todoplanning.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Group;
import com.zhel.todoplanning.models.Item;
import com.zhel.todoplanning.ui.adapters.GroupAdapter;
import com.zhel.todoplanning.ui.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.zhel.todoplanning.ui.activities.ItemActivity.GROUP_EXTRA;
import static com.zhel.todoplanning.ui.activities.ItemActivity.INDEX_GROUP;
import static com.zhel.todoplanning.ui.activities.ItemActivity.INDEX_ITEM_EXTRA;
import static com.zhel.todoplanning.ui.activities.ItemActivity.ITEM_EXTRA;

public class MainActivity extends AppCompatActivity {

    private List<Group> groups;
    private List<Item> items;
    private RecyclerView groupRV;
    private RecyclerView itemRV;
    private Button addTextGroup;
    private GroupAdapter groupAdapter;
    private ItemAdapter itemAdapter;
    private PopupWindow popupAddTextGroup;
    private EditText textGroupName;
    private Button addTextGroupName;
    private TextView textGroupPopup;

    private MainActivity mainActivity;
    private ConstraintLayout layoutMain;

    private int indexGroup;
    public static String MAIN_EXTRA = "item";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        Item item = (Item) data.getSerializableExtra(ITEM_EXTRA);
        int index = data.getIntExtra(INDEX_GROUP, 0);
        int indexItem = data.getIntExtra(INDEX_ITEM_EXTRA, Integer.parseInt(INDEX_ITEM_EXTRA));
//            items = groupWithItem.getItems();
        items = groups.get(index).getItems();
        if (indexItem == Integer.parseInt(INDEX_ITEM_EXTRA)) {

            items.add(item);
        }
        else {
            items.get(indexItem).setItemName(item.getItemName());
        }


            itemRV = groupRV.getChildAt(index).findViewById(R.id.item_list_rv);
            itemAdapter = new ItemAdapter(this, items, i -> {
                Intent myIntent = new Intent(this, ItemActivity.class);
                myIntent.putExtra(INDEX_ITEM_EXTRA, i);
                myIntent.putExtra(GROUP_EXTRA, groups.get(index));
                myIntent.putExtra(INDEX_GROUP, index);

                startActivityForResult(myIntent, 1);
            });
            itemRV.setAdapter(itemAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        groups = new ArrayList<>();
        layoutMain = findViewById(R.id.main_layout);
        addTextGroup = findViewById(R.id.add_text_group);
        addTextGroup.setOnClickListener(v -> onAddTextGroupClick());


        groupRV = findViewById(R.id.group_item_list);

        groupAdapter = new GroupAdapter(this, groups, (group, index) -> {
            Intent myIntent = new Intent(this, ItemActivity.class);
            myIntent.putExtra(GROUP_EXTRA, group);
            myIntent.putExtra(INDEX_GROUP, index);
            myIntent.putExtra(INDEX_ITEM_EXTRA, Integer.parseInt(INDEX_ITEM_EXTRA));

            startActivityForResult(myIntent, 1);
//            this.startActivity(myIntent);
        }, this::dropDownItems);
        groupRV.setAdapter(groupAdapter);


    }

    public void dropDownItems(int i) {
        try {
            View view = groupRV.getChildAt(i).findViewById(R.id.item_list_rv);
            if (view.getVisibility() == View.VISIBLE)
                view.setVisibility(View.GONE);
            else view.setVisibility(View.VISIBLE);
        } catch (Exception e) {

        }
    }

    public void onAddNameTextGroupClick() {

        String name = textGroupName.getText().toString();
        if (!name.isEmpty()) {
            items = new ArrayList<>();
            groupAdapter.addGroup(new Group(name, items));
            popupAddTextGroup.dismiss();
        }
    }

    public void onAddTextGroupClick() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);


        View customView = inflater.inflate(R.layout.add_select_group_popup,null);

        ImageButton close = customView.findViewById(R.id.close_image);
        close.setOnClickListener(cls -> {
            popupAddTextGroup.dismiss();

        });

        popupAddTextGroup = new PopupWindow(
                customView,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true
        );
//        layoutMain.setBackgroundColor(Color.GRAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layoutMain.setForeground(new ColorDrawable(Color.argb(70, 105, 105, 105)));
        }
        ConstraintLayout layoutPopup = customView.findViewById(R.id.popup_layout);

        popupAddTextGroup.showAtLocation(customView, Gravity.CENTER,0,0);
        layoutPopup.setBackgroundColor(Color.WHITE);
        popupAddTextGroup.setOnDismissListener(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layoutMain.setForeground(null);//.setBackgroundColor(Color.WHITE);
            }
            hideKeyboard();
        });


        textGroupName = customView.findViewById(R.id.add_text_group_name);
        addTextGroupName = customView.findViewById(R.id.add_group_btn);
        textGroupPopup = customView.findViewById(R.id.text_popup);

        addTextGroupName.setOnClickListener(v -> onAddNameTextGroupClick());

    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupAddTextGroup != null)
            popupAddTextGroup.dismiss();
    }
}
