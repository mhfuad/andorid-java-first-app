package com.fuad.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    MainActivity mainActivity;
    List<Post> posts;
    public PostAdapter(MainActivity mainActivity, List<Post> posts) {
        this.mainActivity = mainActivity;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.itemText.setText(posts.get(position).getId()+"\n"+posts.get(position).getUserId()+"\n"+posts.get(position).getTitle()+"\n"+posts.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        TextView itemText;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.itemPost);
        }
    }
}
