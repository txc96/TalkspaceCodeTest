package com.txc.healthand.networking.models;

import org.json.JSONArray;

public class Byline {
    String original, organization;
    JSONArray person;

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

    public JSONArray getPerson() {
        return person;
    }

    public void setPerson(JSONArray person) {
        this.person = person;
    }
}
