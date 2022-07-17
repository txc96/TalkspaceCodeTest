package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

public class Legacy {
    @SerializedName("xlarge")
    private String xlarge;
    @SerializedName("xlargewidth")
    private int xlargewidth;
    @SerializedName("xlargeheight")
    private int xlargeheight;

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    public int getXlargewidth() {
        return xlargewidth;
    }

    public void setXlargewidth(int xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    public int getXlargeheight() {
        return xlargeheight;
    }

    public void setXlargeheight(int xlargeheight) {
        this.xlargeheight = xlargeheight;
    }
}
