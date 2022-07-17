package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleList {
    @SerializedName("docs")
    private List<Article> articles;

    public List<Article> getArticles(){
        return articles;
    }

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }
}
