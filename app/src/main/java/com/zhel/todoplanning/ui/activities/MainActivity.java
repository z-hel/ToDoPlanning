package com.zhel.todoplanning.ui.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;

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
    private EditText addTextGroupName;
    private MainActivity mainActivity;
    private ConstraintLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        groups = new ArrayList<>();

        layoutMain = findViewById(R.id.main_layout);

        items.add(new Item("item name", false));
        groups.add(new Group("group", items));

        addTextGroup = findViewById(R.id.add_text_group);
        addTextGroup.setOnClickListener(v -> onAddTextGroupClick());


        groupRV = findViewById(R.id.group_item_list);
        groupAdapter = new GroupAdapter(this, groups);
        groupRV.setAdapter(groupAdapter);
    }

    public static void onAddItemClick(Group group) {

    }

    public void onAddNameTextGroupClick() {

        showKeyboard();
        popupAddTextGroup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        popupAddTextGroup.update(Constraints.LayoutParams.WRAP_CONTENT, Constraints.LayoutParams.WRAP_CONTENT);

//        popupAddTextGroup.showAtLocation(layoutMain, Gravity.CENTER, 0,0);
//        popupAddTextGroup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void onAddTextGroupClick() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);


        View customView = inflater.inflate(R.layout.add_select_group_popup,null);

        ImageButton close = customView.findViewById(R.id.close_image);
        close.setOnClickListener(cls -> {
            popupAddTextGroup.dismiss();
            layoutMain.setBackgroundColor(Color.WHITE);
            hideKeyboard();
        });

//        showKeyboard();

        popupAddTextGroup = new PopupWindow(
                customView,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        if (Build.VERSION.SDK_INT >= 21){
            popupAddTextGroup.setElevation(5.0f);
        }
        layoutMain.setBackgroundColor(Color.GRAY);
        ConstraintLayout layoutPopup = customView.findViewById(R.id.popup_layout);


//        layoutMain.post(new Runnable() {
//            @Override
//            public void run() {
//                popupAddTextGroup.showAtLocation(layoutMain,
//                        Gravity.CENTER_VERTICAL, 0, 0); //set location here
//            }
//        });

//        popupAddTextGroup.setInputMethodMode();
        popupAddTextGroup.showAtLocation(layoutMain, Gravity.CENTER,0,0);
        layoutPopup.setBackgroundColor(Color.WHITE);


        addTextGroupName = customView.findViewById(R.id.add_text_group_name);

//        addTextGroupName.requestFocus();
//        popupAddTextGroup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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
}
