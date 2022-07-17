package com.txc.healthand.activity.models;

public class Filter {
    private String title;
    private boolean selected;

    public Filter(String title, boolean selected){
        this.title = title;
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
