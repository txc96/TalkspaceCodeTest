package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

public class Keyword {
    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;
    @SerializedName("rank")
    private int rank;
    @SerializedName("major")
    private String major;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
