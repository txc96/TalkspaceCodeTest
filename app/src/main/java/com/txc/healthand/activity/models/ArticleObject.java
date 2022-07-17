package com.txc.healthand.activity.models;

public class ArticleObject {
    String imageUrl, title, articleAbstract, author, webUrl;

    public ArticleObject(String imageUrl, String title, String articleAbstract, String author, String webUrl){
        this.imageUrl = imageUrl;
        this.title = title;
        this.articleAbstract = articleAbstract;
        this.author = author;
        this.webUrl = webUrl;
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
}
