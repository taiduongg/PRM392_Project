package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText etSignUpEmail, etSignUpPassword, etSignUpConfirmPassword;
    Button btnSignUp;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // Ánh xạ
        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        etSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);

        btnSignUp.setEnabled(false); // Disable ban đầu

        // Theo dõi input
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInputs();
            }
            @Override public void afterTextChanged(Editable s) {}
        };

        etSignUpEmail.addTextChangedListener(watcher);
        etSignUpPassword.addTextChangedListener(watcher);
        etSignUpConfirmPassword.addTextChangedListener(watcher);

        // 👉 Sau khi đăng ký thành công → quay về LoginActivity
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // đóng SignUpActivity
        });

        // Hoặc click "Already have an account? Log in"
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void validateInputs() {
        String email = etSignUpEmail.getText().toString().trim();
        String pass = etSignUpPassword.getText().toString().trim();
        String confirm = etSignUpConfirmPassword.getText().toString().trim();

        boolean valid = !email.isEmpty() && !pass.isEmpty() && pass.equals(confirm);
        btnSignUp.setEnabled(valid);
    }
}
