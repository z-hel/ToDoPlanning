package com.zhel.todoplanning.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhel.todoplanning.R;

public class ItemActivity extends AppCompatActivity {
    private TextView groupNameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_edit_item_text);
        Intent intent = getIntent();
        String groupName = intent.getStringExtra("item");

        groupNameTV = findViewById(R.id.group_name_item);

        groupNameTV.setText(groupName);
    }
}
