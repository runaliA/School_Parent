package com.infitronics.www.learn_gridview;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Country {
    String code = null;
    String name = null;
    boolean selected = true;

    public Country(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
