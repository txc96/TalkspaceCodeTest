package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ArticleList {
    @SerializedName("docs")
    private List<Article> articles;
    @SerializedName("meta")
    private JSONObject meta;

    public List<Article> getArticles(){
        return articles;
    }

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }

    public JSONObject getMeta() {
        return meta;
    }

    public void setMeta(JSONObject meta) {
        this.meta = meta;
    }
}
