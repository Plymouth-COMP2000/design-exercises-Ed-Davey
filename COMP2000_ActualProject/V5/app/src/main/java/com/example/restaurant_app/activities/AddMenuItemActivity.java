package com.example.restaurant_app.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.R;
import com.example.restaurant_app.database.AppDatabaseHelper;
import com.example.restaurant_app.models.MenuItem;

public class AddMenuItemActivity extends AppCompatActivity {

    private EditText editTextMenuName, editTextMenuPrice;
    private Button buttonSave;
    private AppDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        editTextMenuName = findViewById(R.id.editTextMenuName);
        editTextMenuPrice = findViewById(R.id.editTextMenuPrice);
        buttonSave = findViewById(R.id.buttonSave);

        db = new AppDatabaseHelper(this);

        buttonSave.setOnClickListener(v -> saveMenuItem());
    }

    private void saveMenuItem() {
        String name = editTextMenuName.getText().toString().trim();
        String priceStr = editTextMenuPrice.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please enter name and price", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (Exception e) {
            Toast.makeText(this, "Price must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        // imagePath left blank for now (you can add later)
        MenuItem item = new MenuItem(name, price, "");

        boolean success = db.insertMenuItem(item);

        if (success) {
            Toast.makeText(this, "Menu item added", Toast.LENGTH_SHORT).show();
            finish(); // returns to StaffMenuActivity
        } else {
            Toast.makeText(this, "Failed to add menu item", Toast.LENGTH_SHORT).show();
        }
    }
}
