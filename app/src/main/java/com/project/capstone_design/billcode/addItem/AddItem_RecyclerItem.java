package com.project.capstone_design.billcode.addItem;

public class AddItem_RecyclerItem {
    private int num;
    private String name;
    private String expDate;
    private String image;
    private boolean pushChecked;

    public AddItem_RecyclerItem(String name, String expDate, String image) {
        this.name = name;
        this.expDate = expDate;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getImage() {
        return image;
    }

    public boolean isPushChecked() {
        return pushChecked;
    }

    public void setPushChecked(boolean pushChecked) {
        this.pushChecked = pushChecked;
    }


    public AddItem_RecyclerItem() {}

    public AddItem_RecyclerItem(String name) {
        this.name = name;
    }

    public int getP_NUM() {

        return num;

    }

    public void setP_NUM(int num) {

        this.num = num;

    }

    public String getP_NAME() {

        return name;

    }

    public void setP_NAME(String name) {

        this.name = name;

    }

    public String getP_EXPDATE() {

        return expDate;

    }

    public void setP_EXPDATE(String expDate) {

        this.expDate = expDate;

    }
}