package com.txc.healthand.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.txc.healthand.BuildConfig;
import com.txc.healthand.R;
import com.txc.healthand.activity.models.ArticleObject;
import com.txc.healthand.activity.models.Filter;
import com.txc.healthand.activity.ui.ArticlesAdapter;
import com.txc.healthand.activity.ui.SpinnerAdapter;
import com.txc.healthand.networking.ArticlesService;
import com.txc.healthand.networking.models.Article;
import com.txc.healthand.networking.models.NYTimesResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SpinnerAdapter.Callback, ArticlesAdapter.Callback{

    private RecyclerView articlesList;
    private ArrayList<Filter> filterOptions;
    private ArticlesService mService;
    private int articlePage;
    private boolean lightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesList = findViewById(R.id.articles_list);
        articlesList.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button optionsButton = findViewById(R.id.options_button);

        //click event for light/dark mode button
        optionsButton.setOnClickListener(v -> {
            if(lightMode){
                //Set night mode for the theme and change button text
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                optionsButton.setText(getString(R.string.light_mode));
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                optionsButton.setText(getString(R.string.dark_mode));
            }
            lightMode = !lightMode;
        });

        //set up article search filters
        Spinner filterSpinner = findViewById(R.id.category_spinner);
        setUpFilters(filterSpinner);

        //create retrofit service singleton
        mService = ArticlesService.getInstance();

        //set up article navigation buttons
        Button previousButton = findViewById(R.id.previous_button);
        Button nextButton = findViewById(R.id.next_button);

        previousButton.setOnClickListener(v -> {
            if(articlePage > 0){
                articlePage--;
                if(nextButton.getVisibility() == View.GONE)
                    nextButton.setVisibility(View.VISIBLE);
            }

            if(articlePage == 0){
                previousButton.setVisibility(View.GONE);
            }
            getArticles();
        });

        nextButton.setOnClickListener(v -> {
            if(articlePage < 100){
                articlePage++;
                if(previousButton.getVisibility() == View.GONE)
                    previousButton.setVisibility(View.VISIBLE);
            }
            else{
                nextButton.setVisibility(View.GONE);
            }
            getArticles();
        });

        //get articles at the start
        getArticles();
    }

    /**
     * Adapter Callback Methods
     * */
    @Override
    public void onCheckboxChanged() {
        //refresh search with new filters
        getArticles();
    }

    @Override
    public void onArticleClicked(String url) {
        //open article in browser on single click
        Intent openInWebIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openInWebIntent);
    }

    @Override
    public void onArticleLongCLicked(String articleAbstract, String url, String title) {
        //Share article title, abstract, and url on long click
        //Concatenate abstract and url to for send intent
        String sharedContent = articleAbstract + "\n\n" + url;
        Intent shareArticleIntent = new Intent(Intent.ACTION_SEND);
        shareArticleIntent.putExtra(Intent.EXTRA_TEXT, sharedContent);
        shareArticleIntent.setType("text/plain");
        //make send intent title article title
        //TODO might be better to put this in the intent and create a shorter string for
        //the intent title
        Intent shareIntent = Intent.createChooser(shareArticleIntent, title);
        startActivity(shareIntent);
    }

    @Override
    public void onDownloadArticle(String title, String author, String imageUrl, String articleAbstract, String webUrl, String newsDesk) {
        //Get shared prefs
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();

        ArticleObject articleObject = new ArticleObject(imageUrl, title, articleAbstract, author, webUrl, newsDesk);

        //Get saved articles
        String articlesArrayString = mPrefs.getString("storedArticlesList", "");
        ArticleObject[] savedArticles;
        String json;
        //if no saved articles, add one
        if(articlesArrayString.isEmpty()){
            savedArticles = new ArticleObject[]{articleObject};
            json = gson.toJson(savedArticles);
            mPrefs.edit().putString("storedArticlesList", json).apply();
            handleToast(MainActivity.this, "Article Downloaded");
        }
        //otherwise, get the saved list and append new article to the end of it
        else{
            ArticleObject[] retrievedArticleObjects = gson.fromJson(articlesArrayString, ArticleObject[].class);
            if(retrievedArticleObjects != null && retrievedArticleObjects.length > 0){
                savedArticles = new ArticleObject[retrievedArticleObjects.length + 1];
                for(int i = 0; i < retrievedArticleObjects.length; i++){
                    savedArticles[i] = retrievedArticleObjects[i];
                }
                savedArticles[savedArticles.length-1] = articleObject;
                json = gson.toJson(savedArticles);
                mPrefs.edit().putString("storedArticlesList", json).apply();
                handleToast(MainActivity.this, "Article Downloaded");
            }
            else{
                handleToast(MainActivity.this, "There was a problem retrieving your stored articles");
            }
        }
    }

    /**
     * Adapter Method
     * */
    //Send articles to the adapter
    private void renderArticles(ArrayList<ArticleObject> articleObjects){
        //Using swap adapter instead of notifyDataSet... methods due to amount of changing data
        //and inefficiency of notifyDataSetChanged compared to having garbage collector
        //clean up old adapter
        articlesList.swapAdapter(new ArticlesAdapter(MainActivity.this, articleObjects, MainActivity.this), true);
    }

    /**
     * Read Local Artciles Method
     * */
    //Retrieve articles from local store
    private ArrayList<ArticleObject> getSavedArticles(){
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String articlesArrayString = mPrefs.getString("storedArticlesList", "");
        if(articlesArrayString.isEmpty()){
            handleToast(MainActivity.this, "There was a problem retrieving your stored articles");
            //Returns null if retrieval fails or is empty, null check required on the other end of this method
            return null;
        }
        else{
            ArticleObject[] retrievedArticleObjects = gson.fromJson(articlesArrayString, ArticleObject[].class);
            if(retrievedArticleObjects != null && retrievedArticleObjects.length > 0){
                return new ArrayList<>(Arrays.asList(retrievedArticleObjects));
            }
            else{
                handleToast(MainActivity.this, "There was a problem retrieving your stored articles");
                return null;
            }
        }
    }

    /**
     * Request Method
     * */
    //Handle retrofit call to get articles list based on filter list
    private void getArticles(){
        //Construct filter string based on checked filter items in the spinner
        String filters = "news_desk:(";
        for(Filter f : filterOptions){
            if(f.isSelected() && !f.getTitle().equals("Filters")){
                filters = filters + addQuotesToFilter(f.getTitle()) + " ";
            }
        }
        filters = filters + ")";

        mService.getArticlesApiService().getArticles(articlePage, filters, BuildConfig.API_KEY).enqueue(new Callback<NYTimesResponse>() {
            @Override
            public void onResponse(Call<NYTimesResponse> call, Response<NYTimesResponse> response) {
                if(response.isSuccessful()){
                    List<com.txc.healthand.networking.models.Article> articles = response.body().getDocs().getArticles();
                    ArrayList<ArticleObject> articleObjects = new ArrayList<>();
                    //Only check for and show local articles on page 1 (0)
                    //Not practical for production usage but easy to show saved articles first
                    if(articlePage < 1){
                        ArrayList<ArticleObject> localArticleObjects = getSavedArticles();
                        if(localArticleObjects != null){
                            articleObjects.addAll(localArticleObjects);
                        }
                    }
                    //Continue if we have articles to show
                    if(response.body().getDocs() != null && articles != null && articles.size() > 0){
                        for(int i = 0; i < articles.size(); i++){
                            int finalI = i;
                            //If on page 2+ (1+) add articles as normal
                            if(articlePage > 0){
                                ArticleObject articleObject = new ArticleObject(
                                        getArticleImage(articles.get(i)),
                                        articles.get(i).getHeadline().getMain(),
                                        articles.get(i).getSnippet(),
                                        getAuthorName(articles.get(i)),
                                        articles.get(i).getWeb_url(),
                                        articles.get(i).getNews_desk()
                                );
                                articleObjects.add(articleObject);
                            }
                            //If on page 1 (0) check list for existing titles first
                            //This will remove different articles with duplicate titles (unlikely but possible)
                            //Pretty inefficient, this can be done much better (.stream() in a for loop)
                            //Would be better to filter at the end, and maybe swap to a hashmap
                            else{
                                if(articleObjects.stream().noneMatch(a -> a.getTitle().equals(articles.get(finalI).getHeadline().getMain()))){
                                    ArticleObject articleObject = new ArticleObject(
                                            getArticleImage(articles.get(i)),
                                            articles.get(i).getHeadline().getMain(),
                                            articles.get(i).getSnippet(),
                                            getAuthorName(articles.get(i)),
                                            articles.get(i).getWeb_url(),
                                            articles.get(i).getNews_desk()
                                    );
                                    articleObjects.add(articleObject);
                                }
                            }
                        }
                    }
                    //Error message if call fails
                    else{
                        handleToast(MainActivity.this, "Response was null");
                    }
                    //Only call adapter if we have articles to show
                    if(articleObjects.size() > 0){
                        renderArticles(articleObjects);
                    }
                    else{
                        handleToast(MainActivity.this, "No articles");
                    }
                }
            }

            @Override
            public void onFailure(Call<NYTimesResponse> call, Throwable t) {
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
        if(article.getMultimedia() != null && article.getMultimedia().size() > 0){
            return "https://www.nytimes.com/" + article.getMultimedia().get(0).getUrl();
        }
        else{
            return "";
        }
    }

    //get the 3 separate name strings and combine them into one
    private String getAuthorName(Article article){
        String author = "";
        if(article.getByline().getPerson() != null && article.getByline().getPerson().size() > 0){
            String middleName = article.getByline().getPerson().get(0).getMiddlename();
            author = author + article.getByline().getPerson().get(0).getFirstname() + " ";
            if(middleName != null && !middleName.equals("null") && !middleName.isEmpty()){
                author = author + middleName + " ";
            }
            author = author + article.getByline().getPerson().get(0).getLastname();
        }
        return author;
    }

    //helper method to set up filter spinner
    private void setUpFilters(Spinner filterSpinner){
        filterOptions = new ArrayList<>();
        //get filters from local string array
        for(String s : (getResources().getStringArray(R.array.filter_options))){
            Filter f = new Filter(s, true);
            filterOptions.add(f);
        }
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, this, 0, filterOptions);
        filterSpinner.setAdapter(spinnerAdapter);
    }
}