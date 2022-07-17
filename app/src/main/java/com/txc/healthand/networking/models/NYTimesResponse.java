package com.txc.healthand.networking.models;

import com.google.gson.annotations.SerializedName;

public class NYTimesResponse {
    @SerializedName("status")
    String status;
    @SerializedName("copyright")
    String copyright;
    @SerializedName("response")
    ArticleList docs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArticleList getDocs() {
        return docs;
    }

    public void setDocs(ArticleList docs) {
        this.docs = docs;
    }
}
