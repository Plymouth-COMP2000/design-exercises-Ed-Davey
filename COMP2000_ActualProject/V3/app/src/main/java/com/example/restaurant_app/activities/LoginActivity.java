package com.example.restaurant_app.activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restaurant_app.R;
import com.example.restaurant_app.utils.AuthManager;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private RadioButton rbGuest;
    private RadioButton rbStaff;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainActivityLogin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Username = findViewById(R.id.editTextUsername);
        Password = findViewById(R.id.editTextPassword);

        rbGuest = findViewById(R.id.radioButtonGuest);
        rbStaff = findViewById(R.id.radioButtonStaff);

        btnSubmit = findViewById(R.id.ButtonSubmit);

        btnSubmit.setOnClickListener(v -> attemptLogin());

    }

    private void attemptLogin() {

        String username = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();

        String selectedRole;

        if (rbStaff.isChecked()) {
            selectedRole = "staff"; //Staff input
        } else if (rbGuest.isChecked()) {
            selectedRole = "guest"; //Guest input
        } else {
            selectedRole = ""; //No input
        }


        if (selectedRole.equals("staff")) {
            if (username.equals("staff") && password.equals("staff123")) {
                AuthManager.saveUser(this, username, "staff");
                startActivity(new Intent(this, StaffMainActivity.class));
            } else {
                Toast.makeText(this, "Invalid staff credentials", Toast.LENGTH_SHORT).show();
            }
        } else if (selectedRole.equals("guest")) {
            if (username.equals("guest") && password.equals("guest123")) {
                AuthManager.saveUser(this, username, "guest");
                startActivity(new Intent(this, GuestMainActivity.class));
            } else {
                Toast.makeText(this, "Invalid guest credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
        Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
        }
    }
}

