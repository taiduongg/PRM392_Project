package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        bottomNav = findViewById(R.id.bottomNav);
        if (bottomNav == null) {
            Log.e("DEBUG", "bottomNav is NULL!");
        } else {
            Log.d("DEBUG", "bottomNav found.");
        }

        bottomNav.setSelectedItemId(R.id.navigation_cart);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.navigation_cart) {
                return true;
            } else if (id == R.id.navigation_home) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.navigation_notification) {
                startActivity(new Intent(CartActivity.this, NotificationActivity.class));
                finish();
                return true;
            } else if (id == R.id.navigation_profile) {
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
