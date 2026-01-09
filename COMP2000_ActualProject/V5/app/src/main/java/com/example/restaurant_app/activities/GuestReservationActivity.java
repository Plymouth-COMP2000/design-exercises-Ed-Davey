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

        textResDate.setOnClickListener(v -> showDatePicker());
        textResTime.setOnClickListener(v -> showTimePicker());

        buttonResSubmit.setOnClickListener(v -> makeReservation());
    }

    private void makeReservation() {

        String name = textResName.getText().toString().trim();
        String date = textResDate.getText().toString().trim();
        String time = textResTime.getText().toString().trim();

        if (name.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please enter name, date and time", Toast.LENGTH_SHORT).show();
            return;
        }

        Reservation reservation = new Reservation(
                name,
                date,
                time,
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

            textResName.setText("");
            textResDate.setText("");
            textResTime.setText("");

            Toast.makeText(this, "Reservation made", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDatePicker() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH);
        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);

        android.app.DatePickerDialog dialog = new android.app.DatePickerDialog(
                this,
                (view, y, m, d) -> {
                    String date = String.format("%02d-%02d-%04d", d, m + 1, y);
                    textResDate.setText(date);
                },
                year, month, day
        );

        // Optional: prevent past dates
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dialog.show();
    }

    private void showTimePicker() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = cal.get(java.util.Calendar.MINUTE);

        new android.app.TimePickerDialog(
                this,
                (view, h, min) -> {
                    String time = String.format("%02d:%02d", h, min);
                    textResTime.setText(time);
                },
                hour, minute, true
        ).show();
    }

}
