package com.txc.healthand.networking;

import com.txc.healthand.networking.models.Article;
import com.txc.healthand.networking.models.ArticleList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesApiService {
    @GET("articlesearch.json")
    Call<ArticleList> getArticles(@Query("fq") String filters, @Query("api-key") String apiKey);
}
