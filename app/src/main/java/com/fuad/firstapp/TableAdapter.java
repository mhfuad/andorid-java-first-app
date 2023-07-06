package com.fuad.firstapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder>{
    private List<TableRowModel> tableData;

    public TableAdapter(List<TableRowModel> tableData) {
        this.tableData = tableData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TableRowModel rowModel = tableData.get(position);
        holder.textView1.setText(rowModel.getColumn1());
        // Bind other TextViews for each column
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        // Add TextViews for each column

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            // Initialize other TextViews for each column
        }
    }
}
