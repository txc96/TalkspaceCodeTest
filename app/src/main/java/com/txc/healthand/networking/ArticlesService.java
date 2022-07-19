package com.txc.healthand.networking;

import com.txc.healthand.networking.models.Article;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesService {
    private static ArticlesService SINGLE_INSTANCE;
    private static ArticlesApiService articlesApiService;

    //Create singleton of the service
    public static ArticlesService getInstance() {
        if (SINGLE_INSTANCE == null) {
            SINGLE_INSTANCE = new ArticlesService();
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.nytimes.com/svc/search/v2/")
                    .build();
            articlesApiService = retrofit.create(ArticlesApiService.class);
        }
        return SINGLE_INSTANCE;
    }

    public ArticlesApiService getArticlesApiService() {
        return articlesApiService;
    }
}
