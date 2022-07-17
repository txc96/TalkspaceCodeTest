package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

public class Article {
    @SerializedName("web_url")
    private String web_url;
    @SerializedName("snippet")
    private String snippet;
    @SerializedName("print_section")
    private String print_section;
    @SerializedName("source")
    private String source;
    @SerializedName("pub_date")
    private String pub_date;
    @SerializedName("document_type")
    private String document_type;
    @SerializedName("news_desk")
    private String news_desk;
    @SerializedName("section_name")
    private String section_name;
    @SerializedName("type_of_material")
    private String type_of_material;
    @SerializedName("_id")
    private String _id;
    @SerializedName("uri")
    private String uri;
    @SerializedName("print_page")
    private int print_page;
    @SerializedName("word_count")
    private int word_count;
    @SerializedName("mutlimedia")
    List<Mulitmedia> mutlimedia;
    @SerializedName("keywords")
    List<Keyword> keywords;
    @SerializedName("headline")
    Headline headline;
    @SerializedName("byline")
    Byline byline;

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPrint_section() {
        return print_section;
    }

    public void setPrint_section(String print_section) {
        this.print_section = print_section;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getNews_desk() {
        return news_desk;
    }

    public void setNews_desk(String news_desk) {
        this.news_desk = news_desk;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getPrint_page() {
        return print_page;
    }

    public void setPrint_page(int print_page) {
        this.print_page = print_page;
    }

    public int getWord_count() {
        return word_count;
    }

    public void setWord_count(int word_count) {
        this.word_count = word_count;
    }

    public List<Mulitmedia> getMutlimedia() {
        return mutlimedia;
    }

    public void setMutlimedia(List<Mulitmedia> mutlimedia) {
        this.mutlimedia = mutlimedia;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }
}
