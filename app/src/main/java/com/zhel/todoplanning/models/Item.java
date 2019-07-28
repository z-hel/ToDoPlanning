package com.zhel.todoplanning.models;

public class Item {
    private String itemName;
    private boolean isStrikeout;

    public Item(String itemName, boolean isStrikeout) {
        this.itemName = itemName;
        this.isStrikeout = isStrikeout;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isStrikeout() {
        return isStrikeout;
    }

    public void setStrikeout(boolean strikeout) {
        isStrikeout = strikeout;
    }
}
