package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

public class Headline {
    @SerializedName("main")
    private String main;
    @SerializedName("kicker")
    private String kicker;
    @SerializedName("content_kicker")
    private String content_kicker;
    @SerializedName("print_headline")
    private String print_headline;
    @SerializedName("name")
    private String name;
    @SerializedName("seo")
    private String seo;
    @SerializedName("sub")
    private String sub;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getContent_kicker() {
        return content_kicker;
    }

    public void setContent_kicker(String content_kicker) {
        this.content_kicker = content_kicker;
    }

    public String getPrint_headline() {
        return print_headline;
    }

    public void setPrint_headline(String print_headline) {
        this.print_headline = print_headline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
