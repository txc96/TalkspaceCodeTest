package com.txc.healthand.activity.models;

public class ArticleObject {
    String imageUrl, title, articleAbstract, author, webUrl, news_desk;

    //Custom article object that has only the info we need, easier to setup adapter for
    //A bit memory inefficient since we have another article model (also slightly confusing)

    public ArticleObject(String imageUrl, String title, String articleAbstract, String author, String webUrl, String news_desk){
        this.imageUrl = imageUrl;
        this.title = title;
        this.articleAbstract = articleAbstract;
        this.author = author;
        this.webUrl = webUrl;
        this.news_desk = news_desk;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getNews_desk() {
        return news_desk;
    }

    public void setNews_desk(String news_desk) {
        this.news_desk = news_desk;
    }
}
