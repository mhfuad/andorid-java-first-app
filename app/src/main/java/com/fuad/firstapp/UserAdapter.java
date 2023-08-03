package com.fuad.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    UserActivity userActivity;
    List<User> users;

    public UserAdapter(UserActivity userActivity, List<User> users) {
        this.userActivity = userActivity;
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new UserHolder(LayoutInflater.from(userActivity).inflate(R.layout.))
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class UserHolder extends RecyclerView.ViewHolder{

        public UserHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
