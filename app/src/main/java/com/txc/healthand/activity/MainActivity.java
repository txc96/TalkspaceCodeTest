package com.txc.healthand.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.txc.healthand.BuildConfig;
import com.txc.healthand.R;
import com.txc.healthand.activity.models.ArticleObject;
import com.txc.healthand.activity.models.Filter;
import com.txc.healthand.activity.ui.ArticlesAdapter;
import com.txc.healthand.activity.ui.SpinnerAdapter;
import com.txc.healthand.networking.ArticlesService;
import com.txc.healthand.networking.models.Article;
import com.txc.healthand.networking.models.NYTimesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SpinnerAdapter.Callback, ArticlesAdapter.Callback{

    private RecyclerView articlesList;
    private ArticlesAdapter articlesAdapter;
    private ArrayList<Filter> filterOptions;
    private ArticlesService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesList = findViewById(R.id.articles_list);
        Spinner filterSpinner = findViewById(R.id.category_spinner);
        Button optionsButton = findViewById(R.id.options_button);

        filterOptions = new ArrayList<>();
        for(String s : (getResources().getStringArray(R.array.filter_options))){
            Filter f = new Filter(s, true);
            filterOptions.add(f);
        }
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, this, 0, filterOptions);
        filterSpinner.setAdapter(spinnerAdapter);

        mService = ArticlesService.getInstance();

        getArticles();
    }

    @Override
    public void onCheckboxChanged() {
        getArticles();
    }

    @Override
    public void onArticleClicked(String url) {
        
    }

    @Override
    public void onArticleLongCLicked(String articleAbstract, String url) {

    }

    /**
     * Adapter Method
     * */
    private void renderArticles(ArrayList<ArticleObject> articleObjects){
        if(articlesAdapter == null){
            articlesAdapter = new ArticlesAdapter(MainActivity.this, articleObjects, MainActivity.this);
            articlesList.setAdapter(articlesAdapter);
            articlesList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
        else{
            //TODO come back and optimize this
            articlesAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Request Method
     * */
    //Handle retrofit call to get articles list based on filter list
    private void getArticles(){
        String filters = "news_desk:(";
        for(Filter f : filterOptions){
            if(f.isSelected()){
                filters = filters + addQuotesToFilter(f.getTitle()) + " ";
            }
        }
        filters = filters + ")";

        mService.getArticlesApiService().getArticles(filters, BuildConfig.API_KEY).enqueue(new Callback<NYTimesResponse>() {
            @Override
            public void onResponse(Call<NYTimesResponse> call, Response<NYTimesResponse> response) {
                if(response.isSuccessful()){
                    Log.e("RES", response.raw().request().url().toString());
                    List<com.txc.healthand.networking.models.Article> articles = response.body().getDocs().getArticles();
                    if(response.body().getDocs() != null && articles != null){
                        ArrayList<ArticleObject> articleObjects = new ArrayList<>();
                        for(int i = 0; i < articles.size(); i++){
                            ArticleObject articleObject = new ArticleObject(
                                    getArticleImage(articles.get(i)),
                                    articles.get(i).getHeadline().getMain(),
                                    articles.get(i).getSnippet(),
                                    getAuthorName(articles.get(i)),
                                    articles.get(i).getWeb_url()
                            );
                            articleObjects.add(articleObject);
                        }
                        renderArticles(articleObjects);
                    }
                    else{
                        Log.e("RES", "Null response");
                        handleToast(MainActivity.this, "Response was null");
                    }
                }
            }

            @Override
            public void onFailure(Call<NYTimesResponse> call, Throwable t) {
                Log.e("RES", "Call failed with: " + t.getMessage());
                handleToast(MainActivity.this, "Call failed with: " + t.getMessage());
            }
        });
    }

    /**
     * Helper Methods
     * */
    //Wrap string in escaped quotes for API filters
    private String addQuotesToFilter(String s){
        return "\"" + s + "\"";
    }

    //show toast message to update user to app's status (mostly for requests)
    private void handleToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private String getArticleImage(Article article){
        if(article.getMultimedia() != null){
            return "https://www.nytimes.com/" + article.getMultimedia().get(0).getUrl();
        }
        else{
            return "";
        }
    }

    //get the 3 separate name strings and combine them into one
    private String getAuthorName(Article article){
        String author = "";
        String middleName = article.getByline().getPerson().get(0).getMiddlename();
        author = author + article.getByline().getPerson().get(0).getFirstname() + " ";
        if(middleName != null && !middleName.equals("null") && !middleName.isEmpty()){
            author = author + middleName + " ";
        }
        author = author + article.getByline().getPerson().get(0).getLastname();
        return author;
    }
}