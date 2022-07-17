package com.txc.healthand.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.txc.healthand.BuildConfig;
import com.txc.healthand.R;
import com.txc.healthand.networking.ArticlesService;
import com.txc.healthand.networking.models.Article;
import com.txc.healthand.networking.models.ArticleList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArticlesService mService = ArticlesService.getInstance();
        String filters = "news_desk:(\"Health\" \"Health & Fitness\" \"Men's Health\" \"Women's Health\")";
        mService.getArticlesApiService().getArticles(filters, BuildConfig.API_KEY).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                if(response.isSuccessful()){
                    Log.e("RES", response.raw().request().url().toString());
                    for(Article a : response.body().getArticles()){
                        Log.e("RES", a.getHeadline().getMain());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                //TODO Toast
                Log.e("RES", "Call failed with: " + t.getMessage());
            }
        });
    }
}