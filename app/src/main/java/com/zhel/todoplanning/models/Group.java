package com.zhel.todoplanning.models;

import java.io.Serializable;
import java.util.List;

public class Group {
    private List<Item> items;
    private String name;


    public Group(String name, List<Item> items) {
        this.items = items;
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
