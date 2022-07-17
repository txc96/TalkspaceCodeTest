package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

public class Multimedia {
    @SerializedName("rank")
    private int rank;
    @SerializedName("subtype")
    private String subtype;
    @SerializedName("caption")
    private String caption;
    @SerializedName("credit")
    private String credit;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;
    @SerializedName("height")
    private int height;
    @SerializedName("width")
    private int width;
    @SerializedName("legacy")
    private Legacy legacy;
    @SerializedName("crop_name")
    private String crop_name;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }
}
