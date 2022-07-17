package com.txc.healthand.networking;

import com.txc.healthand.networking.models.NYTimesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesApiService {
    @GET("articlesearch.json")
    Call<NYTimesResponse> getArticles(@Query("fq") String filters, @Query("api-key") String apiKey);
}
