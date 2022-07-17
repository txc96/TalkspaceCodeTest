package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("middlename")
    private String middlename;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("qualifier")
    private String qualifier;
    @SerializedName("title")
    private String title;
    @SerializedName("role")
    private String role;
    @SerializedName("organiziation")
    private String organiziation;
    @SerializedName("rank")
    private String rank;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganiziation() {
        return organiziation;
    }

    public void setOrganiziation(String organiziation) {
        this.organiziation = organiziation;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
