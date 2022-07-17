package com.txc.healthand.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.txc.healthand.BuildConfig;
import com.txc.healthand.R;
import com.txc.healthand.networking.ArticlesService;
import com.txc.healthand.networking.models.Article;
import com.txc.healthand.networking.models.ArticleList;
import com.txc.healthand.networking.models.NYTimesResponse;

import org.json.JSONException;

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
        mService.getArticlesApiService().getArticles(filters, BuildConfig.API_KEY).enqueue(new Callback<NYTimesResponse>() {
            @Override
            public void onResponse(Call<NYTimesResponse> call, Response<NYTimesResponse> response) {
                if(response.isSuccessful()){
                    Log.e("RES", response.raw().request().url().toString());
                    if(response.body().getDocs() != null){
                        if(response.body().getDocs().getArticles() != null){
                            for(int i = 0; i < response.body().getDocs().getArticles().size(); i++){
                                Log.e("RES", ((Article)response.body().getDocs().getArticles().get(i)).getHeadline().getMain());
                            }
                        }
                        else{
                            Log.e("RES", "NULL response 2");
                        }
                    }
                    else{
                        Log.e("RES", "NULL response 1");
                    }
                }
            }

            @Override
            public void onFailure(Call<NYTimesResponse> call, Throwable t) {
                //TODO Toast
                Log.e("RES", "Call failed with: " + t.getMessage());
            }
        });
    }
}