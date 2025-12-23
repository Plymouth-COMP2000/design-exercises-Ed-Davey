package com.example.restaurant_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.R;
import com.example.restaurant_app.database.AppDatabaseHelper;
import com.example.restaurant_app.models.NotificationItem;
import com.example.restaurant_app.models.Reservation;
import com.example.restaurant_app.utils.NotificationHelper;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {
    List<Reservation> list;
    AppDatabaseHelper db;
    Context context;

    public ReservationAdapter(List<Reservation> list, Context context) {
        this.list = list;
        this.context = context;
        this.db = new AppDatabaseHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reservation r = list.get(position);

        holder.textResInfo.setText(
                r.getGuestName() + " - " + r.getDate() + " " + r.getTime()
        );

        if ("Cancelled".equals(r.getStatus())) {
            holder.buttonResCancel.setEnabled(false);
            holder.buttonResCancel.setText("Cancelled");
        } else {
            holder.buttonResCancel.setEnabled(true);
            holder.buttonResCancel.setText("Cancel");

            holder.buttonResCancel.setOnClickListener(v -> {

                boolean success = db.updateReservationStatus(r.getId(), "Cancelled");

                if (success) {

                    db.insertNotification(new NotificationItem(
                            "Reservation Cancelled",
                            "Your reservation on " + r.getDate() + " at " + r.getTime() + " was cancelled",
                            "guest"
                    ));


                    NotificationHelper.showNotification(
                            context,
                            "Reservation Cancelled",
                            "Guest has been notified"
                    );

                    r.setStatus("Cancelled");
                    notifyItemChanged(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textResInfo;
        Button buttonResCancel;

        ViewHolder(View itemView) {
            super(itemView);
            textResInfo = itemView.findViewById(R.id.textResInfo);
            buttonResCancel = itemView.findViewById(R.id.buttonResCancel);
        }
    }
}
