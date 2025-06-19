package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.navigation_profile);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_profile) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.navigation_cart) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
                finish();
                return true;
            } else if (id == R.id.navigation_notification) {
                startActivity(new Intent(ProfileActivity.this, NotificationActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
