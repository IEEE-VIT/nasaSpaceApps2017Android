package com.ieeevit.spaceappsvellore.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String title;
    private String desc;
    private int image;

    private List<SubCategory> subCategories = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public List<SubCategory> getSubCategories(){
        return this.subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories){
        this.subCategories = subCategories;
    }
}