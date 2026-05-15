package com.example.fitnessmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private static final String TAG = "Register";
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z ]{2,39}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!._-]{6,20}$");

    private Button registerButton;
    private Button goToLoginButton;
    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    private EditText passwordField;
    private TextInputLayout nameLayout;
    private TextInputLayout phoneLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerButton = findViewById(R.id.registerd);
        goToLoginButton = findViewById(R.id.goToLogin);
        nameField = findViewById(R.id.name);
        phoneField = findViewById(R.id.phone);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        nameLayout = findViewById(R.id.nameLayout);
        phoneLayout = findViewById(R.id.phoneLayout);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(view -> attemptSignup());
        goToLoginButton.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, LoginActivity.class));
            finish();
        });
    }

    private void attemptSignup() {
        clearErrors();
        String name = nameField.getText().toString().trim();
        String phone = phoneField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (!validateInputs(name, phone, email, password)) {
            return;
        }

        registerButton.setEnabled(false);
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                registerButton.setEnabled(true);
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Signup successful. Please log in.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Exception exception = task.getException();
                    Log.e(TAG, "Signup failed", exception);
                    Toast.makeText(Register.this, getSignupErrorMessage(exception), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateInputs(String name, String phone, String email, String password) {
        if (name.isEmpty()) {
            nameLayout.setError("Full name is required");
            nameField.requestFocus();
            return false;
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            nameLayout.setError("Use 3-40 letters and spaces only");
            nameField.requestFocus();
            return false;
        }
        if (phone.isEmpty()) {
            phoneLayout.setError("Phone number is required");
            phoneField.requestFocus();
            return false;
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            phoneLayout.setError("Enter a valid 10-digit mobile number");
            phoneField.requestFocus();
            return false;
        }
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
            passwordLayout.setError("Password must be 6-20 chars with at least 1 letter and 1 number");
            passwordField.requestFocus();
            return false;
        }
        return true;
    }

    private void clearErrors() {
        nameLayout.setError(null);
        phoneLayout.setError(null);
        emailLayout.setError(null);
        passwordLayout.setError(null);
    }

    private String getSignupErrorMessage(Exception exception) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            return "Password is too weak. Use at least 6 characters.";
        }
        if (exception instanceof FirebaseAuthUserCollisionException) {
            return "This email is already registered. Try logging in instead.";
        }
        if (exception instanceof FirebaseAuthException) {
            String errorCode = ((FirebaseAuthException) exception).getErrorCode();
            if ("ERROR_INVALID_EMAIL".equals(errorCode)) {
                return "That email address is not valid.";
            }
            if ("ERROR_OPERATION_NOT_ALLOWED".equals(errorCode)) {
                return "Email/password signup is not enabled in Firebase yet.";
            }
            if ("ERROR_NETWORK_REQUEST_FAILED".equals(errorCode)) {
                return "Network issue while signing up. Check your internet and try again.";
            }
            if ("ERROR_TOO_MANY_REQUESTS".equals(errorCode)) {
                return "Too many signup attempts. Please wait and try again.";
            }
        }
        if (exception != null && exception.getMessage() != null && !exception.getMessage().trim().isEmpty()) {
            return exception.getMessage();
        }
        return "Signup failed. Please try again.";
    }
}
