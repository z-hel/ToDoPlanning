package com.zhel.todoplanning.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.zhel.todoplanning.R;
import com.zhel.todoplanning.models.Group;
import com.zhel.todoplanning.models.Item;
import com.zhel.todoplanning.ui.adapters.ItemAdapter;

import java.util.List;

public class ItemActivity extends AppCompatActivity {
    private Item item;
    private List<Item> items;
    private TextView groupNameTV;
    private TextView textItem;
    private Button backBtn;
    private Button saveTextItem;
    private ItemAdapter itemAdapter;

    public static String GROUP_EXTRA = "group";
    public static String ITEM_EXTRA = "item";
    public static String INDEX_GROUP = "indexGroup";
    Group group;
    int indexGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_edit_item_text);
        Intent intent = getIntent();
        group = (Group) intent.getSerializableExtra(GROUP_EXTRA);
        indexGroup = intent.getIntExtra(INDEX_GROUP, 0);
//        items = new ArrayList<>();
//        itemAdapter = new ItemAdapter(this, items);


        groupNameTV = findViewById(R.id.group_name_item);
        textItem = findViewById(R.id.item_text);
        backBtn = findViewById(R.id.back_item_to_main);
        saveTextItem = findViewById(R.id.save_text_item);

        backBtn.setOnClickListener(back -> onBackPressed());
        saveTextItem.setOnClickListener(save -> saveText());

        groupNameTV.setText(group.getName());
    }

    public void saveText() {



        String text = textItem.getText().toString();
        item = new Item(text, false);
//        MainActivity.onSaveItem(item, group);
//        onBackPressed();


//        items = group.getItems();
//        items.add(item);
//        group.setItems(items);
        Intent myIntent = new Intent(this, MainActivity.class);
//        myIntent.putExtra(ITEM_EXTRA, group);
        myIntent.putExtra(INDEX_GROUP, indexGroup);
        myIntent.putExtra(ITEM_EXTRA, item);

        setResult(RESULT_OK, myIntent);
        finish();
//        this.startActivity(myIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
