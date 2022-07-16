package com.txc.healthand.networking.models;

public class Article {
    private String title;
    private String summary;
    private String imageUrl;

    public Article(String title, String summary, String imageUrl){
        this.title = title;
        this.summary = summary;
        this.imageUrl = imageUrl;
    }

    //TODO Construct from JSONObject

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getSummary(){
        return summary;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public String getImage(){
        return imageUrl;
    }

    public void setImage(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
