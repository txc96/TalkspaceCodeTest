package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

public class Byline {
    @SerializedName("original")
    private String original;
    @SerializedName("organization")
    private String organization;
    @SerializedName("person")
    private List<Person> person;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }
}
