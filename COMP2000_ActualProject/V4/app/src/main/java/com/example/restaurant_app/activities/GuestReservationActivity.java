package com.example.restaurant_app.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.database.AppDatabaseHelper;
import com.example.restaurant_app.models.NotificationItem;
import com.example.restaurant_app.models.Reservation;

import com.example.restaurant_app.R;
import com.example.restaurant_app.utils.NotificationHelper;

public class GuestReservationActivity extends AppCompatActivity {
    EditText textResName, textResDate, textResTime;
    Button buttonResSubmit;
    AppDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reservations);

        textResName = findViewById(R.id.textResName);
        textResDate = findViewById(R.id.textResDate);
        textResTime = findViewById(R.id.textResTime);
        buttonResSubmit = findViewById(R.id.buttonResSubmit);

        db = new AppDatabaseHelper(this);

        buttonResSubmit.setOnClickListener(v -> makeReservation());
    }

    private void makeReservation() {

        Reservation reservation = new Reservation(
                textResName.getText().toString(),
                textResDate.getText().toString(),
                textResTime.getText().toString(),
                "Pending"
        );

        boolean success = db.insertReservation(reservation);

        if (success) {
            NotificationHelper.showNotification(
                    this,
                    "New Reservation",
                    "A new table reservation has been made"
            );

            db.insertNotification(new NotificationItem(
                    "New Reservation",
                    "A new table reservation has been made for " + reservation.getDate() + " at " + reservation.getTime(),
                    "staff"
            ));

            Toast.makeText(this, "Reservation made", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
