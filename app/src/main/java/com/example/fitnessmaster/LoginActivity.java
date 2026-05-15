package com.example.fitnessmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!._-]{6,20}$");

    private Button loginButton;
    private Button registerButton;
    private EditText emailField;
    private EditText passwordField;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.showData), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Register.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> attemptLogin());

        TextView helpText = findViewById(R.id.forgot);
        helpText.setOnClickListener(v ->
                Toast.makeText(LoginActivity.this, "Use the same email and password you used during signup.", Toast.LENGTH_SHORT).show());
    }

    private void attemptLogin() {
        clearErrors();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (!validateInputs(email, password)) {
            return;
        }

        loginButton.setEnabled(false);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loginButton.setEnabled(true);
                        if (task.isSuccessful()) {
                            sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                            sharedPreferences.edit().putBoolean("isLogin", true).apply();
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, getLoginErrorMessage(task.getException()), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            emailLayout.setError("Email is required");
            emailField.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Enter a valid email address");
            emailField.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordLayout.setError("Password is required");
            passwordField.requestFocus();
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordLayout.setError("Password must be 6-20 chars and include at least 1 letter and 1 number");
            passwordField.requestFocus();
            return false;
        }
        return true;
    }

    private void clearErrors() {
        emailLayout.setError(null);
        passwordLayout.setError(null);
    }

    private String getLoginErrorMessage(Exception exception) {
        if (exception instanceof FirebaseAuthException) {
            String errorCode = ((FirebaseAuthException) exception).getErrorCode();
            if ("ERROR_INVALID_CREDENTIAL".equals(errorCode) || "ERROR_WRONG_PASSWORD".equals(errorCode)) {
                return "Incorrect email or password.";
            }
            if ("ERROR_USER_NOT_FOUND".equals(errorCode)) {
                return "No account found for this email.";
            }
            if ("ERROR_INVALID_EMAIL".equals(errorCode)) {
                return "That email address is not valid.";
            }
            if ("ERROR_NETWORK_REQUEST_FAILED".equals(errorCode)) {
                return "Network issue while logging in. Please try again.";
            }
            if ("ERROR_TOO_MANY_REQUESTS".equals(errorCode)) {
                return "Too many attempts. Please wait a moment and try again.";
            }
        }
        if (exception != null && exception.getMessage() != null && !exception.getMessage().trim().isEmpty()) {
            return exception.getMessage();
        }
        return "Login failed. Please try again.";
    }
}
