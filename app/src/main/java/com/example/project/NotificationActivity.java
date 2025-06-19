package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_page);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.navigation_notification);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_notification) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(NotificationActivity.this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.navigation_cart) {
                startActivity(new Intent(NotificationActivity.this, CartActivity.class));
                finish();
                return true;
            } else if (id == R.id.navigation_profile) {
                startActivity(new Intent(NotificationActivity.this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
