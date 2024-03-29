package com.txc.healthand.activity.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.txc.healthand.R;
import com.txc.healthand.activity.models.ArticleObject;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private final Context context;
    private final Callback callback;
    private final ArrayList<ArticleObject> articleObjects;
    private boolean landscape;

    public ArticlesAdapter(Context context, ArrayList<ArticleObject> articleObjects, Callback callback, boolean landscape){
        this.context = context;
        this.articleObjects = articleObjects;
        this.callback = callback;
        this.landscape = landscape;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout cl;
        if(landscape){
            cl = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_landscape, parent, false);
        }
        else{
            cl = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_portrait, parent, false);
        }
        return new ArticlesAdapter.ViewHolder(cl);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(articleObjects.get(position).getImageUrl()).into(holder.image);
        holder.title.setText(articleObjects.get(position).getTitle());
        holder.articleAbstract.setText(articleObjects.get(position).getArticleAbstract());
        holder.author.setText(articleObjects.get(position).getAuthor());
        holder.tags.setText(articleObjects.get(position).getNews_desk());
        holder.article.setOnClickListener(v -> callback.onArticleClicked(articleObjects.get(holder.getAdapterPosition()).getWebUrl()));
        holder.article.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callback.onArticleLongCLicked(articleObjects.get(
                        holder.getAdapterPosition()).getArticleAbstract(),
                        articleObjects.get(holder.getAdapterPosition()).getWebUrl(),
                        articleObjects.get(holder.getAdapterPosition()).getTitle()
                );
                return false;
            }
        });
        holder.download.setOnClickListener(v -> callback.onDownloadArticle(
                articleObjects.get(holder.getAdapterPosition()).getTitle(),
                articleObjects.get(holder.getAdapterPosition()).getAuthor(),
                articleObjects.get(holder.getAdapterPosition()).getImageUrl(),
                articleObjects.get(holder.getAdapterPosition()).getArticleAbstract(),
                articleObjects.get(holder.getAdapterPosition()).getWebUrl(),
                articleObjects.get(holder.getAdapterPosition()).getNews_desk()
        ));
    }

    @Override
    public int getItemCount() {
        return articleObjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout article;
        private ImageView image;
        private TextView title, articleAbstract, author, tags;
        private ImageView download;

        public ViewHolder(View itemView) {
            super(itemView);
            article = itemView.findViewById(R.id.article);
            image = itemView.findViewById(R.id.article_image);
            title = itemView.findViewById(R.id.article_title);
            articleAbstract = itemView.findViewById(R.id.article_abstract);
            author = itemView.findViewById(R.id.article_author);
            tags = itemView.findViewById(R.id.article_tags);
            download = itemView.findViewById(R.id.download_button);
        }
    }

    public interface Callback{
        //Callback interface to handle heavier logic in the activity/fragment and not on
        //the adapter/ui thread
        void onArticleClicked(String url);
        void onArticleLongCLicked(String articleAbstract, String url, String title);
        void onDownloadArticle(String title, String author, String imageUrl, String articleAbstract, String webUrl, String news_desk);
    }
}
