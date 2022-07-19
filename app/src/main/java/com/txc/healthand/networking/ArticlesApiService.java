package com.txc.healthand.networking;

import com.txc.healthand.networking.models.NYTimesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesApiService {
    @GET("articlesearch.json")
    //Page needs to come first for call to work correctly
    Call<NYTimesResponse> getArticles(@Query("page") int page, @Query("fq") String filters, @Query("api-key") String apiKey);
}
