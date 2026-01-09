package com.example.restaurant_app.activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurant_app.utils.AuthManager;
import android.util.Log;

import com.example.restaurant_app.R;
import com.example.restaurant_app.utils.NotificationHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationHelper.createNotificationChannel(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainActivityLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Username = findViewById(R.id.editTextUsername);
        Password = findViewById(R.id.editTextPassword);

        btnSubmit = findViewById(R.id.ButtonSubmit);

        btnSubmit.setOnClickListener(v -> attemptLogin());

    }

    private static final String STUDENT_ID = "10921313";

    private void attemptLogin() {

        String username = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable button to prevent double taps
        btnSubmit.setEnabled(false);

        new Thread(() -> {
            try {
                // Call the API
                org.json.JSONObject response = com.example.restaurant_app.utils.ApiClient
                        .readUser(STUDENT_ID, username);

                org.json.JSONObject user = response.getJSONObject("user");

                String apiPassword = user.getString("password");
                String userType = user.getString("usertype"); // Find if to go to Staff or Guest

                boolean passOk = password.equals(apiPassword);

                runOnUiThread(() -> {
                    btnSubmit.setEnabled(true);

                    if (!passOk) {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Chose API roles to the app roles
                    String appRole;
                    if ("staff".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
                        appRole = "staff";
                    } else {
                        appRole = "guest";
                    }

                    // Log to debug issues with the API integration
                    Log.d("API_LOGIN", "Usertype from API: " + userType);
                    Log.d("API_LOGIN", "Routing role in app: " + appRole);

                    AuthManager.saveUser(this, username, appRole);

                    if ("staff".equals(appRole)) {
                        startActivity(new Intent(this, StaffMainActivity.class));
                    } else {
                        startActivity(new Intent(this, GuestMainActivity.class));
                    }

                    finish();
                });

            } catch (Exception e) {
                runOnUiThread(() -> {
                    btnSubmit.setEnabled(true);
                    Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
}

