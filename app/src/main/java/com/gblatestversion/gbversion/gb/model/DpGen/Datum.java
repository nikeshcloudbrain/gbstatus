package com.gblatestversion.gbversion.gb.model.DpGen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("image")
    @Expose
    private List<Emage> emage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Emage> getImage() {
        return emage;
    }

    public void setImage(List<Emage> image) {
        this.emage = image;
    }

}
