package com.zhel.todoplanning.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        groups = new ArrayList<>();

        layoutMain = findViewById(R.id.main_layout);

//        items.add(new Item("item name", false));
//        groups.add(new Group("group", items));

        addTextGroup = findViewById(R.id.add_text_group);
        addTextGroup.setOnClickListener(v -> onAddTextGroupClick());


        groupRV = findViewById(R.id.group_item_list);
        groupAdapter = new GroupAdapter(this, groups, (Group group) -> {
            Intent myIntent = new Intent(this, ItemActivity.class);
            myIntent.putExtra("item", group.getName());
            this.startActivity(myIntent);
        });
        groupRV.setAdapter(groupAdapter);


    }

    public static void onAddItemClick(Group group) {
//        Intent myIntent = new Intent(this, ItemActivity.class);
//        myIntent.putExtra("item", group); //Optional parameters
//        this.startActivity(myIntent);
    }

    public void onAddNameTextGroupClick() {

        String name = textGroupName.getText().toString();
        if (!name.isEmpty()) {
            items = new ArrayList<>();
            groupAdapter.addGroup(new Group(name, items));
//            itemAdapter = new ItemAdapter(this, items);
//            itemRV.setAdapter(itemAdapter);
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
