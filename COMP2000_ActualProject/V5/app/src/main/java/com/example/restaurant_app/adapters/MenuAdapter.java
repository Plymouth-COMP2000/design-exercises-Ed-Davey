package com.example.restaurant_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.restaurant_app.models.MenuItem;
import com.example.restaurant_app.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
    private List<MenuItem> menuList;

    public MenuAdapter(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textItemName, itemItemPrice;

        public ViewHolder(View view) {
            super(view);
            textItemName = view.findViewById(R.id.textItemName);
            itemItemPrice = view.findViewById(R.id.itemItemPrice);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem item = menuList.get(position);
        holder.textItemName.setText(item.getName());
        holder.itemItemPrice.setText("£" + item.getPrice());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
