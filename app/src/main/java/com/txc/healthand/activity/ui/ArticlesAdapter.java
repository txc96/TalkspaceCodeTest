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
    public ArticlesAdapter(Context context, ArrayList<ArticleObject> articleObjects, Callback callback){
        this.context = context;
        this.articleObjects = articleObjects;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout cl = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticlesAdapter.ViewHolder(cl);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(articleObjects.get(position).getImageUrl()).into(holder.image);
        holder.title.setText(articleObjects.get(position).getTitle());
        holder.articleAbstract.setText(articleObjects.get(position).getArticleAbstract());
        holder.author.setText(articleObjects.get(position).getAuthor());
        holder.article.setOnClickListener(v -> callback.onArticleClicked(articleObjects.get(holder.getAdapterPosition()).getWebUrl()));
        holder.article.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callback.onArticleLongCLicked(articleObjects.get(
                        holder.getAdapterPosition()).getArticleAbstract(),
                        articleObjects.get(holder.getAdapterPosition()).getWebUrl()
                );
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleObjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout article;
        private ImageView image;
        private TextView title, articleAbstract, author;

        public ViewHolder(View itemView) {
            super(itemView);
            article = itemView.findViewById(R.id.article);
            image = itemView.findViewById(R.id.article_image);
            title = itemView.findViewById(R.id.article_title);
            articleAbstract = itemView.findViewById(R.id.article_abstract);
            author = itemView.findViewById(R.id.article_author);
        }
    }

    public interface Callback{
        void onArticleClicked(String url);
        void onArticleLongCLicked(String articleAbstract, String url);
    }
}
