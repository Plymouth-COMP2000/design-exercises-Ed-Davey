package com.example.restaurant_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.R;
import com.example.restaurant_app.models.NotificationItem;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationItem> list;

    public NotificationAdapter(List<NotificationItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationItem n = list.get(position);
        holder.title.setText(n.getTitle());
        holder.message.setText(n.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<NotificationItem> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, message;

        ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.textNotificationTitle);
            message = v.findViewById(R.id.textNotificationMessage);
        }
    }
}